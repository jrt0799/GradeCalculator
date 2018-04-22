package runner;

import model.ClassListModel;
import view.MainView;

public class MainViewRunner {
	
	public static void main(String[] args) {
		ClassListModel listModel = new ClassListModel();
		MainView mv = new MainView(listModel);
		mv.setVisible(true);
	}
}
