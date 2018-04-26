package data;

import java.util.List;

public interface ClassDAO {
	public Class getClassByName(String name);
	public List<data.Class> getAllClasses();
	public boolean saveClass(Class c);
	public boolean updateClass(Class c);
	public boolean deleteClass(Class c);
}
