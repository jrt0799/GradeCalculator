package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.Assignment;
import data.AssignmentDAO;
import data.Class;
import data.ClassDAO;
import data.SQLAssignmentDAO;
import data.SQLClassDAO;

public class ClassService extends Observable implements ClassServiceInterface {

	private List<Class> classes;
	private ClassDAO classDAO;
	private AssignmentDAO assignmentDAO;
	
	public ClassService() {
		classDAO = new SQLClassDAO();
		assignmentDAO = new SQLAssignmentDAO();
	}

	@Override
	public List<Class> getClasses() {
		if (classes == null) {
			updateClassList();
		}
		return classes;
	}
	
	private void updateClassList() {
		// ClassDAO only knows how to retrieve Classes
		classes = classDAO.getAllClasses();

		// Use a AssignmentDAO to retrieve the class' assignments
		for (Class c : classes) {
			List<Assignment> classAssignments = assignmentDAO.findAssignmentsByClassName(c.getName());
			c.getAssignments().addAll(classAssignments);
		}
	}

	@Override
	public ServiceResponse saveClass(Class c) {
		if (c.getName().equals("")) {
			return new ServiceResponse(false, "Cannot save class with no name!!");
		}
		if (c.getInstructor().equals("")) {
			return new ServiceResponse(false, "Cannot save class with no instructor name!");
		}

		// Save the class
		if (classDAO.saveClass(c)) {

			// Save any assignments that the class may now have
			for (Assignment assignment : c.getAssignments()) {
				assignmentDAO.saveAssignment(assignment);
			}

			// Update the list that service provides
			updateClassList();

			// Let everyone know that there is a new class
			setChanged();

			Map<String, Integer> changes = new HashMap<>();
			changes.put("new", classes.size());

			notifyObservers(changes);

			return new ServiceResponse(true, "Save successful");
		}
		return new ServiceResponse(false, "Save Failed");
	}

	@Override
	public ServiceResponse deleteClass(Class c) {
		// Do JOPtionPane are you sure here OR DONT

		// Delete the class
		if (classDAO.deleteClass(c)) {

			// Find where the class was in the list
			int positionRemoved = classes.indexOf(c);

			// Update the list that service provides
			updateClassList();

			// Let everyone know that there is a new farmer
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
