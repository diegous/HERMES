package bd;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Notification {
	int id;
	private Child child;
	private Context context;
	private Category category;
	private Date sent, recieved;
	private Tag tag;
	private Pictogram pictogram;
	
	public Notification(int id_notification, int id_child, int id_context, int id_category, int id_tag, int id_pictogram, long sent, long recieved) throws SQLException, ParseException {
		this.id = id_notification;
		
		this.child     = new ChildDAO().getById(id_child);
		this.context   = new ContextDAO().getById(id_context);
		this.category  = new CategoryDAO().getById(id_category);
		this.tag       = new TagDAO().getById(id_tag);
		this.pictogram = new PictogramDAO().getById(id_pictogram);

		this.sent = new Date(sent);
		this.recieved = new Date(recieved);
	}
	
	
	public Notification(ResultSet result) throws SQLException, ParseException {
		this(
			result.getInt("id_notification"),
	
			result.getInt("id_child"),
			result.getInt("id_context"),
			result.getInt("id_category"),
			result.getInt("id_tag"),
			result.getInt("id_pictogram"),
			result.getLong("id_sent"),
			result.getLong("id_recieved")
		);
	}
	
	
	public Notification(Child child, Context context, Category category, Tag tag, Pictogram pictogram, Date sent, Date recieved){
		this.child = child;
		this.context = context;
		this.category = category;
		this.sent = sent;
		this.recieved = recieved;
		this.tag = tag;
		this.pictogram = pictogram;
		
		this.sent = sent;
		this.recieved = recieved;
	}
	
	
	public Notification(int id, Child child, Context context, Category category, Tag tag, Pictogram pictogram, Date sent, Date recieved){
		this(child, context, category, tag, pictogram, sent, recieved);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", child=" + child + ", context="
				+ context + ", category=" + category + ", sent=" + sent
				+ ", recieved=" + recieved + ", tag=" + tag + ", content="
				+ pictogram + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Child getChild() {
		return child;
	}


	public void setChild(Child child) {
		this.child = child;
	}


	public Context getContext() {
		return context;
	}


	public void setContext(Context context) {
		this.context = context;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Date getSent() {
		return sent;
	}


	public void setSent(Date sent) {
		this.sent = sent;
	}


	public Date getRecieved() {
		return recieved;
	}


	public void setRecieved(Date recieved) {
		this.recieved = recieved;
	}


	public Tag getTag() {
		return tag;
	}


	public void setTag(Tag tag) {
		this.tag = tag;
	}


	public Pictogram getPictogram() {
		return pictogram;
	}


	public void setPictogram(Pictogram pictogram) {
		this.pictogram = pictogram;
	}
	
	
}