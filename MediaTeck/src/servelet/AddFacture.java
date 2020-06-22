package servelet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Facture;
import beans.Ligne_facture;
import beans.Produit;
import beans.User;
import dao.ClientService;
import dao.FactureService;
import dao.ProduitService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class AddFacture
 */
@WebServlet("/AddFacture")
public class AddFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFacture() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		User user = null;
		boolean error = false;

		if (session == null) {
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				error = true;
			} else {
				user = (User) session.getAttribute("user");
				if (user == null || user.getCategorie() == 2) {
					error = true;
				}
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			Connection connection = (Connection) session.getAttribute("connection");
			if (connection == null) {
				connection = SingletonConnexion.startConnection();
			}

			// Charger les clients pour la page
			ClientService clientService = new ClientService(connection);
			List<Client> clients = clientService.findAll();
			request.setAttribute("clients", clients);

			// Charger les produits
			ProduitService produitService = new ProduitService(connection);
			List<Produit> produits = produitService.findAll();
			request.setAttribute("produits", produits);

			if (session.getAttribute("newFac") == null) {
				Facture facture = new Facture();
				session.setAttribute("newFac", 1);
				session.setAttribute("FactureAdd", facture);
			}

			if (request.getParameter("remove") != null) {
				Facture facture = (Facture) session.getAttribute("FactureAdd");

				if (facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					long id = Long.parseLong(request.getParameter("remove"));
					
					for(Ligne_facture ligne_facture : facture.getLigne_factures()) {
						if(ligne_facture.getProduit().getId() == id) {
							facture.getLigne_factures().remove(ligne_facture);
							facture.calculTotal();
							break;
						}
					}
					
				}
			}

			if (user.getCategorie() == 3) {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/AddFactureAdmin.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/AddFacture.jsp");
				dispatcher.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		User user = null;
		boolean error = false;

		if (session == null) {
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				error = true;
			} else {
				user = (User) session.getAttribute("user");
				if (user == null || user.getCategorie() == 2) {
					error = true;
				}
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {

			if (request.getParameter("ajouter") != null) {
				Facture facture = (Facture) session.getAttribute("FactureAdd");

				if (facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}

					String date = request.getParameter("date");
					long clientId = Long.parseLong(request.getParameter("client"));
					long produitId = Long.parseLong(request.getParameter("produit"));
					int qte = Integer.parseInt(request.getParameter("qte"));

					ClientService clientService = new ClientService(connection);
					Client client = clientService.findById(clientId);
					facture.setClient(client);

					ProduitService produitService = new ProduitService(connection);
					Produit produit = produitService.findById(produitId);

					if(qte > produit.getQte_stock()) {
						request.setAttribute("NewError", true);
						request.setAttribute("NewNo", 20001);
					}
					else {
						Ligne_facture test = facture.getLigne_factures().stream()
								.filter(l -> l.getProduit().getId() == produitId).findFirst().orElse(null);
						
						if (test == null) {
							Ligne_facture ligne_facture = new Ligne_facture(produit, qte);
							facture.getLigne_factures().add(ligne_facture);
						} else {
							test.setQte_achete(test.getQte_achete() + qte);
						}
					}
					

					facture.setDate_fac(date);
					facture.calculTotal();

					session.setAttribute("newFac", 0);
					session.setAttribute("FactureAdd", facture);

					doGet(request, response);
				}
			}

			else if (request.getParameter("enregistrer") != null) {
				
				Facture facture = (Facture) session.getAttribute("FactureAdd");

				if (facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}
					
					FactureService factureService = new FactureService(connection);
					int result = factureService.save(facture);
					
					request.setAttribute("NewError", true);
					request.setAttribute("NewNo", result);
					if(result == 1) {
						session.removeAttribute("newFac");
						session.removeAttribute("FactureAdd");
						
						RequestDispatcher dispatcher = context.getRequestDispatcher("/Dashboard");
						dispatcher.forward(request, response);
					}
					else {
						doGet(request, response);
					}
					
				}
				
			} else {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			}

		}
	}

}
