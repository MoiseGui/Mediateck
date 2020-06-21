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

import beans.Produit;
import beans.User;
import dao.ProduitService;
import utils.SingletonConnexion;


/**
 * Servlet implementation class Produits
 */
@WebServlet("/Produits")
public class Produits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Produits() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				ProduitService produitService = new ProduitService(connection);

					// vérifier si il y'a un produit en cours de delete
					
					if (request.getParameter("prod") != null) {
						
						long id = Long.valueOf(request.getParameter("prod"));
						
						int i = produitService.deleteById(id);
						
						request.setAttribute("error", true);
						request.setAttribute("errorNo", i);
						
					}
					
					// Charger les clients pour la page
					
					List<Produit> produits = produitService.findAll();
					
					request.setAttribute("produits", produits);
					
					RequestDispatcher dispatcher = context.getRequestDispatcher("/produits.jsp");
					dispatcher.forward(request, response);
					
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
