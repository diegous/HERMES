package bd;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	public static void main(String[] args) throws ParseException, SQLException {
		// Esto genera una fecha entre 01/01/97 00:00 y 12/12/15 00:00
		Random randomGenerator = new Random();
		long randomNumber = (randomGenerator.nextInt(597801600)+852076800L)*1000+randomGenerator.nextInt(1000);
		Date birthday = new Date(randomNumber);
		birthday.getTime();
		// Esto genera un DNI entre 39.000.000 y 45.000.000
//		String dni = String.valueOf(randomGenerator.nextInt(6000000)+39000000);
		
		
		
		
		Child child = new Child("name");

		ChildDAO dao = new ChildDAO();
	
		dao.getOrSave(child);

	for (Child aChild : dao.getList()) {
		System.out.println(aChild.toString());
	}
		
	
	
	
	/*String json = "{\"child\": \"pepito\", \"context\":\"Pista\", \"category\":\"Emociones\", \"sent\":1111111111111111111, \"tag\":\"infeliz\", \"content\":\"Alegre\"}";
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject jArray = (JSONObject) parser.parse(json);
			
			System.out.println(jArray.get("sent").getClass());
			
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
//		JsonElement jelement = new JsonParser().parse(json);
//		JsonObject jobject = jelement.getAsJsonObject();
//		jobject = jobject.getAsJsonObject("child");
//		
//		System.out.println(jobject);
//		
//		System.out.println("hola");
	}

}
