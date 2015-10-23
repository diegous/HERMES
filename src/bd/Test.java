package bd;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		
		Child child = new Child(123, "pepito", "124567", date);
		
		ChildDAO dao = new ChildDAO();
		
		dao.save(child);
		
		System.out.println("hola");
	}

}
