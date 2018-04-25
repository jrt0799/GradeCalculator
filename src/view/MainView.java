package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import data.Assignment;
import data.Class;
import model.ObservantTableModel;
import service.ClassServiceInterface;
import service.ServiceResponse;

public class MainView extends Observable{
	
	private final int WIDTH = 1000;
	private final int HEIGHT = 600;
	
	private JFrame frame;
	private Class selectedClass;
	private JTable assignmentsTable;
	private List<Assignment> clickedAssignments;
	
	private JLabel classNameLabel;
	private JLabel classInstructorLabel;
	private JLabel classGradeLabel;
	private JLabel classGPALabel;
	
	public MainView(ListModel<Class> clm, ObservantTableModel<List<Assignment>> sc, ClassServiceInterface csi) {
		initialize(clm, sc, csi);
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
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.weighty = 0;
		
		final JLabel classListLabel = new JLabel("Classes", JLabel.CENTER);
		classListLabel.setOpaque(true);
		classListLabel.setFont(classListLabel.getFont().deriveFont(20.0f));
		classListLabel.setPreferredSize(new Dimension(300, 26));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(classListLabel, c);
		
		final JLabel cNameLabel = new JLabel("Class: ", JLabel.LEFT);
		cNameLabel.setOpaque(true);
		cNameLabel.setFont(cNameLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		window.add(cNameLabel, c);
		
		classNameLabel = new JLabel("-", JLabel.LEFT);
		classNameLabel.setOpaque(true);
		classNameLabel.setFont(classNameLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		window.add(classNameLabel, c);
		
		final JLabel cInstructorLabel = new JLabel("Instructor: ", JLabel.LEFT);
		cInstructorLabel.setOpaque(true);
		cInstructorLabel.setFont(cInstructorLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		window.add(cInstructorLabel, c);
		
		classInstructorLabel = new JLabel("-", JLabel.LEFT);
		classInstructorLabel.setOpaque(true);
		classInstructorLabel.setFont(classInstructorLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		window.add(classInstructorLabel, c);
		
		final JLabel cGradeLabel = new JLabel("Grade: ", JLabel.LEFT);
		cGradeLabel.setOpaque(true);
		cGradeLabel.setFont(cGradeLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		window.add(cGradeLabel, c);
		
		classGradeLabel = new JLabel("-", JLabel.LEFT);
		classGradeLabel.setOpaque(true);
		classGradeLabel.setFont(classGradeLabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 0;
		window.add(classGradeLabel, c);
		
		final JLabel cGPALabel = new JLabel("GPA: ", JLabel.LEFT);
		cGPALabel.setOpaque(true);
		cGPALabel.setFont(cGPALabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 0;
		window.add(cGPALabel, c);
		
		classGPALabel = new JLabel("-", JLabel.LEFT);
		classGPALabel.setOpaque(true);
		classGPALabel.setFont(classGPALabel.getFont().deriveFont(20.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 0;
		window.add(classGPALabel, c);
		
		JButton addAssignmentButton = new JButton("Add New Assignment");
		addAssignmentButton.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 9;
		c.gridy = 0;
		window.add(addAssignmentButton,c);
		
		addAssignmentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddAssignmentPopup popup = new AddAssignmentPopup(selectedClass);
				popup.setVisible(true);
			}
			
		});
		
		JList<Class> classList = new JList<>(classListModel);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setBackground(Color.WHITE);
		classList.setFont(classList.getFont().deriveFont(16.0f));
		classList.setPreferredSize(new Dimension(300,(int) dimension.getHeight()));
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		window.add(classList,c);
		
		JPopupMenu classRightClickMenu = new JPopupMenu();
		JMenuItem removeSelectedClassMenuItem = new JMenuItem("Remove Selected Class");
		removeSelectedClassMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						ServiceResponse response = csi.deleteClass(selectedClass);
					}
				});
			}
		});
		classRightClickMenu.add(removeSelectedClassMenuItem);
		
		final MainView myMainView = this;
		classList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				selectedClass = (Class) list.getSelectedValue();
				if(!list.isSelectionEmpty() && list.locationToIndex(evt.getPoint()) == list.getSelectedIndex()) {
					// The selected class has changed. Notify anyone who cares.
					myMainView.setChanged();
					myMainView.notifyObservers(selectedClass);
					classNameLabel.setText(selectedClass.getName());
					classInstructorLabel.setText(selectedClass.getInstructor());
					classGradeLabel.setText(Double.toString(selectedClass.getGrade()));
					classGPALabel.setText(Double.toString(selectedClass.getGpa()));
					addAssignmentButton.setEnabled(true);
					
					if(SwingUtilities.isRightMouseButton(evt)) {
						classRightClickMenu.show(list, evt.getX(), evt.getY());
					}
				}
			}
		});
		
		assignmentsTable = new JTable(selectedAssignments);
		//assignmentsTable.setFillsViewportHeight(true);
		assignmentsTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				ObservantTableModel<List<Assignment>> assignmentModel = (ObservantTableModel<List<Assignment>>)table.getModel();
				int[] selected = table.getSelectedRows();
				final List<Assignment> allSelectedAssignments = assignmentModel.getObservedValue();
				clickedAssignments = new ArrayList<Assignment>();
				for(Integer assignmentIndex: selected) {
					clickedAssignments.add(allSelectedAssignments.get(assignmentIndex));
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(assignmentsTable);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		c.gridwidth = 8;
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		
		window.add(scrollPane,c);
		
		frame.setContentPane(window);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}
