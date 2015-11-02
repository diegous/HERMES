package controller;

import java.awt.EventQueue;

import modelo.*;
import view.HermesView;

public class MainController {

	
	public static void main(String[] args){
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDAO<Child> dao = FactoriaDAO.getChildDAO();
					
					HermesView frame = new HermesView(dao.getList());
					frame.setVisible(true);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		

	}
	
}
