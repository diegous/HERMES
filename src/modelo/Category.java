package modelo;

public class Category {

	private int id;
	private String description;
	
	public Category(int id, String description){
		this.description = description;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return description;
	}

	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	public String getDescription() {return description;}
	
	public void setDescription(String description) {this.description = description;}

}
