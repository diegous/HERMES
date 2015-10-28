package bd;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws ParseException, SQLException {
		// Esto genera una fecha entre 01/01/97 00:00 y 12/12/15 00:00
		Random randomGenerator = new Random();
		long randomNumber = (randomGenerator.nextInt(597801600)+852076800L)*1000+randomGenerator.nextInt(1000);
		Date birthday = new Date(randomNumber);
		
		// Esto genera un DNI entre 39.000.000 y 45.000.000
		String dni = String.valueOf(randomGenerator.nextInt(6000000)+39000000);
		
		Child child = new Child("name", "lastname", dni, birthday);

		ChildDAO dao = new ChildDAO();
		
		dao.save(child);

		for (Child aChild : dao.getList()) {
			System.out.println(aChild.toString());
		}
	}

}
