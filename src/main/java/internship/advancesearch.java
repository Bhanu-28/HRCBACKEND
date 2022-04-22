package internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * Servlet implementation class SearchInvoice
 */
@WebServlet("/advancesearch")
public class advancesearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public advancesearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int rowCount = 12;*/
		
		try {
			Connection con = Connect.getConnection();
			
			String doc_id = request.getParameter("doc_id");
			int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
			int cust_number = Integer.parseInt(request.getParameter("cust_number"));
			int buisness_year = Integer.parseInt(request.getParameter("buisness_year"));

			
			Statement st = con.createStatement();
			String sql_statement = "SELECT * FROM winter_internship WHERE doc_id = "+ doc_id + " AND invoice_id = "+ "'" + invoice_id + "'" + " AND cust_number = " + cust_number + " AND buisness_year = " + buisness_year;// + "%' LIMIT " + page +"," + rowCount;
			ResultSet rs = st.executeQuery(sql_statement);

			ArrayList<POJO> data = new ArrayList<>();
			while(rs.next()) {
				POJO srh = new POJO();
				srh.setSl_no(rs.getInt("sl_no"));
				srh.setBusiness_code(rs.getString("business_code"));
				srh.setCust_number(rs.getInt("cust_number"));
				srh.setClear_date(rs.getString("clear_date"));
				srh.setBuisness_year(rs.getInt("buisness_year"));
				srh.setDoc_id(rs.getString("doc_id"));
				srh.setPosting_date(rs.getString("posting_date"));
				srh.setDocument_create_date(rs.getString("document_create_date"));
				srh.setDocument_create_date1(rs.getString("document_create_date1"));
				srh.setDue_in_date(rs.getString("due_in_date"));
				srh.setInvoice_currency(rs.getString("invoice_currency"));
				srh.setDocument_type(rs.getString("document_type"));
				srh.setPosting_id(rs.getInt("posting_id"));
				srh.setArea_business(rs.getInt("area_business"));
				srh.setTotal_open_amount(rs.getDouble("total_open_amount"));
				srh.setBaseline_create_date(rs.getString("baseline_create_date"));
				srh.setCust_payment_terms(rs.getString("cust_payment_terms"));
				srh.setInvoice_id(rs.getInt("invoice_id"));
				srh.setIsOpen(rs.getInt("isOpen"));
				srh.setAging_bucket(rs.getString("aging_bucket"));
				srh.setIs_deleted(rs.getInt("is_deleted"));
				data.add(srh);
			}
			
			/*Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices = gson.toJson(data);
			out.print(invoices);
			response.setStatus(200);
			out.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}*/
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices  = gson.toJson(data);
			response.setContentType("application/json");
			try {
				response.getWriter().write(invoices);//getWriter() returns a PrintWriter object that can send character text to the client. 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			rs.close();
			st.close();
			con.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
