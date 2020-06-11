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

import dao.UserService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
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
		beans.User user;
		boolean error = false;

		if (session == null) {
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				error = true;
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			user = (beans.User) session.getAttribute("user");
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = context.getRequestDispatcher("/user.jsp");
			dispatcher.forward(request, response);

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
		beans.User user;
		boolean error = false;

		if (session == null) {
			error = true;
		} else {
			if (session.getAttribute("user") == null) {
				error = true;
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			user = (beans.User) session.getAttribute("user");

			if (request.getParameter("sauvegarder") == null) {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			} else {
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String username = request.getParameter("username");
				String oldPass = request.getParameter("oldPass");
				String pass = request.getParameter("pass");
				String passConfirm = request.getParameter("passConfirm");

				if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty()) {
					request.setAttribute("formError", true);
					request.setAttribute("formMessage", "Veuillez saisir les informations obligatoires");
					doGet(request, response);
				} else {
					System.out.println("The fiels are not empty. good");
					Connection connection = (Connection) session.getAttribute("connection");
					if (connection == null) {
						connection = SingletonConnexion.startConnection();
					}
					UserService userService = new UserService(connection);

					if (oldPass.isEmpty() && pass.isEmpty() && passConfirm.isEmpty()) {
						// password is not being changed
						System.out.println("password is not being changed");

						if (!username.equals(user.getUsername())) {
							beans.User check = userService.findByUsername(username);

							if (check != null) {
								request.setAttribute("formError", true);
								request.setAttribute("formMessage",
										"Username d�j� utilis�, veuillez essayer en un autre.");
								doGet(request, response);
								return;
							}
						}

						System.out.println("new username good");
						int result = userService.edit(user.getId(), nom, prenom, username, null);

						request.setAttribute("userError", true);
						request.setAttribute("userNo", result);

						if (result == 1) {
							user = userService.findByUsername(username);
							session.setAttribute("user", user);
						}

						if (user.getCategorie() == 3) {
							context = getServletContext();
							RequestDispatcher dispatcher = context.getRequestDispatcher("/Admin");
							dispatcher.forward(request, response);
						} else if (user.getCategorie() == 2) {
							context = getServletContext();
							RequestDispatcher dispatcher = context.getRequestDispatcher("/SAV");
							dispatcher.forward(request, response);
						} else {
							context = getServletContext();
							RequestDispatcher dispatcher = context.getRequestDispatcher("/Dashboard");
							dispatcher.forward(request, response);
						}

					} else {
						boolean changingPassError = false;

						if (oldPass.isEmpty())
							changingPassError = true;
						if (pass.isEmpty())
							changingPassError = true;
						if (passConfirm.isEmpty())
							changingPassError = true;

						if (changingPassError) {
							request.setAttribute("formError", true);
							request.setAttribute("formMessage",
									"L'ancien mot de passe, le nouveau et sa confirmation sont obligatoirs pour changer votre mot de passe.");
							doGet(request, response);
						} else {
							// password is being changed
							if (!pass.equals(passConfirm)) {
								request.setAttribute("formError", true);
								request.setAttribute("formMessage",
										"Le nouveau mot de passe et sa confirmation ne sont pas identiques.");
								doGet(request, response);
							} else {

								if (!pass.equals(user.getPass())) {
									request.setAttribute("formError", true);
									request.setAttribute("formMessage",
											"Ancien mot de passe incorrect, veuillez r�essayer.");
									doGet(request, response);
								} else {

									if (!username.equals(user.getUsername())) {
										beans.User check = userService.findByUsername(username);

										if (check != null) {
											request.setAttribute("formError", true);
											request.setAttribute("formMessage",
													"Username d�j� utilis�, veuillez essayer en un autre.");
											doGet(request, response);
										} else {

											int result = userService.edit(user.getId(), nom, prenom, username, pass);

											request.setAttribute("userError", true);
											request.setAttribute("userNo", result);

											if (result == 1) {
												user = userService.findByUsername(username);
												session.setAttribute("user", user);
											}

											if (user.getCategorie() == 3) {
												context = getServletContext();
												RequestDispatcher dispatcher = context.getRequestDispatcher("/Admin");
												dispatcher.forward(request, response);
											} else if (user.getCategorie() == 2) {
												context = getServletContext();
												RequestDispatcher dispatcher = context.getRequestDispatcher("/SAV");
												dispatcher.forward(request, response);
											} else {
												context = getServletContext();
												RequestDispatcher dispatcher = context
														.getRequestDispatcher("/Dashboard");
												dispatcher.forward(request, response);
											}

										}
									}

								}

							}
						}
					}

				}
			}

		}
	}

}