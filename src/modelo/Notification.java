package modelo;

import java.util.Date;

public class Notification {
	
	int id;
	private Child child;
	private Context context;
	private Category category;
	private Date sent, recieved;
	private String tag;
	private Pictogram pictogram;
	
	public Notification(int id, Child child, Context context, Category category, String tag, Pictogram pictogram, Date sent, Date recieved){
		this.id = id;
		this.child = child;
		this.context = context;
		this.category = category;
		this.sent = sent;
		this.recieved = recieved;
		this.tag = tag;
		this.pictogram = pictogram;
	}
	
	
	@Override
	public String toString() {
		return "Notification [id=" + id + ", child=" + child + ", context="
				+ context + ", category=" + category + ", sent=" + sent
				+ ", recieved=" + recieved + ", tag=" + tag + ", content="
				+ pictogram + "]";
	}


	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public Child getChild() {return child;}

	public void setChild(Child child) {this.child = child;}

	public Context getContext() {return context;}

	public void setContext(Context context) {this.context = context;}

	public Category getCategory() {return category;}

	public void setCategory(Category category) {this.category = category;}

	public Date getSent() {return sent;}

	public void setSent(Date sent) {this.sent = sent;}

	public Date getRecieved() {return recieved;}

	public void setRecieved(Date recieved) {this.recieved = recieved;}

	public String getTag() {return tag;}

	public void setTag(String tag) {this.tag = tag;}

	public Pictogram getPictogram() {return pictogram;}

	public void setPictogram(Pictogram pictogram) {this.pictogram = pictogram;}

}
