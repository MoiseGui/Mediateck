package servelet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.ProduitService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class AddClient
 */
@WebServlet("/AddProduit")
public class AddProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduit() {
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
			
				RequestDispatcher dispatcher = context.getRequestDispatcher("/AddProduit.jsp");
				dispatcher.forward(request, response);
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("ajouter") == null) {
			doGet(request, response);
		}
		else {
			String designation = request.getParameter("designation");
			double prix = Double.valueOf(request.getParameter("prix"));
			long qte_stock = Long.valueOf(request.getParameter("qte_stock"));
			
			if(designation.isEmpty() || prix <= 0 || qte_stock <= 0) {
				
				doGet(request, response);
			}
			else {
				HttpSession session = request.getSession(false);
				Connection connection = (Connection) session.getAttribute("connection");
				if (connection == null) {
					connection = SingletonConnexion.startConnection();
				}
				
				ProduitService produitService = new ProduitService(connection);
				
				int result = produitService.add(designation, prix, qte_stock);
				
				request.setAttribute("NewError", true);
				request.setAttribute("NewNo", result);
				
				doGet(request, response);
				
			}
		}
	}

}
