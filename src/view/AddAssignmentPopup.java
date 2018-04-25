package view;

import data.Assignment;
import data.Class;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddAssignmentPopup extends JFrame{
	private Assignment assignmentInQuestion;
	
	public AddAssignmentPopup(Class c) {
		final AddAssignmentPopup window = this;
		
		//Make an assignment
		assignmentInQuestion = new Assignment();
		
		setTitle("Add new Assignment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		GridLayout grid = new GridLayout(7,2);
		
		JTextField assignmentName = new JTextField();
		JComboBox type = new JComboBox();
		for(String t : c.getAssignmentTypes().keySet()) {
			type.addItem(t + " (" + c.getAssignmentTypes().get(t) + "%)");
		}
		JTextField pointsReceived = new JTextField();
		JTextField possiblePoints = new JTextField();
		JCheckBox included = new JCheckBox();
		JButton cancel = new JButton("Cancel");
		JButton addAssignment = new JButton("Add Assignment");
		
		add(new JLabel("Class:"));
		add(new JLabel(c.getName()));
		
		add(new JLabel("Assignment:"));
		add(assignmentName);
		
		add(new JLabel("Type:"));
		add(type);
		
		add(new JLabel("Points Received:"));
		add(pointsReceived);
		
		add(new JLabel("Possible Points:"));
		add(possiblePoints);
		
		add(new JLabel("Included:"));
		add(included);
		add(cancel);
		add(addAssignment);
		
		setLayout(grid);
	}
}
