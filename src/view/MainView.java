package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import data.Assignment;
import data.Class;
import model.ObservantTableModel;
import service.ClassServiceInterface;

public class MainView extends Observable{
	
	private final int WIDTH = 1000;
	private final int HEIGHT = 600;
	
	private JFrame frame;
	JLabel classNameLabel;
	JLabel classInstructorLabel;
	JLabel classGradeLabel;
	JLabel classGPALabel;
	
	public MainView(ListModel<Class> flm, ObservantTableModel<List<Assignment>> sc, ClassServiceInterface csi) {
		initialize(flm, sc, csi);
	}
	
	private void initialize(ListModel<Class> lm, ObservantTableModel<List<Assignment>> otm, final ClassServiceInterface csi) {
		// Get the location for the center of the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - WIDTH) / 2);
	    int y = (int) ((dimension.getHeight() - HEIGHT) / 2);
	    
		// Make a ClassListModel for the list
		ListModel<Class> classListModel = lm;

		// Make a SelectedAssignmentTableModel
		ObservantTableModel<List<Assignment>> selectedAssignments = otm;
		
		frame = new JFrame();
		frame.setTitle("Grade Calculation Tool");
		frame.setBounds(x, y, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem addClassBtn = new JMenuItem("Add New Class");
		addClassBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						AddClassPopUp popup = new AddClassPopUp(csi);
						popup.setVisible(true);
					}
				});
			}
		});
		menu.add(addClassBtn);
		
		JPanel window = new JPanel(new GridBagLayout());
		window.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.weighty = 0.01;
		
		final JLabel classListLabel = new JLabel("Classes", JLabel.LEFT);
		classListLabel.setBackground(Color.WHITE);
		classListLabel.setOpaque(true);
		classListLabel.setFont(classListLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(classListLabel, c);
		
		final JLabel cNameLabel = new JLabel("Class: ", JLabel.RIGHT);
		cNameLabel.setBackground(Color.WHITE);
		cNameLabel.setOpaque(true);
		cNameLabel.setFont(cNameLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		window.add(cNameLabel, c);
		
		classNameLabel = new JLabel("-", JLabel.LEFT);
		classNameLabel.setBackground(Color.WHITE);
		classNameLabel.setOpaque(true);
		classNameLabel.setFont(classNameLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		window.add(classNameLabel, c);
		
		final JLabel cInstructorLabel = new JLabel("Instructor: ", JLabel.RIGHT);
		cInstructorLabel.setBackground(Color.WHITE);
		cInstructorLabel.setOpaque(true);
		cInstructorLabel.setFont(cInstructorLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		window.add(cInstructorLabel, c);
		
		classInstructorLabel = new JLabel("-", JLabel.LEFT);
		classInstructorLabel.setBackground(Color.WHITE);
		classInstructorLabel.setOpaque(true);
		classInstructorLabel.setFont(classInstructorLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		window.add(classInstructorLabel, c);
		
		final JLabel cGradeLabel = new JLabel("Grade: ", JLabel.RIGHT);
		cGradeLabel.setBackground(Color.WHITE);
		cGradeLabel.setOpaque(true);
		cGradeLabel.setFont(cGradeLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		window.add(cGradeLabel, c);
		
		classGradeLabel = new JLabel("-", JLabel.LEFT);
		classGradeLabel.setBackground(Color.WHITE);
		classGradeLabel.setOpaque(true);
		classGradeLabel.setFont(classGradeLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 0;
		window.add(classGradeLabel, c);
		
		final JLabel cGPALabel = new JLabel("Instructor: ", JLabel.RIGHT);
		cGPALabel.setBackground(Color.WHITE);
		cGPALabel.setOpaque(true);
		cGPALabel.setFont(cGPALabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 0;
		window.add(cGPALabel, c);
		
		classGPALabel = new JLabel("-", JLabel.LEFT);
		classGPALabel.setBackground(Color.WHITE);
		classGPALabel.setOpaque(true);
		classGPALabel.setFont(classGPALabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 0;
		window.add(classGPALabel, c);
		
		JList<Class> classList = new JList<>(classListModel);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setBackground(Color.WHITE);
		classList.setFont(classList.getFont().deriveFont(20.0f));
		classList.setPreferredSize(new Dimension(300, HEIGHT));
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		window.add(classList,c);
		
		
		
		frame.setContentPane(window);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}
