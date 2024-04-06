import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
private static Connection connection;
private static DatabaseConnection DBC;
private String serverUrl = "jdbc:mysql://localhost:3306/*Insert DATABASENAMEHERE*"; //alter with accurate info
/**
 * This is the username to access the sql database. CHANGE TO LOCAL VALUE BEFORE RUNNING PROGRAM. *changed to *** for submission to protect my data.
 */
private String userString = "***"; //alter with proper username
/**
 * This is the password to access the sql database. CHANGE TO LOCAL VALUE BEFORE RUNNING PROGRAM. *changed to *** for submission to protect my data.
 */
private String passwordString = "***"; //alter with proper password
/**
 * This private constructor ensures the singleton format and allows only this class to create its own instance. Upon creating it establishes its connection to the database.
 */

private DatabaseConnection() {
	if(connection == null) {
		try {
			connection = DriverManager.getConnection(serverUrl,userString,passwordString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
public static DatabaseConnection useDBC() {
	if(DBC == null) {
		DBC = new DatabaseConnection();
	}
	return DBC;
}
public Connection useConnection() {
	return connection;
}
}
