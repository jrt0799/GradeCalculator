package runner;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import data.Assignment;
import model.ClassListModel;
import model.ObservantTableModel;
import model.SelectedAssignmentsTableModel;
import service.ClassService;
import view.MainView;

public class MainViewRunner {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ClassService cs = new ClassService();
				ClassListModel listModel = new ClassListModel(cs);
				cs.addObserver(listModel);
				ObservantTableModel<List<Assignment>> otm = new SelectedAssignmentsTableModel();
				MainView app = new MainView(listModel, otm, cs);
				app.addObserver(otm);
				app.setVisible(true);
			}
			
		});
	}
}
