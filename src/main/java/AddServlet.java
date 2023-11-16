import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();		
		out.println("<html><body>");
		out.println("<h3>Employee Registration</h3><br/>");
		out.println("<form action='add' method='post' style='margin: 0px 20px;'>");
		out.println("<div>Enter Name: <input required name='name'/></div><br/>");
		out.println("<div>Enter Designation: <input required name='designation'/></div><br/>");
		out.println("<div>Enter Salary: <input required name='salary'/></div><br/>");
		out.println("<div><input type='submit' value='Add' /></div>");
		out.println("</body></html>");
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String designation = req.getParameter("designation");
		String salary = req.getParameter("salary");
		String createEmployeeSQL = "insert into employees (name, designation, salary) values (?,?,?)";
		try {
			DBconfig config = new DBconfig();
			Connection connection = config.returnConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(createEmployeeSQL);
			preparedStatement.setString(1, name);
            preparedStatement.setString(2, designation);
            preparedStatement.setString(3, salary);
            int affectedRows = preparedStatement.executeUpdate();
			res.sendRedirect("/EmployeeManagement/view");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
