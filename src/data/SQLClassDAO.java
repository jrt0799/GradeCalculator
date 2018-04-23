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
				c.setName(results.getString("name"));
				classes.add(c);
			}
		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Data could not be retrieved");
			e.printStackTrace();
		}
		return classes;
	}

	@Override
	public boolean saveClass(Class c) {
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO classes (name) VALUES (?)");
			statement.setString(1, c.getName());
			statement.executeUpdate();

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
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM classes WHERE name_id = ?");
			statement.setString(1, c.getName());
			statement.executeUpdate();

		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Could not remove the class");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
