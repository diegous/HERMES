package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Category {
	private int id;
	private String description;
	
	public Category(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_category");
		this.description = result.getString("description");
	}
	
	public Category(String text) throws SQLException, ParseException {
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
