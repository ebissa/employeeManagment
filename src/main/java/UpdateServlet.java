import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();	
		String retrieveEmployeeSQL = "select * from employees where id=?";
		
		try {
			DBconfig config = new DBconfig();
			Connection connection = config.returnConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(retrieveEmployeeSQL);
			preparedStatement.setString(1, req.getParameter("id"));
//            preparedStatement.setString(2, designation);
//            preparedStatement.setString(3, salary);
            ResultSet result = preparedStatement.executeQuery();
//			res.sendRedirect("/EmployeeManagement/view");
			result.next();
            out.println("<html><body>");
            out.println("<h3>Employee Update</h3><br/>");
            out.println("<form action='update' method='post' style='margin: 0px 20px;'>");
            out.println("<input type='hidden' name='id' value='" + req.getParameter("id") + "'>");

            out.println("<div>Enter Name: <input required name='name' value='" + result.getString("name") +"'/></div><br/>");
            out.println("<div>Enter Designation: <input required name='designation' value='" + result.getString("designation") +"'/></div><br/>");
            out.println("<div>Enter Salary: <input required name='salary' value='" + result.getString("salary") +"' /></div><br/>");
            out.println("<div><input type='submit' value='Save' /></div>");
            out.println("</body></html>");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		
		String updateEmployeeSQL = "update employees set name=? , designation=?, salary=? where id='" + id + "'";
		System.out.println(updateEmployeeSQL);
		String designation = req.getParameter("designation");
		String salary = req.getParameter("salary");

		DBconfig config = new DBconfig();
		Connection connection;
		try {
			connection = config.returnConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeSQL);
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, designation);
			preparedStatement.setString(3, salary);
			
			int result = preparedStatement.executeUpdate();
			System.out.println(result);
			res.sendRedirect("/EmployeeManagement/view");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		res.sendRedirect("/EmployeeManagement/view");
	}

}
