package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import data.Assignment;
import data.Class;
import service.ClassService;
import service.ClassServiceInterface;
import service.ServiceResponse;

public class AddClassPopUp extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField instructorField;
	private JTextField typesField;
	private Class classInQuestion;

	
	
	public void setClassInQuestion(Class classInQuestion) {
		this.classInQuestion = classInQuestion;
	}

	/**
	 * Create the frame.
	 */
	public AddClassPopUp(final ClassServiceInterface csi) {
		
		// Retain reference to this under different name
		final AddClassPopUp window = this;
		
		// Make a class
		classInQuestion = new Class();
		
		setTitle("Add new Class");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(verticalBox, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		verticalBox.add(panel);
		
		JLabel lblName = new JLabel("Name of New Class:");
		panel.add(lblName);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		nameField = new JTextField();
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblInstructor = new JLabel("Class Instructor:");
		panel.add(lblInstructor);
		
		JSeparator separator2 = new JSeparator();
		panel.add(separator2);
		
		instructorField = new JTextField();
		panel.add(instructorField);
		instructorField.setColumns(10);
		
		JLabel lblTypes = new JLabel("Enter all types of assignments and their weights in the following format: (Type, Percentage);");
		panel.add(lblTypes);
		
		JSeparator separator3 = new JSeparator();
		panel.add(separator3);
		
		typesField = new JTextField();
		panel.add(typesField);
		typesField.setColumns(10);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
		horizontalBox.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
			
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		horizontalBox.add(saveBtn);
		
		final JLabel warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		contentPane.add(warningLabel, BorderLayout.NORTH);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// update the name with the current text field values (default grade and GPA values are set)
				String[] typesArr = typesField.getText().split(";");
				Map<String,Double> types = new HashMap<String,Double>();
				for(String type : typesArr) {
					type = type.substring(1, type.length()-1);
					types.put(type.substring(0,type.indexOf(',')), Double.parseDouble(type.substring(type.indexOf(',') + 1)));
				}
				
				classInQuestion.setName(nameField.getText());
				classInQuestion.setInstructor(instructorField.getText());
				classInQuestion.setAssignmentTypes(types);
				classInQuestion.setGrade(100.0);
				classInQuestion.setGpa(4.0);
				classInQuestion.setAssignments(new ArrayList<Assignment>());
				
				// save the new farmer
				ServiceResponse response = csi.saveClass(classInQuestion);
				
				if(response.isSuccess()) {
					// dispose of the window
					window.dispose();
				}
				
				// something went wrong with saving
				warningLabel.setText(response.getMessage());
				
			}
			
		});
	}
}
