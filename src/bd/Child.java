package bd;

import java.util.Date;

public class Child {
	private int id;
	private String name, dni;
	private Date birthday;
	
	public Child(int id, String name, String dni, Date birthday){
		this.id = id;
		this.name = name;
		this.dni = dni;
		this.birthday = birthday;
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
