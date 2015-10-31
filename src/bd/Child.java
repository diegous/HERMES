package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Child{
	private int id;
	private String name;

	public Child(String name) {
		this.name = name;
	}
	public Child(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public Child(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_child");
		this.name = result.getString("name");
	}

	@Override
	public String toString() {
		return "Child [id="+id+", name="+name+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
