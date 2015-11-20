package controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import modelo.*;
import server.NotificationHandler;
import view.HermesView;

public class MainController {
	
	public static void main(String[] args) throws IOException{
		
		//VISTA
		MonitorInformation monitor = FactoriaDAO.getMonitorInformationDAO().getMonitorInformation();
		
		HermesView frame = new HermesView(monitor);
		frame.setVisible(true);
		
		//SERVIDOR
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
 	    server.createContext("/get", new NotificationHandler());
    	server.setExecutor(null);
    	server.start();
		
	}
	
}
