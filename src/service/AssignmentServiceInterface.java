package service;

import java.util.List;

import data.Assignment;
import data.Class;

public interface AssignmentServiceInterface {
	/**
	 * gets all the assignments from a class
	 * @author titzman
	 * @param c the class to get assignments from
	 * @return list of assignments from class
	 */
	public List<Assignment> getAssignments(Class c);
	/**
	 * saves the assignment to the given class
	 * @author titzman
	 * @param assignment the assignment to save
	 * @param c the class to save the assignment to
	 * @return a serviceresponse for saving assignments
	 */
	public ServiceResponse saveAssignment(Assignment assignment, Class c);
	/**
	 * delete the assignment from the given class
	 * @author titzman
	 * @param assignment the assignment to delete
	 * @param c the class to delete the assignment from
	 * @return a serviceresponse for deleting assignments
	 */
	public ServiceResponse deleteAssignment(Assignment assignment, Class c);
}
