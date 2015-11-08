package modelo;

import java.util.Date;
import java.util.List;

import entities.Notification;

public class Filter {
	private String child;
	private String context;
	private String category;
	private long since, until;
	private String tag;
	private String pictogram;
	
	public Filter(){

		this.child="Todo";
		this.context="Todo";
		this.category="Todo";
		this.since=0; 
		this.until=new Date().getTime();
		this.tag="Todo";
		this.pictogram="Todo";

	}
	
	@Override
	public String toString() {
		return "Filter [child=" + child + ", context=" + context + ", category=" + category + ", since=" + since
				+ ", until=" + until + ", tag=" + tag + ", pictogram=" + pictogram + "]";
	}

	public String getChild() {return child;}
	
	public void setChild(String child) {this.child = child;}
	
	public String getContext() {return context;}
	
	public void setContext(String context) {this.context = context;}
	
	public String getCategory() {return category;}
	
	public void setCategory(String category) {this.category = category;}
	
	public long getSince() {return since;}
	
	public void setSince(long since) {this.since = since;}
	
	public long getUntil() {return until;}
	
	public void setUntil(long until) {this.until = until;}
	
	public String getTag() {return tag;}
	
	public void setTag(String tag) {this.tag = tag;}
	
	public String getPictogram() {return pictogram;}
	
	public void setPictogram(String pictogram) {this.pictogram = pictogram;}
	
	public List<Notification> filtar(){
		List<Notification> n = FactoriaDAO.getNotificationDAO().getList(this);
		return n;
	}

	
	

}
