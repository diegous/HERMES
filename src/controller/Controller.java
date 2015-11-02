package controller;

import java.util.Date;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import modelo.*;

public class Controller {
	
	public void processIncomingJson(String json){
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject jArray = (JSONObject) parser.parse(json);
			
			String
				jchild     = (String) jArray.get("child"),
				jcontext   = (String) jArray.get("context"),
				jcategory  = (String) jArray.get("category"),
				jtag       = (String) jArray.get("tag"),
				jpictogram = (String) jArray.get("pictogram");
			
			long
				jsent      = (Long) jArray.get("sent");

			Child child = FactoriaDAO.getChildDAO().getByText(jchild);
			if (child == null){
				FactoriaDAO.getChildDAO().save(new Child(-1, jchild));
				child = FactoriaDAO.getChildDAO().getByText(jchild);
			}

			Context context = FactoriaDAO.getContextDAO().getByText(jcontext);
			if (context == null){
				FactoriaDAO.getContextDAO().save(new Context(-1, jcontext));
				context = FactoriaDAO.getContextDAO().getByText(jcontext);
			}

			Category category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
			if (category == null){
				FactoriaDAO.getCategoryDAO().save(new Category(-1, jcategory));
				category = FactoriaDAO.getCategoryDAO().getByText(jcategory);
			}

			Tag tag = FactoriaDAO.getTagDAO().getByText(jtag);
			if (tag == null){
				FactoriaDAO.getTagDAO().save(new Tag(-1, jtag));
				tag = FactoriaDAO.getTagDAO().getByText(jtag);
			}

			Pictogram pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
			if (pictogram == null){
				FactoriaDAO.getPictogramDAO().save(new Pictogram(-1, jpictogram));
				pictogram = FactoriaDAO.getPictogramDAO().getByText(jpictogram);
			}
			
			Date sent = new Date(jsent);
			Date recieved = new Date();
			
			Notification notification = new Notification(-1,child,context,category,tag,pictogram,sent,recieved);
			
			FactoriaDAO.getNotificationDAO().save(notification);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		//cdao.getByText(jArray.get("child"));
		
		//Notification notification = processJson(json);
		
	}
}
