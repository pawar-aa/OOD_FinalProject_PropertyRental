package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public static Connection connectDb() {
		try {
			//	Cj used for MySQL 8
			Class.forName("com.mysql.cj.jdbc.Driver");
			// NOTE!! MAKE YOUR OWN DATABASE
			// root is the default username while "" or empty is for the password
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/PrestigeProperties", "root", "12345678");
			return connect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}