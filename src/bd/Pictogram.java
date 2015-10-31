package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Pictogram {
	private int id;
	private String content;
	
	public Pictogram(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_context");
		this.content = result.getString("content");
	}
	
	public Pictogram(String text) throws SQLException, ParseException {
		this.content = text;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
