package controller;

import java.sql.Date;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bd.*;

public class Controller {
	/*
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
				jsent      = (Long) jArray.get("sent"),
				jrecieved  = (Long) jArray.get("recieved");

			Child child = new ChildDAO().getOrSave(jchild);
			Context context = new ContextDAO().getOrSave(jcontext);
			Category category = new CategoryDAO().getOrSave(jcategory);
			Tag tag = new TagDAO().getOrSave(jtag);
			Pictogram pictogram = new PictogramDAO().getOrSave(jpictogram);
			Date sent = new Date(jsent);
			Date recieved = new Date(jrecieved);
			
			Notification notification = new Notification(child, context, category, tag, pictogram, sent, recieved);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			System.err.println("Malformed JSON");
			e.printStackTrace();
		}
		
		
		//cdao.getByText(jArray.get("child"));
		
		//Notification notification = processJson(json);
		
	}*/
}
