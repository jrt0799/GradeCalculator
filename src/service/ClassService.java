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
	
	public ClassService() {
		classDAO = new SQLClassDAO();
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
	public ServiceResponse updateClass(Class c) {
		// Update the class
		if (classDAO.updateClass(c)) {

			// Find where the class was in the list
			int positionUpdated = classes.indexOf(c);
			
			// Update the list that service provides
			updateClassList();

			// Let everyone know that there is a new class
			setChanged();

			Map<String, Integer> changes = new HashMap<>();
			changes.put("update", positionUpdated);

			notifyObservers(changes);

			return new ServiceResponse(true, "Update successful");
		}
		return new ServiceResponse(false, "Update Failed");
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
