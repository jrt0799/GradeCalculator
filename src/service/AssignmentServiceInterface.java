package service;

import java.util.List;

import data.Assignment;
import data.Class;

public interface AssignmentServiceInterface {
	public List<Assignment> getAssignments(Class c);
	public ServiceResponse saveAssignment(Assignment assignment, Class c);
	public ServiceResponse deleteAssignment(Assignment assignment, Class c);
}
