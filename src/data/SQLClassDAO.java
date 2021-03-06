package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM classes");
			ResultSet results = statement.executeQuery();
			Class c;
			while(results.next()) {
				c = new Class();
				
				String className = results.getString("name_id");
				
				Map<String, Double> types = new HashMap<String, Double>();
				PreparedStatement typeStatement = sqlConnection.prepareStatement("SELECT * FROM types WHERE class_id = '" + className + "'");
				ResultSet resultTypes = typeStatement.executeQuery();
				
				while(resultTypes.next()) {
					types.put(resultTypes.getString("type"), resultTypes.getDouble("percentage"));
				}
				
				List<Assignment> assignments = new ArrayList<Assignment>();
				PreparedStatement assignmentStatement = sqlConnection.prepareStatement("SELECT * FROM assignments WHERE class_id = '" + className + "'");
				ResultSet resultAssignments = assignmentStatement.executeQuery();
				
				Assignment assignment;
				while(resultAssignments.next()) {
					assignment = new Assignment();
					assignment.setClassName(resultAssignments.getString("class_id"));
					assignment.setName(resultAssignments.getString("name"));
					assignment.setType(resultAssignments.getString("type"));
					assignment.setPointsReceived(resultAssignments.getDouble("points_received"));
					assignment.setPossiblePoints(resultAssignments.getDouble("possible_points"));
					assignment.setScore(resultAssignments.getDouble("score"));
					assignment.setIncluded(resultAssignments.getBoolean("included"));
					assignments.add(assignment);
				}
				
				c.setName(className);
				c.setInstructor(results.getString("instructor"));
				c.setGrade(results.getDouble("grade"));
				c.setGpa(results.getDouble("gpa"));
				c.setAssignmentTypes(types);
				c.setAssignments(assignments);
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
			
			statement = sqlConnection.prepareStatement("INSERT INTO TYPES VALUES (?, ?, ?)");
			
			Map<String,Double> types = c.getAssignmentTypes();
			for(String type : types.keySet()) {
				statement.setString(1, c.getName());
				statement.setString(2, type);
				statement.setDouble(3, types.get(type));
				statement.executeUpdate();
			}
			
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
	public boolean updateClass(Class c) {
		try {
			
			// Calculate grade
			double grade = 0;
			for(String type : c.getAssignmentTypes().keySet()) {
				double allAssignmentsWithThisType = 0;
				double numIncluded = 0;
				for(Assignment a : c.getAssignments()) {
					if(a.getType().equals(type) && a.isIncluded()) {
						allAssignmentsWithThisType += a.getScore();
						numIncluded++;
					}
				}
				// get the average type grade
				if(numIncluded > 0) {
					allAssignmentsWithThisType /= numIncluded;
				}
				
				// multiply type by its multiplier
				allAssignmentsWithThisType *= c.getAssignmentTypes().get(type)/100;
				
				// add to overall grade
				grade += allAssignmentsWithThisType;
			}
			
			c.setGrade(grade);
			
			
			// Update GPA
			if(c.getGrade() >= 90.0) {
				c.setGpa(4.0);
			} else if (c.getGrade() < 90.0 && c.getGrade() >= 80.0) {
				c.setGpa(3.0);
			} else if (c.getGrade() < 80.0 && c.getGrade() >= 70.0) {
				c.setGpa(2.0);
			} else if (c.getGrade() < 70.0 && c.getGrade() >= 60.0) {
				c.setGpa(1.0);
			} else if (c.getGrade() < 60.0) {
				c.setGpa(0.0);
			}
			
			
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE classes SET instructor = ?, grade = ?, gpa = ? WHERE name_id = ?");
			ps.setString(1, c.getInstructor());
			ps.setDouble(2, c.getGrade());
			ps.setDouble(3, c.getGpa());
			ps.setString(4, c.getName());
			ps.executeUpdate();
			
			ps.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteClass(Class c) {
		try {
			Connection sqlConnection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM classes WHERE name_id = ?");
			statement.setString(1, c.getName());
			statement.executeUpdate();
			
			statement = sqlConnection.prepareStatement("DELETE FROM assignments WHERE class_id = ?");
			statement.setString(1, c.getName());
			statement.executeUpdate();
			
			statement = sqlConnection.prepareStatement("DELETE FROM types WHERE class_id = ?");
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
	
	public static void createTypeTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE TYPES(CLASS_ID VARCHAR(255) NOT NULL, " 
				+ "TYPE VARCHAR(255), " + "PERCENTAGE FLOAT NOT NULL)";
		try {
			dbConnection = ConnectionFactory.getInstance().getConnection();
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table \"TYPES\" is created!");
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
	
	public static void createAssignmentTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE ASSIGNMENTS(CLASS_ID VARCHAR(255) NOT NULL, " 
				+ "NAME VARCHAR(255) NOT NULL, " + "TYPE VARCHAR(255), " 
				+ "POINTS_RECEIVED FLOAT NOT NULL, " + "POSSIBLE_POINTS FLOAT NOT NULL, " 
				+ "SCORE FLOAT NOT NULL, " + "INCLUDED SMALLINT NOT NULL)";
		try {
			dbConnection = ConnectionFactory.getInstance().getConnection();
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table \"ASSIGNMENTS\" is created!");
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
