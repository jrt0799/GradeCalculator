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
	private JTable table;	
	
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
		window.setBackground(Color.GRAY);
		GridBagConstraints c = new GridBagConstraints();
		
		JList<Class> classList = new JList<>(classListModel);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setBackground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(classList,c);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		window.add(table, c);
		
		frame.setContentPane(window);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}
