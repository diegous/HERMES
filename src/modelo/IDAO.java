package modelo;

import java.util.List;

public interface IDAO<T> {
	public List<T> getList();
	public T getByText(String text);
	public void save(T t);
	public void delete(T selectDelete);
	public void modify(String selectModify, String string);
	public String getByID(int int1);
}
