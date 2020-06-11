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
import dao.ClientService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class AddClient
 */
@WebServlet("/AddClient")
public class AddClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClient() {
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
			
				RequestDispatcher dispatcher = context.getRequestDispatcher("/AddClient.jsp");
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
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			
			if(nom.isEmpty() || prenom.isEmpty()) {
				
				doGet(request, response);
			}
			else {
				HttpSession session = request.getSession(false);
				Connection connection = (Connection) session.getAttribute("connection");
				if (connection == null) {
					connection = SingletonConnexion.startConnection();
				}
				
				ClientService clientService = new ClientService(connection);
				
				int result = clientService.add(nom, prenom);
				
				request.setAttribute("NewError", true);
				request.setAttribute("NewNo", result);
				
				doGet(request, response);
				
			}
		}
	}

}
