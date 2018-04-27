package runner;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import data.Assignment;
import data.SQLClassDAO;
import model.ClassListModel;
import model.ObservantTableModel;
import model.SelectedAssignmentsTableModel;
import service.AssignmentService;
import service.ClassService;
import view.MainView;

public class MainViewRunner {
	
	/**
	 * Runs the application
	 * @author titzman
	 * @param args Application arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			/**
			 * Set up the application and run it
			 * @author titzman
			 */
			@Override
			public void run() {
				ClassService cs = new ClassService();
				AssignmentService as = new AssignmentService();
				ClassListModel listModel = new ClassListModel(cs);
				cs.addObserver(listModel);
				ObservantTableModel<List<Assignment>> otm = new SelectedAssignmentsTableModel();
				MainView app = new MainView(listModel, otm, cs, as);
				app.addObserver(otm);
				app.setVisible(true);
			}
			
		});
	}
}
