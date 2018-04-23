package service;

import java.util.List;

import data.Assignment;
import data.Class;
import data.ClassDAO;
import data.SQLClassDAO;


public class ClassService implements ClassServiceInterface {

	private List<Class> classes;
	private ClassDAO classDAO;
	//private AssignmentDAO assignmentDAO;
	
	public ClassService() {
		classDAO = new SQLClassDAO();
		//assignmentDAO = new SQLAssignmentDAO();
	}

	@Override
	public List<Class> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse saveClass(Class c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse deleteClass(Class c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse transferAssignmentsToClass(List<Assignment> assignments, Class c) {
		// TODO Auto-generated method stub
		return null;
	}
}
