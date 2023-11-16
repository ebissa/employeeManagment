import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DBconfig config = new DBconfig();
		Connection connection;
		try {
			connection = config.returnConnection();
			Statement statement = connection.createStatement();
			String id = req.getParameter("id");
			String deleteEmployeeSQL = "delete from employees where id=" +  id;
			int result = statement.executeUpdate(deleteEmployeeSQL);
			System.out.println(result);

			res.sendRedirect("/EmployeeManagement/view");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
