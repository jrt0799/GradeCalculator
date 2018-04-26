package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.Assignment;
import data.AssignmentDAO;
import data.SQLAssignmentDAO;
import data.Class;

public class AssignmentService extends Observable implements AssignmentServiceInterface {

	private List<Assignment> assignments;
	private AssignmentDAO assignmentDAO;

	public AssignmentService() {
		assignmentDAO = new SQLAssignmentDAO();
	}

	@Override
	public List<Assignment> getAssignments(Class c) {
		if (assignments == null) {
			updateAssignmentList(c);
		}
		return assignments;
	}

	private void updateAssignmentList(Class c) {
		assignments = assignmentDAO.findAssignmentsByClassName(c.getName());
	}

	@Override
	public ServiceResponse saveAssignment(Assignment assignment, Class c) {
		if (assignment.getName().equals("")) {
			return new ServiceResponse(false, "Cannot save assignment with no name!");
		}
		
		// Save the assignment
		if (assignmentDAO.saveAssignment(assignment)) {
			// Update the list that service provides
			updateAssignmentList(c);

			// Let everyone know that there is a new assignment
			setChanged();

			Map<String, Integer> changes = new HashMap<>();
			changes.put("new", assignments.size());

			notifyObservers(changes);
			
			return new ServiceResponse(true, "Save successful");
		}

		

		return new ServiceResponse(false, "Save Failed");
	}

	@Override
	public ServiceResponse deleteAssignment(Assignment assignment, Class c) {
		// Delete the class
		if (assignmentDAO.deleteAssignment(assignment)) {

			// Find where the assignment was in the list
			int positionRemoved = assignments.indexOf(assignment);

			// Update the list that service provides
			updateAssignmentList(c);

			// Let everyone know that there is a new assignment
			setChanged();

			Map<String, Integer> changes = new HashMap<>();
			changes.put("remove", positionRemoved);

			notifyObservers(changes);

			// Return success message
			return new ServiceResponse(true, "Deletion Successful");
		}

		return new ServiceResponse(false, "Deletion Failed");
	}

}
