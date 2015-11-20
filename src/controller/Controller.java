package controller;

import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;
import modelo.*;

public class Controller {
	public void processIncomingJson(String json, SynchronizerNotifications sn){
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject jArray = (JSONObject) parser.parse(json);
			
			String
				jchild     = (String) jArray.get("child"),
				jcontext   = (String) jArray.get("context"),
				jcategory  = (String) jArray.get("category"),
				jpictogram = (String) jArray.get("pictogram");
			
			long
				jsent      = (Long) jArray.get("sent");
				

			
			Child child = FactoriaDAO.getChildDAO().getByText(jchild);
			if (child == null){
				FactoriaDAO.getChildDAO().save(new Child(-1, jchild));
				child = FactoriaDAO.getChildDAO().getByText(jchild);
				sn.getChild().addElement(child);
				
			}

			Context context = FactoriaDAO.getContextDAO().getByText(jcontext);
			if (context == null){
				FactoriaDAO.getContextDAO().save(new Context(-1, jcontext));
				context = FactoriaDAO.getContextDAO().getByText(jcontext);
				sn.getContext().addElement(context);
				
			}

			Category category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
			if (category == null){
				FactoriaDAO.getCategoryDAO().save(new Category(-1, jcategory));
				category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
				sn.getCategory().addElement(category);
				
			}

			Pictogram pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
			if (pictogram == null){
				FactoriaDAO.getPictogramDAO().save(new Pictogram(-1, jpictogram));
				pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
				sn.getPictogram().addElement(pictogram);
				
			}
			
			Date sent = new Date(jsent);
			Date recieved = new Date();
			Notification notification = new Notification(-1,child,context,category, null,pictogram,sent,recieved);
			FactoriaDAO.getNotificationDAO().save(notification);
			sn.getNotification().addElement(notification);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
