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

	

	private String selectAsignar;
	private String selectModify;
	private String selectNotification;
	private String selectRename;
	private Tag selectDelete;


	


	public MonitorInformation(List<Child> child, List<Context> context, List<Category> category, List<Tag> tag, List<Pictogram> content, List<Notification> notification, Filter filter){
		this.selectDelete=null;
		this.selectNotification=null;
		this.selectRename =null;
		this.selectAsignar="   ";
		this.child=child;
		this.context=context;
		this.category=category;
		this.tag=tag;
		this.pictogram=content;
		this.notification=notification;
		this.filter=filter;
	}
	
	

	
	public String getSelectNotification() {return selectNotification;}

	public void setSelectNotification(String selectNotification) {this.selectNotification = selectNotification;}

	public String getSelectAsignar() {return selectAsignar;}

	public void setSelectAsignar(String selectAsignar) {this.selectAsignar = selectAsignar;}
	
		public Tag getSelectDelete() {return selectDelete;}

	
	public void setSelectDelete(Tag selectDelete) {this.selectDelete = selectDelete;}

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
	
	public String getSelectRename() {return selectRename;}
	
	public void setSelectRename(String selectRename) {this.selectRename = selectRename;}

	public void deleteTag(Tag string) {
		tag.remove(string);
	}

}
