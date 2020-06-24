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

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import beans.Facture;
import beans.Ligne_facture;
import beans.User;
import dao.FactureService;
import utils.SingletonConnexion;

/**
 * Servlet implementation class GetFacture
 */
@WebServlet("/GetFacture")
public class GetFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFacture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				if (user == null || user.getCategorie() == 2) {
					error = true;
				}
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			String redirectTo;
			if(user.getCategorie() == 1) {
				redirectTo = "/Dashboard";
			}
			else {
				redirectTo = "/Factures";
			}
			RequestDispatcher dispatcher = context.getRequestDispatcher(redirectTo);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				if (user == null || user.getCategorie() == 2) {
					error = true;
				}
			}
		}

		if (error) {
			RequestDispatcher dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			
			String redirectTo;
			if(user.getCategorie() == 1) {
				redirectTo = "/Dashboard";
			}
			else {
				redirectTo = "/Factures";
			}
			
			if (request.getParameter("generer") == null || request.getParameter("facture") == null) {
				RequestDispatcher dispatcher = context.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			}
			else {
				Connection connection = (Connection) session.getAttribute("connection");
				if (connection == null) {
					connection = SingletonConnexion.startConnection();
				}
				FactureService factureService = new FactureService(connection);
				
				long id = Long.valueOf(request.getParameter("facture"));
				Facture facture = factureService.findById(id);
				
				if(facture == null) {
					RequestDispatcher dispatcher = context.getRequestDispatcher(redirectTo);
					dispatcher.forward(request, response);
				}
				else {
					generatePdf(request, response, facture);
				}
			}
			
		}
	}
	
	
	private void generatePdf(HttpServletRequest request, HttpServletResponse response, Facture facture) {
		String masterPath = request.getServletContext().getRealPath( "/WEB-INF/factureModele.pdf" );
		response.setContentType( "application/pdf" );
		
		
		try ( PdfReader reader = new PdfReader( masterPath );
			  PdfWriter writer = new PdfWriter( response.getOutputStream() );
			  PdfDocument document = new PdfDocument( reader, writer ) ){
			
			PdfPage page = document.getPage( 1 );
			PdfCanvas canvas = new PdfCanvas( page );
			
			FontProgram fontProgram = FontProgramFactory.createFont();
			PdfFont font = PdfFontFactory.createFont( fontProgram, "utf-8", true );
			canvas.setFontAndSize( font, 16 );
			
			canvas.beginText();
			
			canvas.setTextMatrix( 38, 708 );
			canvas.showText( "" + facture.getDate_fac() );
			
			canvas.setTextMatrix( 215, 708 );
			canvas.showText( "" + facture.getNum_fac() );
			
			
			canvas.setTextMatrix( 38, 640 );
			canvas.showText( "" + facture.getClient().getPrenom() + " " + facture.getClient().getNom() );
			
			
			canvas.setFontAndSize( font, 12 );
			
			int top = 500;
			for(Ligne_facture ligne : facture.getLigne_factures()) {
				
				canvas.setTextMatrix( 35, top );
				canvas.showText( "" + ligne.getProduit().getDesignation() );
				
				canvas.setTextMatrix( 205, top );
				canvas.showText( "" + ligne.getQte_achete() );
				
				canvas.setTextMatrix( 310, top );
				canvas.showText( "" + ligne.getProduit().getPrix() );
				
				canvas.setTextMatrix( 450, top );
				canvas.showText( "" + ligne.getQte_achete() * ligne.getProduit().getPrix() );
				
				top -= 25;
			}
			
			canvas.setTextMatrix( 460, 197 );
			canvas.showText( "" + facture.getTotal() );
			
			canvas.setTextMatrix( 460, 182 );
			canvas.showText( "" + facture.getTotal() * 0.2 );
			
			canvas.setTextMatrix( 460, 167 );
			canvas.showText( "" + facture.getTotal() * 1.2 + " MAD" );
			
			
			canvas.endText();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
