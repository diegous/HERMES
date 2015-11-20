package controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;
import modelo.*;
import server.NotificationHandler;
import view.HermesView;

public class MainController {
	
	public static void main(String[] args) throws IOException{
		
		//VISTA
		MonitorInformation monitor = FactoriaDAO.getMonitorInformationDAO().getMonitorInformation();
		
		HermesView frame = new HermesView(monitor);
		frame.setVisible(true);
		
		SynchronizerNotifications sn=new SynchronizerNotifications();
		
		//SERVIDOR
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
 	    server.createContext("/get", new NotificationHandler(sn));
    	server.setExecutor(null);
    	server.start();
    	
    	
    	//CONSULTA POR NOTIFICACIONES
    	while(true){
    		Notification n=sn.getNotification().removeElement();
    		if(n!=null){
    			frame.addNotification(n);
    			Child child= sn.getChild().removeElement();
    			if(child!=null){frame.addChild(child);}
    			Context context=sn.getContext().removeElement();
    			if(context!=null){frame.addContext(context);}
    			Category category=sn.getCategory().removeElement();
    			if(category!=null){frame.addCategory(category);}
    			Pictogram pictogram=sn.getPictogram().removeElement();
    			if(pictogram!=null){frame.addPictogram(pictogram);}
    		}
    		try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
		
	}
	
}
