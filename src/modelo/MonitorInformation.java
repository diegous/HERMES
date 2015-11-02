package modelo;

import java.util.List;

public class MonitorInformation {
	
	private List<Child> child;
	private List<Context> context;
	private List<Category> category;
	private List<Tag> tag;
	private List<Pictogram> content;
	private List<Notification> notification;
	
	
	public MonitorInformation(List<Child> child, List<Context> context, List<Category> category, List<Tag> tag, List<Pictogram> content, List<Notification> notification){
		this.child=child;
		this.context=context;
		this.category=category;
		this.tag=tag;
		this.content=content;
		this.notification=notification;
	}
	
	
	public List<Child> getChild() {return child;}
	
	public void addChild(Child child) {this.child.add(child);}
	
	public List<Context> getContext() {return context;}
	
	public void addContext(Context context) {this.context.add(context);}
	
	public List<Category> getCategory() {return category;}
	
	public void addCategory(Category category) {this.category.add(category);}
	
	public List<Tag> getTag() {return tag;}
	
	public void addTag(Tag tag) {this.tag.add(tag);}
	
	public List<Pictogram> getContent() {return content;}
	
	public void addContent(Pictogram content) {this.content.add(content);}
	
	public List<Notification> getNotification() {return notification;}
	
	public void setNotification(Notification notification) {this.notification.add(notification);}
	
	
	
}
