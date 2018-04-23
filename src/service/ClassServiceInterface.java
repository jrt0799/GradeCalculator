package service;

import java.util.List;

import data.Assignment;
import data.Class;

public interface ClassServiceInterface {
	public List<Class> getClasses();
	public ServiceResponse saveClass(Class c);
	public ServiceResponse deleteClass(Class c);
	public ServiceResponse transferAssignmentsToClass(List<Assignment> assignments, Class c);
}
