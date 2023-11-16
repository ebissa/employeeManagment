import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewEmployeesServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException{
		PrintWriter out = res.getWriter();
		
		DBconfig config = new DBconfig();
		try {
			Connection connection = config.returnConnection();
			Statement statement = connection.createStatement();
			String getAllEmployeesSQL = "select * from employees";
			ResultSet result = statement.executeQuery(getAllEmployeesSQL);
			int count = 0;
			while(result.next()) {
				count ++;
			}
			
			
			ResultSet result2 = statement.executeQuery(getAllEmployeesSQL);
			out.println("<html><body>");
			if(count != 0) {
				out.println("<h2>Employee List</h2>");
				
			}
			if(count == 0) {
				out.println("<div><h3>Opps, No employee record found.</h2><a href='/EmployeeManagement/add'>Click to add</a> </div>");
			}
			out.println("<ul>");
			while (result2.next()) {
				String deletePath = "/EmployeeManagement/delete?id=" +  result2.getInt("id"); 
				String updatePath = "/EmployeeManagement/update?id=" + result2.getInt("id");
				out.println("<li>" +  "<div style='display:flex; gap:10px;'><div>"+ result2.getString("name") +"</div>" + "<a href='" + deletePath  + "'>Delete Employee</a>"+"</div>" +
						"<div style='margin: 4px 16px;'>" + 
						"<section>Designation: "+ result2.getString("designation") +"</section>"+
						"<section>Salary: "+ result2.getString("salary") + "</section>" +
						"<section><a href='"+ updatePath +"'>Edit</a></section>" + "</div>" + "</li>");
			}
			
			out.println("</ul>");
			
			if(count != 0) {
				
				out.println("<h4><a href='/EmployeeManagement/add'>Add</a></h4>");
			}
			out.println("</body></html>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
