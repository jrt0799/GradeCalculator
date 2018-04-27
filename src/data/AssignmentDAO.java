package data;

import java.util.List;

public interface AssignmentDAO {
	/**
	 * Finds an assignment based on the assignment's name
	 * @author titzman
	 * @param assignmentName The name of the assignment to find
	 * @return The Assignment that was found
	 */
	public Assignment findAssignmentByName(String assignmentName);
	/**
	 * Finds all the assigments from a specific class
	 * @author titzman
	 * @param className The name of the class to find assignments from
	 * @return A List of the assignments from the class
	 */
	public List<Assignment> findAssignmentsByClassName(String className);
	/**
	 * saves a new assignment
	 * @author titzman
	 * @param assignment The assignment to save
	 * @return true if save was successful
	 */
	public boolean saveAssignment(Assignment assignment);
	/**
	 * updates an assignment
	 * @author titzman
	 * @param assignment The assignment to update
	 * @return true if update was successful
	 */
	public boolean updateAssignment(Assignment assignment);
	/**
	 * deletes an assignment
	 * @author titzman
	 * @param assignment The assignment to delete
	 * @return true if deletion was successful
	 */
	public boolean deleteAssignment(Assignment assignment);
}
