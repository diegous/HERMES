package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;
import modelo.*;
import server.ServerHandlerThread;
import view.HermesView;

public class MainController {
	static int serverPortNumber;

	public static Document getConfigFile(){
		try {
			File fXmlFile = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
						
			doc.getDocumentElement().normalize();
			
			return doc;
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException{
		
		// CARGAR CONFIGURACIONES
		Document configs = getConfigFile();
		serverPortNumber = Integer.parseInt(configs.getElementsByTagName("serverport").item(0).getTextContent());
		
		//VISTA
		MonitorInformation monitor = FactoriaDAO.getMonitorInformationDAO().getMonitorInformation();
		
		HermesView frame = new HermesView(monitor);
		frame.setVisible(true);
		
		SynchronizerNotifications sn = new SynchronizerNotifications();
		
		//SERVIDOR
		ServerHandlerThread server = new ServerHandlerThread(sn, serverPortNumber); 
		server.run();
    	
    	
    	//CONSULTA POR NOTIFICACIONES
		String ultimaFecha=monitor.getFechas().get(monitor.getFechas().size()-1).toString();
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
    			Date fecha=sn.getFecha().removeElement();
    			if(fecha!=null && !ultimaFecha.equals(fecha.toString())){
    				ultimaFecha=fecha.toString();
    				frame.addFecha(fecha);}
    		}
    	}
		
	}
	
}
