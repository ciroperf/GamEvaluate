package gamevaluate.controller.gestioneGiochi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import gamevaluate.bean.Gioco;
import gamevaluate.model.GiocoManager;

/**
 * Servlet implementation class FillModifyGame
 */
@WebServlet("/admin/FillModifyGame")
public class FillModifyGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GiocoManager model = new GiocoManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillModifyGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_gioco = Integer.parseInt(request.getParameter("gioco"));
		PrintWriter out = response.getWriter();
		
		try {

			Gioco gioco = model.doRetrieveByKey(id_gioco);
			if (gioco != null) {
				
				Gson gson = new Gson();
				String jsongiochi = gson.toJson(gioco);
				out.println("{ \"gioco\":");
				out.println(jsongiochi+"}");		
			}

		} catch (SQLException e) {
			
			System.out.println("Errore SQL in VisualizzaGioco.java : "+e.getMessage());
		}
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
