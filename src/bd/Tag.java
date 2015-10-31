package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Tag {
	private int id;
	private String description;
	
	public Tag(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_tag");
		this.description = result.getString("description");
	}
	
	public Tag(String text) throws SQLException, ParseException {
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
