package data;

import java.util.List;
import java.util.Map;

public class Class {
	private String name;
	private String instructor;
	private double grade;
	private double gpa;
	private Map<String, Double> assignmentTypes;
	private List<Assignment> assignments;
	
	/**
	 * Returns a string representation of the class
	 * @author titzman
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * getter for name
	 * @author titzman
	 * @return Class' name
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for name
	 * @author titzman
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for instructor
	 * @author titzman
	 * @return Class' instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * setter for instructor
	 * @author titzman
	 * @param instructor The new instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * getter for grade
	 * @author titzman
	 * @return Class' grade
	 */
	public double getGrade() {
		return grade;
	}
	/**
	 * setter for grade
	 * @author titzman
	 * @param grade The new grade
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}
	/**
	 * getter for gpa
	 * @author titzman
	 * @return Class' gpa
	 */
	public double getGpa() {
		return gpa;
	}
	/**
	 * setter for gpa
	 * @author titzman
	 * @param gpa The new gpa
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	/**
	 * getter for assignmentTypes
	 * @author titzman
	 * @return Class' assignmentTypes
	 */
	public Map<String, Double> getAssignmentTypes() {
		return assignmentTypes;
	}
	/**
	 * setter for assignmentTypes
	 * @author titzman
	 * @param assignmentTypes The new assignmentTypes
	 */
	public void setAssignmentTypes(Map<String, Double> assignmentTypes) {
		this.assignmentTypes = assignmentTypes;
	}
	/**
	 * getter for assignments
	 * @author titzman
	 * @return Class' assignments
	 */
	public List<Assignment> getAssignments() {
		return assignments;
	}
	/**
	 * setter for assignments
	 * @author titzman
	 * @param assignments The new assignments
	 */
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
}
