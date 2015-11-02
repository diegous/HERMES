package modelo;

public class Child{
	private int id;
	private String name;

	public Child(int id, String name) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {return "Child [id="+id+", name="+name+"]";}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}
}
