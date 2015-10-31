package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Context {
	private int id;
	private String description;
	
	public Context(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_context");
		this.description = result.getString("description");
	}
	
	public Context(String text) throws SQLException, ParseException {
		this.description = text;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
