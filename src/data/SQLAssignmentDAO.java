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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		return false;
	}

}
