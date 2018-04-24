package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import data.Assignment;
import data.Class;

public class SelectedAssignmentsTableModel extends AbstractTableModel implements ObservantTableModel<List<Assignment>>{

	
	private static final long serialVersionUID = 1L;
	private static final String[] assignmentFields = {"Class", "Assignment", "Type", "Points Received", "Possible Points", "Score", "Included"};
	
	private List<Assignment> selectedAssignments = new ArrayList<Assignment>();
	
	private void setSelectedAssignments(List<Assignment> selectedAssignments) {
		this.selectedAssignments = selectedAssignments;
		this.fireTableDataChanged();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg != null) {
			setSelectedAssignments(((Class)arg).getAssignments());
		}
		else {
			setSelectedAssignments(new ArrayList<Assignment>());
		}
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumnCount() {
		return assignmentFields.length;
	}

	@Override
	public String getColumnName(int col) {
		return assignmentFields[col];
	}

	@Override
	public int getRowCount() {
		return selectedAssignments.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		String field = assignmentFields[colIndex];
		if(field == "Class") {
			return selectedAssignments.get(rowIndex).getClassName();
		}
		if(field == "Assignment") {
			return selectedAssignments.get(rowIndex).getName();
		}
		if(field == "Type") {
			return selectedAssignments.get(rowIndex).getType();
		}
		if(field == "Points Received") {
			return selectedAssignments.get(rowIndex).getPointsReceived();
		}
		if(field == "Possible Points") {
			return selectedAssignments.get(rowIndex).getPossiblePoints();
		}
		if(field == "Score") {
			return selectedAssignments.get(rowIndex).getScore();
		}
		if(field == "Included") {
			return selectedAssignments.get(rowIndex).isIncluded();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Assignment> getObservedValue() {
		return new ArrayList<Assignment>(selectedAssignments);
	}

}
