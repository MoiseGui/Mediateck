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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = SingletonConnexion.startConnection();
		int exist = 0;
		String errorMessage;
		boolean error = false;
		if(connection == null) {
			error = true;
			errorMessage = "La connexion a échouée. Veillez réessayer plutard.";
			request.setAttribute("error", error);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("exist", exist);
			doGet(request, response);
		}
		else {
			String username = request.getParameter("login");
			String pass = request.getParameter("pass");
			
			
			UserService service = new UserService(connection);
			
			exist = service.existByUsernameAndPass(username, pass);
			
			if(exist != 1) {
//				System.out.println(exist);
				error = true;
				errorMessage = "Username ou mot de passe incorrect. Veuillez réessayer.";
				request.setAttribute("error", error);
				request.setAttribute("errorMessage", errorMessage);
				request.setAttribute("exist", exist);
				doGet(request, response);
			}
			else {
				User user = service.findByUsername(username);
				
				if(user == null) {
					error = true;
					errorMessage = "Utilisateur introuvable. Veillez réessayer plutard.";
					request.setAttribute("error", error);
					request.setAttribute("errorMessage", errorMessage);
					request.setAttribute("exist", exist);
					doGet(request, response);
				}
				else {
					HttpSession session = request.getSession(true);
					session.setAttribute("connection", connection);
					if(user.getCategorie() == 3) {
						session.setAttribute("user", user);
						ServletContext context = getServletContext();
						RequestDispatcher dispatcher = context.getRequestDispatcher("/Admin");
						dispatcher.forward(request, response);
					}
					else if(user.getCategorie() == 2) {
						session.setAttribute("user", user);
						ServletContext context = getServletContext();
						RequestDispatcher dispatcher = context.getRequestDispatcher("/SAV");
						dispatcher.forward(request, response);
					}
					else {
						session.setAttribute("user", user);
						ServletContext context = getServletContext();
						RequestDispatcher dispatcher = context.getRequestDispatcher("/Dashboard");
						dispatcher.forward(request, response);
					}
				}
			}
		}
	}

}
