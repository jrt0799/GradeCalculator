package service;

import java.util.List;

import data.Assignment;
import data.Class;

public interface ClassServiceInterface {
	public List<Class> getClasses();
	public ServiceResponse saveClass(Class c);
	public ServiceResponse deleteClass(Class c);
	
	// this doesn't belong here
	//public ServiceResponse saveAssignment(Assignment assignment);
}
