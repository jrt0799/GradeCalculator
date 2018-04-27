package model;

import java.util.Observer;

import javax.swing.table.TableModel;

public interface ObservantTableModel<E> extends Observer, TableModel {
	/**
	 * gets The observed value of the ObservantTableModel of type E
	 * @author titzman
	 * @return the observed value E
	 */
	public E getObservedValue();
}
