package bd;

import java.util.List;

public interface IDAO<T> {
	public List<T> getList();
	public void save(T t);
	public void delete(T t);
	public void modify(T t);
}
