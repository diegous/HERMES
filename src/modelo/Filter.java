package modelo;

public class Filter {
	private String child;
	private String context;
	private String category;
	private String since, until;
	private String tag;
	private String pictogram;
	
	public Filter(){
		this.child=" ";
		this.context=" ";
		this.category=" ";
		this.since=" "; 
		this.until=" ";
		this.tag=" ";
		this.pictogram=" ";
	}
	
	public String getChild() {return child;}
	
	public void setChild(String child) {this.child = child;}
	
	public String getContext() {return context;}
	
	public void setContext(String context) {this.context = context;}
	
	public String getCategory() {return category;}
	
	public void setCategory(String category) {this.category = category;}
	
	public String getSince() {return since;}
	
	public void setSince(String since) {this.since = since;}
	
	public String getUntil() {return until;}
	
	public void setUntil(String until) {this.until = until;}
	
	public String getTag() {return tag;}
	
	public void setTag(String tag) {this.tag = tag;}
	
	public String getPictogram() {return pictogram;}
	
	public void setPictogram(String pictogram) {this.pictogram = pictogram;}
	
	public void reset(){
		setCategory("");
		setChild("");
		setContext("");
		setPictogram("");
		setTag("");
		setSince("");
		setUntil("");
	}
	
	

}
