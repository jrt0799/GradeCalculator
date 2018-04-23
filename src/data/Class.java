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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public Map<String, Double> getAssignmentTypes() {
		return assignmentTypes;
	}
	public void setAssignmentTypes(Map<String, Double> assignmentTypes) {
		this.assignmentTypes = assignmentTypes;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
}
