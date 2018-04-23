package data;

import java.util.List;

public interface AssignmentDAO {
	public Assignment findAssignmentByName(String assignmentName);
	public List<Assignment> findAssignmentsByClassName(String className);
	public boolean saveAssignment(Assignment assignment);
	public boolean updateAssignment(Assignment assignment);
	public boolean deleteAssignment(Assignment assignment);
}
