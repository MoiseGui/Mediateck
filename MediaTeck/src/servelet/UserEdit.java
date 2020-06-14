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
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEdit() {
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
			
			UserService userService = new UserService(connection);

			// vérifier si il y'a un user en cours d'edit

			if (request.getParameter("edit") != null) {

				long id = Long.valueOf(request.getParameter("edit"));

				User userEdit = userService.findById(id);

				if (userEdit != null) {
					request.setAttribute("userEdit", userEdit);
					RequestDispatcher dispatcher = context.getRequestDispatcher("/userEdit.jsp");
					dispatcher.forward(request, response);
				} else {
					
					RequestDispatcher dispatcher = context.getRequestDispatcher("/Users");
					dispatcher.forward(request, response);
				}

			}
			else {
				
				if (request.getAttribute("edit") != null) {

					long id = Long.valueOf( (Long) request.getAttribute("edit"));

					User userEdit = userService.findById(id);

					if (userEdit != null) {
						request.setAttribute("userEdit", userEdit);
						RequestDispatcher dispatcher = context.getRequestDispatcher("/userEdit.jsp");
						dispatcher.forward(request, response);
					} else {
						
						RequestDispatcher dispatcher = context.getRequestDispatcher("/Users");
						dispatcher.forward(request, response);
					}

				}
				else {
					RequestDispatcher dispatcher = context.getRequestDispatcher("/Users");
					dispatcher.forward(request, response);
				}
			}

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
			System.out.println("Error was true here session");
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				System.out.println("Error was true here user");
				error = true;
			} else {
				user = (User) session.getAttribute("user");
				if (user == null || user.getCategorie() != 3) {
					System.out.println("Error was true here categorie");
					error = true;
				}
			}
		}

		if (error) {
			System.out.println("Error was true");
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			
			if (request.getParameter("sauvegarder") == null) {
				System.out.println("no sauvegarder");
				RequestDispatcher dispatcher = context.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			} else {
				long id = Long.parseLong(request.getParameter("id"));
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String username = request.getParameter("username");
				int categorie = Integer.parseInt(request.getParameter("categorie"));
				String pass = request.getParameter("pass");
				String passConfirm = request.getParameter("passConfirm");
				
				if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty()) {
					request.setAttribute("formError", true);
					request.setAttribute("formMessage", "Veuillez saisir les informations obligatoires");
					request.setAttribute("edit", id);
					doGet(request, response);
				} else {
					if(pass.isEmpty() && !passConfirm.isEmpty() || !pass.isEmpty() && passConfirm.isEmpty() || !pass.equals(passConfirm)) {
						request.setAttribute("formError", true);
						request.setAttribute("formMessage", "Les deux mots de passe doivent être identiques");
						request.setAttribute("edit", id);
						doGet(request, response);
					}
					else {
						System.out.println("Pass : " + pass + "PassConfirm " + passConfirm);
						System.out.println("pass and passConfirm exacts");
						
						Connection connection = (Connection) session.getAttribute("connection");
						if (connection == null) {
							connection = SingletonConnexion.startConnection();
						}
						UserService userService = new UserService(connection);
						
						boolean redirect = false;
						
						User userEdit = userService.findById(id);
						
						System.out.println("userEdit charged");
						
						if(!userEdit.getUsername().equals(username)) {
							
							System.out.println("old username : " + userEdit.getUsername() + " new : " + username);
							
							System.out.println("username has changed");
							
							int exist = userService.existByUsername(username);
							
							if(exist != -1) {
								
								System.out.println("username already used");
								
								request.setAttribute("formError", true);
								request.setAttribute("formMessage", "Username déjà utlisé par un autre utilisateur");
								redirect = true;
							}
							
						}
						
						if(redirect) {
							System.out.println("doG was true");
							request.setAttribute("edit", id);
							doGet(request, response);
						}
						else {
							System.out.println("doGet false");
							
							int result;
							if(!pass.isEmpty()) {
								System.out.println("changing password too");
								result = userService.adminEdit(id, nom, prenom, categorie, username, pass);
							}
							else {
								System.out.println("Not changing password");
								result = userService.adminEdit(id, nom, prenom, categorie, username, null);
							}
							
							System.out.println("Result "+result);
							
							request.setAttribute("editError", true);
							request.setAttribute("editNo", result);
							
							RequestDispatcher dispatcher = context.getRequestDispatcher("/Users");
							dispatcher.forward(request, response);
						}
						
					}
				}
			}
				
		}
	}

}
