import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {
	public Connection returnConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/project1";
		String username = "root"; 
		String password = "password"; 
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Established Connection");
		return connection;
		
	}
}
