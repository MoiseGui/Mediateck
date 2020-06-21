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
import beans.Produit;
import beans.User;
import dao.ClientService;
import dao.FactureService;
import dao.ProduitService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
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
		User user;
		boolean error = false;

		if (session == null) {
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				error = true;
			} else {
				user = (User) session.getAttribute("user");
				if (user == null || user.getCategorie() != 1) {
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

			FactureService factureService = new FactureService(connection);
			List<Facture> factures = factureService.findAll();
			request.setAttribute("factures", factures);

			RequestDispatcher dispatcher = context.getRequestDispatcher("/dashboard.jsp");
			dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
