package controller;

import java.util.ArrayList;
import java.util.List;
import entities.Notification;

public class SynchronizedNotification {
	private List<Notification> l=new ArrayList<Notification>();
	
	public synchronized void addNotification(Notification n){
		l.add(n);
	}
	
	public synchronized Notification removeNotification(){
		if(l.size()>0){
			return l.remove(0);
		}
		else return null;
	}
	
}
