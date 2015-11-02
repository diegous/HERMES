package modelo;

import java.util.List;

public interface IDAO<T> {
	public List<T> getList();
	public T getByText(String text);
	public void save(T t);
}
