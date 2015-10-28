package bd;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IDAO<T> {
	public List<T> getList() throws SQLException, ParseException;
	public T getById(int id) throws SQLException, ParseException;
	public Boolean save(T t);
	public Boolean delete(T t);
	public Boolean modify(T t);
}
