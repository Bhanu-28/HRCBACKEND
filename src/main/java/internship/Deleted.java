package internship;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteInvoice
 */
@WebServlet("/Deleted")
public class Deleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deleted() {
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
		try {
			
			Connection con = Connect.getConnection();
//			Statement st = con.createStatement();
			
			String fieldValue = request.getParameter("sl_no");
			int field = Integer.parseInt(fieldValue);
			String query = "DELETE FROM winter_internship WHERE sl_no = ?";
			

				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, field);
				stmt.executeUpdate();
				con.close();
			
	}
		catch(Exception e) {
			
		}

}
}