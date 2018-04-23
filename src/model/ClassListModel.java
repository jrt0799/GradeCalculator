package model;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import data.Class;
import service.ClassServiceInterface;

public class ClassListModel extends AbstractListModel<Class> implements Observer{

	private static final long serialVersionUID = 1L;
	
	private ClassServiceInterface classService;
	
	public ClassListModel(ClassServiceInterface csi) {
		classService = csi;
	}
	
	@Override
	public Class getElementAt(int index) {
		return classService.getClasses().get(index);
	}

	@Override
	public int getSize() {
		return classService.getClasses().size();
	}

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Integer> changes = (Map<String, Integer>) arg;
		if(changes.containsKey("new")) {
			fireIntervalAdded(this, changes.get("new"), changes.get("new"));
		}
		if(changes.containsKey("remove")) {
			fireIntervalRemoved(this, changes.get("remove"), changes.get("remove"));
		}
	}

}
