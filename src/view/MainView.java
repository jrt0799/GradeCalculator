package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainView {
	
	private final int WIDTH = 1000;
	private final int HEIGHT = 600;
	
	private JFrame frame;
	private JTable table;
	
	
	public MainView(){
		initialize();
	}
	
	private void initialize() {
		// Get the location for the center of the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - WIDTH) / 2);
	    int y = (int) ((dimension.getHeight() - HEIGHT) / 2);
		
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
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		c.gridx = 4;
		c.gridy = 0;
		window.add(table, c);
		
		
		JButton button = new JButton("I am a button");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(button, c);
		
		JButton button2 = new JButton("I am a button2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		window.add(button2, c);
		
		window.setBackground(Color.GRAY);
		frame.setContentPane(window);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}
