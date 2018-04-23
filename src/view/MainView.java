package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
	    
	    ListModel<Class> classListModel = lm; 
		
		frame = new JFrame();
		frame.setTitle("Grade Calculation Tool");
		frame.setBounds(x, y, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JPanel window = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JList<Class> classList = new JList<>(classListModel);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setBackground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(classList, c);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		window.add(table, c);
		
		
		JButton button = new JButton("I am a button");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		window.add(button, c);
		
		JButton button2 = new JButton("I am a button2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		window.add(button2, c);
		
		window.setBackground(Color.GRAY);
		frame.setContentPane(window);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}
