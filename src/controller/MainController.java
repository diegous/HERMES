package controller;

import java.awt.EventQueue;

import modelo.*;
import view.HermesView;

public class MainController {

	
	public static void main(String[] args){
		
		String json = "{"
				+ "\"child\": \"pepito\", "
				+ "\"context\":\"Pista\", "
				+ "\"category\":\"Emociones\", "
				+ "\"sent\":1111111111111111111, "
				+ "\"tag\":\"infeliz\", "
				+ "\"content\":\"Alegre\"}";
		
		Controller controller = new Controller();
		controller.processIncomingJson(json);
		
		
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					IDAO<Child> dao = FactoriaDAO.getChildDAO();
//					
//					HermesView frame = new HermesView(dao.getList());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		
		
		

	}
	
}
