package controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

import entities.Notification;
import modelo.*;
import server.NotificationHandler;
import view.HermesView;

public class MainController {
	
	public static void main(String[] args) throws IOException{
		
		//VISTA
		MonitorInformation monitor = FactoriaDAO.getMonitorInformationDAO().getMonitorInformation();
		
		HermesView frame = new HermesView(monitor);
		frame.setVisible(true);
		
		SynchronizedNotification sn=new SynchronizedNotification();
		
		//SERVIDOR
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
 	    server.createContext("/get", new NotificationHandler(sn));
    	server.setExecutor(null);
    	server.start();
    	
    	
    	//CONSULTA POR NOTIFICACIONES
    	while(true){
    		Notification n=sn.removeNotification();
    		if(n!=null){
    			frame.addNotification(n);
    		}
    		try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
		
	}
	
}
