package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class Child{
	private int id;
	private String name, lastname, dni;
	private Date birthday;

	public Child(String name, String lastname, String dni, Date birthday) {
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public Child(int id, String name, String lastname, String dni, Date birthday) {
		this(name, lastname, dni, birthday);
		this.id = id;
	}

	public Child(ResultSet result) throws SQLException, ParseException {
		this.id = result.getInt("id_child");
		this.dni = result.getString("dni");
		this.name = result.getString("name");
		this.lastname = result.getString("lastname");
		
		System.out.println(result.getString("birthday"));
		this.birthday = new Date(result.getLong("birthday"));
	}

	@Override
	public String toString() {
		return "Child [id="     + id 
				  + ", name="     + name 
				  + ", lastname=" + lastname
				  + ", dni="      + dni 
				  + ", birthday=" + birthday 
				  + "]";
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
