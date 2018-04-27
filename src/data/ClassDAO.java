package data;

import java.util.List;
import data.Class;

public interface ClassDAO {
	/**
	 * finds a class based on the class' name
	 * @author titzman
	 * @param name The name of the class to find
	 * @return The found class
	 */
	public Class getClassByName(String name);
	/**
	 * gets all the classes
	 * @author titzman
	 * @return A List of all the classes
	 */
	public List<Class> getAllClasses();
	/**
	 * Saves a new class
	 * @author titzman
	 * @param c The class to save
	 * @return true if save was successful
	 */
	public boolean saveClass(Class c);
	/**
	 * Updates an existing class
	 * @author titzman
	 * @param c The class to update
	 * @return true if update was successful
	 */
	public boolean updateClass(Class c);
	/**
	 * Deletes an existing class
	 * @author titzman
	 * @param c The class to delete
	 * @return true if deletion was successful
	 */
	public boolean deleteClass(Class c);
}
