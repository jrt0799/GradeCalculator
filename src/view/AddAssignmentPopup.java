package view;

import data.Assignment;
import data.Class;
import service.ServiceResponse;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
			type.addItem(t);
		}
		
		NumberFormat numbersOnly = NumberFormat.getNumberInstance();
		
		JFormattedTextField pointsReceived = new JFormattedTextField(numbersOnly);
		JFormattedTextField possiblePoints = new JFormattedTextField(numbersOnly);
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
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		addAssignment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double received = Double.parseDouble(pointsReceived.getText());
				double possible = Double.parseDouble(possiblePoints.getText());
				double score = (received/possible)*100;
				
				assignmentInQuestion.setName(assignmentName.getText());
				assignmentInQuestion.setClassName(c.getName());
				assignmentInQuestion.setType(type.getSelectedItem().toString());
				assignmentInQuestion.setPointsReceived(received);
				assignmentInQuestion.setPossiblePoints(possible);
				assignmentInQuestion.setScore(score);
				assignmentInQuestion.setIncluded(included.isSelected());
				
				// save the new farmer
				//ServiceResponse response = csi.saveClass(classInQuestion);
				
//				if(response.isSuccess()) {
//					// dispose of the window
//					window.dispose();
//				}
			}
			
		});
		
		setLayout(grid);
	}
}
