package controller;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;
import modelo.*;
import view.HermesView;

public class Controller {
	public void processIncomingJson(String json, HermesView frame){
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject jArray = (JSONObject) parser.parse(json);
			
			String
				jchild     = (String) jArray.get("child"),
				jcontext   = (String) jArray.get("context"),
				jcategory  = (String) jArray.get("category"),
				jpictogram = (String) jArray.get("pictogram");
			
			long
//				jsent      = (Long) jArray.get("sent");
				jsent	   = 1447200000000L + (new Random().nextInt(2712960))*10000;

			
			Child child = FactoriaDAO.getChildDAO().getByText(jchild);
			if (child == null){
				FactoriaDAO.getChildDAO().save(new Child(-1, jchild));
				child = FactoriaDAO.getChildDAO().getByText(jchild);
				frame.addChild(child);
			}

			Context context = FactoriaDAO.getContextDAO().getByText(jcontext);
			if (context == null){
				FactoriaDAO.getContextDAO().save(new Context(-1, jcontext));
				context = FactoriaDAO.getContextDAO().getByText(jcontext);
				frame.addContext(context);
			}

			Category category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
			if (category == null){
				FactoriaDAO.getCategoryDAO().save(new Category(-1, jcategory));
				category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
				frame.addCategory(category);
			}

			Pictogram pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
			if (pictogram == null){
				FactoriaDAO.getPictogramDAO().save(new Pictogram(-1, jpictogram));
				pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
				frame.addPictogram(pictogram);
			}
			
			Date sent = new Date(jsent);
			Date recieved = new Date();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Notification notification = new Notification(-1,child,context,category, null,pictogram,sent,recieved);
			frame.addNotification(notification);
			
			FactoriaDAO.getNotificationDAO().save(notification);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
