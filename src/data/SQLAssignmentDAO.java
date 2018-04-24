package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;

public class SQLAssignmentDAO implements AssignmentDAO{

	@Override
	public Assignment findAssignmentByName(String assignmentName) {
		return null;
	}

	@Override
	public List<Assignment> findAssignmentsByClassName(String className) {
		List<Assignment> assignments = new ArrayList<>();
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement("Select * FROM assignments WHERE class_id = ?");
			ps.setString(1, className);
			ResultSet results = ps.executeQuery();
			
			while(results.next()) {
				assignments.add(assignmentFromResultSet(results));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assignments;
	}
	
	private Assignment assignmentFromResultSet(ResultSet result) throws SQLException {
		Assignment assignment = new Assignment();
		assignment.setClassName(result.getString("class_id"));
		assignment.setName(result.getString("name"));
		assignment.setType(result.getString("type"));
		assignment.setPointsReceived(result.getDouble("points_received"));
		assignment.setPossiblePoints(result.getDouble("possible_points"));
		assignment.setScore(result.getDouble("score"));
		assignment.setIncluded(result.getBoolean("included"));
		return assignment;
	}

	@Override
	public boolean saveAssignment(Assignment assignment) {
		return false;
	}

	@Override
	public boolean updateAssignment(Assignment assignment) {
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE assignments SET type = ?, points_received = ?, possible_points = ?, score = ?, included = ? WHERE class_id = ? AND name = ?");
			ps.setString(1, assignment.getType());
			ps.setDouble(2, assignment.getPointsReceived());
			ps.setDouble(3, assignment.getPossiblePoints());
			ps.setDouble(4, assignment.getScore());
			ps.setBoolean(5, assignment.isIncluded());
			ps.setString(6, assignment.getClassName());
			ps.setString(7, assignment.getName());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAssignment(Assignment assignment) {
		return false;
	}

}
