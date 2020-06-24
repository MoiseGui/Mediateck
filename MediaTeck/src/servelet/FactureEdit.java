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
import dao.Ligne_factureService;
import dao.ProduitService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class FactureEdit
 */
@WebServlet("/FactureEdit")
public class FactureEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FactureEdit() {
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
			String redirectTo;
			if(user.getCategorie() == 1) {
				redirectTo = "/Dashboard";
			}
			else {
				redirectTo = "/Factures";
			}

			if (request.getParameter("edit") != null) {
				Connection connection = (Connection) session.getAttribute("connection");
				if (connection == null) {
					connection = SingletonConnexion.startConnection();
				}


				FactureService factureService = new FactureService(connection);
				long id = Long.valueOf(request.getParameter("edit"));
				Facture facture = factureService.findById(id);
				
				if(facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher(redirectTo);
					dispatcher.forward(request, response);
				}
				else {
					session.setAttribute("FactureEdit", facture);
					
					// Charger les clients pour la page
					ClientService clientService = new ClientService(connection);
					List<Client> clients = clientService.findAll();
					request.setAttribute("clients", clients);
					
					// Charger les produits
					ProduitService produitService = new ProduitService(connection);
					List<Produit> produits = produitService.findAll();
					request.setAttribute("produits", produits);
					
					if (user.getCategorie() == 3) {
						RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEditAdmin.jsp");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEdit.jsp");
						dispatcher.forward(request, response);
					}
				}
				

			}
			else if (request.getParameter("remove") != null) {
				Facture facture = (Facture) session.getAttribute("FactureEdit");

				if (facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}
					long id = Long.parseLong(request.getParameter("remove"));

					Ligne_facture ligne = null;
					for (Ligne_facture ligne_facture : facture.getLigne_factures()) {
						if (ligne_facture.getProduit().getId() == id) {
							ligne = ligne_facture;
							facture.getLigne_factures().remove(ligne_facture);
							facture.calculTotal();
							break;
						}
					}
					
					if(ligne != null) {
						Ligne_factureService ligne_factureService = new Ligne_factureService(connection);
						int result = ligne_factureService.deleteById(ligne.getNum_fac(), ligne.getProduit().getId(), ligne.getQte_achete());
						request.setAttribute("removeError", true);
						request.setAttribute("removeNo", result);
					}
					
					FactureService factureService = new FactureService(connection);
					facture = factureService.findById(facture.getNum_fac());
					
					if(facture == null) {
						RequestDispatcher dispatcher = context.getRequestDispatcher(redirectTo);
						dispatcher.forward(request, response);
					}
					else {
						session.setAttribute("FactureEdit", facture);
						
						// Charger les clients pour la page
						ClientService clientService = new ClientService(connection);
						List<Client> clients = clientService.findAll();
						request.setAttribute("clients", clients);
						
						// Charger les produits
						ProduitService produitService = new ProduitService(connection);
						List<Produit> produits = produitService.findAll();
						request.setAttribute("produits", produits);
						
						if (user.getCategorie() == 3) {
							RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEditAdmin.jsp");
							dispatcher.forward(request, response);
						} else {
							RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEdit.jsp");
							dispatcher.forward(request, response);
						}
					}

				}
			}
			else {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Dashboard");
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
			String redirectTo;
			if(user.getCategorie() == 1) {
				redirectTo = "/Dashboard";
			}
			else {
				redirectTo = "/Factures";
			}

			if (request.getParameter("ajouter") != null) {
				Facture facture = (Facture) session.getAttribute("FactureEdit");

				if (facture == null) {
					System.out.println("facture nulle");
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}

					
					long produitId = Long.parseLong(request.getParameter("produit"));
					int qte = Integer.parseInt(request.getParameter("qte"));


					ProduitService produitService = new ProduitService(connection);
					Produit produit = produitService.findById(produitId);

					if(qte > produit.getQte_stock()) {
						request.setAttribute("NewError", true);
						request.setAttribute("NewNo", 20001);
					}
					else {
						Ligne_facture test = facture.getLigne_factures().stream()
								.filter(l -> l.getProduit().getId() == produitId).findFirst().orElse(null);
						
						Ligne_factureService ligne_factureService = new Ligne_factureService(connection);
						int result;
						if (test == null) {
							Ligne_facture ligne_facture = new Ligne_facture(produit, qte);
							facture.getLigne_factures().add(ligne_facture);
							
							result = ligne_factureService.add(facture.getNum_fac(), produitId, qte);
							
						} else {
							test.setQte_achete(test.getQte_achete() + qte);
							
							result = ligne_factureService.update(facture.getNum_fac(), produitId, qte);
						}
						
						request.setAttribute("ajouterError", true);
						request.setAttribute("ajouterNo", result);
					}
					
					session.setAttribute("FactureEdit", facture);
					
					// Charger les clients pour la page
					ClientService clientService = new ClientService(connection);
					List<Client> clients = clientService.findAll();
					request.setAttribute("clients", clients);
					
					// Charger les produits
					List<Produit> produits = produitService.findAll();
					request.setAttribute("produits", produits);
					
					if (user.getCategorie() == 3) {
						RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEditAdmin.jsp");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = context.getRequestDispatcher("/FactureEdit.jsp");
						dispatcher.forward(request, response);
					}
				}
			}

			else if (request.getParameter("enregistrer") != null) {
				String date = request.getParameter("date");
				long clientId = Long.parseLong(request.getParameter("client"));
				
				Facture facture = (Facture) session.getAttribute("FactureEdit");

				if (facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/");
					dispatcher.forward(request, response);
				} else {
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}
					
					FactureService factureService = new FactureService(connection);
					int result = factureService.update(facture.getNum_fac(), clientId, date);
					
					request.setAttribute("factureError", true);
					request.setAttribute("factureNo", result);
					if(result >= 0) {
						session.removeAttribute("FactureEdit");
						
						RequestDispatcher dispatcher = context.getRequestDispatcher(redirectTo);
						dispatcher.forward(request, response);
					}
					else {
						session.setAttribute("FactureEdit", facture);
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
