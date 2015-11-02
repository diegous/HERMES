package controller;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import view.HermesView;

public class MainController {
	
	public static List<String> getJson(){
		List<String> result = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("jsons.json"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       result.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorInformation monitor = FactoriaDAO.getMonitorInformationDAO().getMonitorInformation();
					
					HermesView frame = new HermesView(monitor);
					frame.setVisible(true);

					Controller controller = new Controller();

					for(String json : getJson()){
						controller.processIncomingJson(json, monitor);
					}					
					
					frame.repaint();
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
}
