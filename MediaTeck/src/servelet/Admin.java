package servelet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import dao.UserService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
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
				if (user == null || user.getCategorie() != 3) {
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
			
			// charger les factures
			FactureService factureService = new FactureService(connection);
			List<Facture> factures = factureService.findAll();
			
			request.setAttribute("factures", factures.size());
			
			double chiffre = 0;
			for (Facture facture : factures) {
				chiffre += facture.getTotal();
			}
			
			request.setAttribute("chiffre", chiffre);

			// Charger les clients pour la page
			ClientService clientService = new ClientService(connection);
			List<Client> clients = clientService.findAll();
			request.setAttribute("clients", clients.size());

			// Charger les produits
			ProduitService produitService = new ProduitService(connection);
			List<Produit> produits = produitService.findAll();
			request.setAttribute("produits", produits.size());
			
			List<Produit> produitsStock = new ArrayList<Produit>();
			
			for (Produit produit : produits) {
				if(produit.getQte_stock() <= 5) produitsStock.add(produit);
			}
			
			request.setAttribute("produitsStock", produitsStock);
			request.setAttribute("problems", produitsStock.size());
			
			// charger les users
			UserService userService = new UserService(connection);
			List<User> users = userService.findAll();
			
			int normals = 0, admins = 0, savs = 0;
			for (User u : users) {
				if(u.getCategorie() == 1) normals ++;
				else if(u.getCategorie() == 2) savs ++;
				else admins ++;
			}
			
			request.setAttribute("users", users.size());
			request.setAttribute("normals", normals);
			request.setAttribute("admins", admins);
			request.setAttribute("savs", savs);

			RequestDispatcher dispatcher = context.getRequestDispatcher("/admin.jsp");
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
