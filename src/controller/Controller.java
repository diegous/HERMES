package controller;

import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
				jsent      = (Long) jArray.get("sent");

			
			Child child = FactoriaDAO.getChildDAO().getByText(jchild);
			if (child == null){
				FactoriaDAO.getChildDAO().save(new Child(-1, jchild));
				child = FactoriaDAO.getChildDAO().getByText(jchild);
				frame.addChild(child.getName());
			}

			Context context = FactoriaDAO.getContextDAO().getByText(jcontext);
			if (context == null){
				FactoriaDAO.getContextDAO().save(new Context(-1, jcontext));
				context = FactoriaDAO.getContextDAO().getByText(jcontext);
				frame.addContext(context.getDescription());
			}

			Category category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
			if (category == null){
				FactoriaDAO.getCategoryDAO().save(new Category(-1, jcategory));
				category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
				frame.addCategory(category.getDescription());
			}

			Pictogram pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
			if (pictogram == null){
				System.out.println(jpictogram);
				FactoriaDAO.getPictogramDAO().save(new Pictogram(-1, jpictogram));
				pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
				frame.addPictogram(pictogram.getContent());
			}
			
			Date sent = new Date(jsent);
			Date recieved = new Date();
			
			Notification notification = new Notification(-1,child,context,category, null,pictogram,sent,recieved);
			
			FactoriaDAO.getNotificationDAO().save(notification);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
