package modelo;

import java.util.List;

public class MonitorInformation {
	
	private List<Child> child;
	private List<Context> context;
	private List<Category> category;
	private List<Tag> tag;
	private List<Pictogram> pictogram;
	private List<Notification> notification;
	private Filter filter;
	private String selectDelete;
	//private String selectModify;

	
	public MonitorInformation(List<Child> child, List<Context> context, List<Category> category, List<Tag> tag, List<Pictogram> content, List<Notification> notification, Filter filter){
		this.selectDelete=null;
		this.child=child;
		this.context=context;
		this.category=category;
		this.tag=tag;
		this.pictogram=content;
		this.notification=notification;
		this.filter=filter;
	}
	
	
	public String getSelectDelete() {return selectDelete;}
	
	public void setSelectDelete(String selectDelete) {this.selectDelete = selectDelete;}

	public List<Child> getChild() {return child;}
	
	public void addChild(Child child) {this.child.add(child);}
	
	public List<Context> getContext() {return context;}
	
	public void addContext(Context context) {this.context.add(context);}
	
	public List<Category> getCategory() {return category;}
	
	public void addCategory(Category category) {this.category.add(category);}
	
	public List<Tag> getTag() {return tag;}
	
	public void addTag(Tag tag) {this.tag.add(tag);}
	
	public List<Pictogram> getPictogram() {return pictogram;}
	
	public void addPictogram(Pictogram pictogram) {this.pictogram.add(pictogram);}
	
	public List<Notification> getNotification() {return notification;}
	
	public void addNotification(Notification notification) {this.notification.add(notification);}
	
	public void setNotification(List<Notification> notification){this.notification = notification;}
	
	
	public Filter getFilter() {return filter;}
	
	public void filtar(){
		this.setNotification(FactoriaDAO.getNotificationDAO().getList(this.getFilter()));
		//this.filter.reset();
	}

	public void deleteTag(String string) {
		int i=0;
		boolean encontre=false;
		while(!encontre){
			if(this.tag.get(i).getDescription()==string){
				this.tag.remove(i);
				encontre=true;
			}
		}
		
	}
}
