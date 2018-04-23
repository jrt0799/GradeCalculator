package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;

public class SQLClassDAO implements ClassDAO{

	@Override
	public Class getClassByName(String name) {
		return null;
	}

	@Override
	public List<Class> getAllClasses() {
		List<Class> classes = new ArrayList<Class>();
		try {
			Connection sqlConnection = ConnectionFactory.getInstance().getConnection();
			Statement statement = sqlConnection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM classes");
			Class c;
			while(results.next()) {
				c = new Class();
				c.setName(results.getString("name_id"));
				c.setInstructor(results.getString("instructor"));
				c.setGrade(results.getDouble("grade"));
				c.setGpa(results.getDouble("gpa"));
				classes.add(c);
			}
			
			statement.close();
			sqlConnection.close();
		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Data could not be retrieved");
			e.printStackTrace();
		}
		return classes;
	}

	@Override
	public boolean saveClass(Class c) {
		try {
			Connection sqlConnection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO classes VALUES (?, ?, ?, ?)");
			statement.setString(1, c.getName());
			statement.setString(2, c.getInstructor());
			statement.setDouble(3, c.getGrade());
			statement.setDouble(4, c.getGpa());
			statement.executeUpdate();
			
			statement.close();
			sqlConnection.close();
		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Could not save the class");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteClass(Class c) {
		try {
			Connection sqlConnection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM classes WHERE name_id = ?");
			statement.setString(1, c.getName());
			statement.executeUpdate();

			statement.close();
			sqlConnection.close();
		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Could not remove the class");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void createClassesTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE CLASSES(" + "NAME_ID VARCHAR(255) NOT NULL, "
				+ "INSTRUCTOR VARCHAR(255) NOT NULL, " + "GRADE FLOAT NOT NULL, " 
				+ "GPA FLOAT NOT NULL)";
		try {
			dbConnection = ConnectionFactory.getInstance().getConnection();
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table \"CLASSES\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

}
