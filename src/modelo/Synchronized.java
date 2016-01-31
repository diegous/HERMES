package modelo;

import java.util.ArrayList;
import java.util.List;

public class Synchronized<T> {

	private List<T> list=new ArrayList<T>();
	
	
	
	public synchronized void addElement(T element){
		list.add(element);
	}
	
	public synchronized T removeElement(){
		if(list.size()>0){
			return list.remove(0);
		}
		else return null;
	}
	
	public int cantElementos(){
		return list.size();
	}
	

	
}
