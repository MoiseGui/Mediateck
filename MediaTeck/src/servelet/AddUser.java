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
import dao.UserService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
			
				RequestDispatcher dispatcher = context.getRequestDispatcher("/AddUser.jsp");
				dispatcher.forward(request, response);
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			if (request.getParameter("sauvegarder") == null) {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			} else {
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String username = request.getParameter("username");
				int categorie = Integer.parseInt(request.getParameter("categorie"));
				String pass = request.getParameter("pass");
				String passConfirm = request.getParameter("passConfirm");
				
				if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
					request.setAttribute("formError", true);
					request.setAttribute("formMessage", "Veuillez saisir les informations obligatoires");
					doGet(request, response);
				} else {
					if(!pass.equals(passConfirm)) {
						request.setAttribute("formError", true);
						request.setAttribute("formMessage", "Les deux mot de passe doivent être identiques");
						doGet(request, response);
					}
					else {
						Connection connection = (Connection) session.getAttribute("connection");
						if (connection == null) {
							connection = SingletonConnexion.startConnection();
						}
						UserService userService = new UserService(connection);
						int exist = userService.existByUsername(username);
						
						if(exist != -1) {
							request.setAttribute("formError", true);
							request.setAttribute("formMessage", "Username déjà utlisé par un autre utilisateur");
							doGet(request, response);
						}
						else {
							int result = userService.add(nom, prenom, categorie, username, pass);
							
							request.setAttribute("NewError", true);
							request.setAttribute("NewNo", result);
							
							doGet(request, response);
						}
						
					}
				}
			}
				
		}
	}

}
