package modelo;

import java.util.Date;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;


public class SynchronizerNotifications {
	
	
	private Synchronized<Child> child = new Synchronized<Child>();
	private Synchronized<Context> context= new Synchronized<Context>();
	private Synchronized<Category> category= new Synchronized<Category>();
	private Synchronized<Pictogram> pictogram= new Synchronized<Pictogram>();
	private Synchronized<Notification> notification= new Synchronized<Notification>();
	private Synchronized<Date> fechas= new Synchronized<Date>();
	
	
	public Synchronized<Child> getChild() {return child;}
	public Synchronized<Context> getContext() {return context;}
	public Synchronized<Category> getCategory() {return category;}
	public Synchronized<Pictogram> getPictogram() {return pictogram;}
	public Synchronized<Notification> getNotification() {return notification;}
	public Synchronized<Date> getFecha() {return fechas;}
	
	
}
