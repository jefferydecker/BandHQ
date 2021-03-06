package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Band;
import model.Musician;

/**
 * Servlet implementation class MusicianNavServlet
 */
@WebServlet("/MusicianNavServlet")
public class MusicianNavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicianNavServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("doThisToMember");
		MusicianHelper dao = new MusicianHelper();

		
		if (act == null) {
		 //no button has been selected
		getServletContext().getRequestDispatcher("/ViewAllItemsServlet").forward(request, response);
		} else if (act.equals("delete")) {		
			
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			Musician musicianToDelete = dao.searchForMusicianById(tempId);
			dao.deleteMusician(musicianToDelete);
			getServletContext().getRequestDispatcher("/ViewAllMusiciansServlet").forward(request, response);		
			}
			catch (NumberFormatException e) {
				System.out.println("Forgot to click a button.");
			} 
			finally {				
				getServletContext().getRequestDispatcher("/ViewAllMusiciansServlet").forward(request, response);
				
			}
	
		} else if (act.equals("edit")) {
			
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Musician musicianToEdit = dao.searchForMusicianById(tempId);
				request.setAttribute("musicianToEdit", musicianToEdit);
				getServletContext().getRequestDispatcher("/EditMusician.jsp").forward(request, response);
				 } catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/ViewAllMusiciansServlet").
				forward(request, response);
				 }
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/AddMusician.jsp").forward(request, response);
		}
	}
	}

