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

import beans.Client;
import beans.User;
import dao.ClientService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class ClientEdit
 */
@WebServlet("/ClientEdit")
public class ClientEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientEdit() {
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
			
			ClientService clientService = new ClientService(connection);

			// vérifier si il y'a un cli en cours d'edit

			if (request.getParameter("edit") != null) {

				long id = Long.valueOf(request.getParameter("edit"));

				Client client = clientService.findById(id);

				if (client != null) {
					request.setAttribute("client", client);
					RequestDispatcher dispatcher = context.getRequestDispatcher("/clientEdit.jsp");
					dispatcher.forward(request, response);
				} else {
					
					RequestDispatcher dispatcher = context.getRequestDispatcher("/Clients");
					dispatcher.forward(request, response);
				}

			}
			else {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Clients");
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
		if(request.getParameter("sauvegarder") == null) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Clients");
			dispatcher.forward(request, response);
		}
		else {
			long id = Long.valueOf(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			
			if(nom.isEmpty() || prenom.isEmpty() || id <= 0) {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Clients");
				dispatcher.forward(request, response);
			}
			else {
				HttpSession session = request.getSession(false);
				Connection connection = (Connection) session.getAttribute("connection");
				if (connection == null) {
					connection = SingletonConnexion.startConnection();
				}
				
				ClientService clientService = new ClientService(connection);
				
				int result = clientService.update(id, nom, prenom);
				
				request.setAttribute("editError", true);
				request.setAttribute("editNo", result);
				RequestDispatcher dispatcher = context.getRequestDispatcher("/Clients");
				dispatcher.forward(request, response);
				
			}
		}
	}

}
