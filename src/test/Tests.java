package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Assignment;
import data.AssignmentDAO;
import data.Class;
import data.ClassDAO;
import data.SQLAssignmentDAO;
import data.SQLClassDAO;

class Tests {

	private ClassDAO classDAO;
	private AssignmentDAO assignmentDAO;
	
	@BeforeEach
	void clearData(){
		classDAO = new SQLClassDAO();
		List<Class> classes = classDAO.getAllClasses();
		
		for(Class c : classes) {
			classDAO.deleteClass(c);
		}
	}
	
	@Test
	void testAddClass() {
		classDAO = new SQLClassDAO();
		Class c = new Class();
		c.setName("Test Class");
		c.setInstructor("JUnit");
		c.setGrade(100.0);
		c.setGpa(4.0);
		c.setAssignments(new ArrayList<Assignment>());
		c.setAssignmentTypes(new HashMap<String,Double>());
		
		classDAO.saveClass(c);
		
		List<Class> classes = classDAO.getAllClasses();
		
		List<String> classNames = new ArrayList<String>();
		for(Class cl : classes) {
			classNames.add(cl.getName());
		}
		
		assertTrue(classNames.contains(c.getName()));
	}
	
	@Test
	void testDeleteClass() {
		classDAO = new SQLClassDAO();
		Class c = new Class();
		c.setName("Test Class");
		c.setInstructor("JUnit");
		c.setGrade(100.0);
		c.setGpa(4.0);
		c.setAssignments(new ArrayList<Assignment>());
		c.setAssignmentTypes(new HashMap<String,Double>());
		
		classDAO.saveClass(c);
		
		classDAO.deleteClass(c);
		
		List<Class> classes = classDAO.getAllClasses();
		
		List<String> classNames = new ArrayList<String>();
		for(Class cl : classes) {
			classNames.add(cl.getName());
		}
		
		assertFalse(classNames.contains(c.getName()));
	}
	
	@Test
	void testAddAssignment() {
		classDAO = new SQLClassDAO();
		Class c = new Class();
		c.setName("Test Class");
		c.setInstructor("JUnit");
		c.setGrade(100.0);
		c.setGpa(4.0);
		c.setAssignments(new ArrayList<Assignment>());
		c.setAssignmentTypes(new HashMap<String,Double>());
		
		classDAO.saveClass(c);
		
		assignmentDAO = new SQLAssignmentDAO();
		Assignment a = new Assignment();
		a.setClassName(c.getName());
		a.setName("Test Assignment");
		a.setPointsReceived(10.0);
		a.setPossiblePoints(20.0);
		a.setType("Some type");
		
		assignmentDAO.saveAssignment(a);
		
		List<Assignment> assignments = assignmentDAO.findAssignmentsByClassName(c.getName());
		
		List<String> assignmentNames = new ArrayList<String>();
		for(Assignment as : assignments) {
			assignmentNames.add(as.getName());
		}
		
		assertTrue(assignmentNames.contains(a.getName()));
	}
	
	@Test
	void testDeleteAssignment() {
		classDAO = new SQLClassDAO();
		Class c = new Class();
		c.setName("Test Class");
		c.setInstructor("JUnit");
		c.setGrade(100.0);
		c.setGpa(4.0);
		c.setAssignments(new ArrayList<Assignment>());
		c.setAssignmentTypes(new HashMap<String,Double>());
		
		classDAO.saveClass(c);
		
		assignmentDAO = new SQLAssignmentDAO();
		Assignment a = new Assignment();
		a.setClassName(c.getName());
		a.setName("Test Assignment");
		a.setPointsReceived(10.0);
		a.setPossiblePoints(20.0);
		a.setType("Some type");
		
		assignmentDAO.saveAssignment(a);
		
		assignmentDAO.deleteAssignment(a);
		
		List<Assignment> assignments = assignmentDAO.findAssignmentsByClassName(c.getName());
		
		List<String> assignmentNames = new ArrayList<String>();
		for(Assignment as : assignments) {
			assignmentNames.add(as.getName());
		}
		
		assertFalse(assignmentNames.contains(a.getName()));
	}

}
