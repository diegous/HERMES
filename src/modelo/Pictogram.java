package modelo;

public class Pictogram {

	private int id;
	private String content;
	
	public Pictogram(int id, String content){
		this.id =id;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content;
	}

	public Pictogram(String text) {this.content = text;}
	
	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	public String getContent() {return content;}
	
	public void setContent(String content) {this.content = content;}
	
}
