package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static final String JDBC_URL = "jdbc:derby:GradesDatabase;create=true";
	
	private static ConnectionFactory INSTANCE;
	
	/**
	 * ConnectionFactory constructor
	 * @author titzman
	 * @return instance of ConnectionFactory
	 */
	public static ConnectionFactory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ConnectionFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * gets the connection to the SQL database
	 * @author titzman
	 * @return the database connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}
}
