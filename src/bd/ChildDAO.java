package bd;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChildDAO implements IDAO<Child> {

	public List<Child> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Child t) {
		// TODO Auto-generated method stub
		Conector conector = new Conector();
		conector.connect();
		
		PreparedStatement query;
		try {
			query = conector.connect.prepareStatement("INSERT INTO child(dni, name, lastname, birthday)"
					+ " VALUES (" + t.getDni()+ ","+t.getName()+", "+t.getName()+","+ t.getBirthday().toString()+");");
		
			query.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delete(Child t) {
		// TODO Auto-generated method stub
		
	}

	public void modify(Child t) {
		// TODO Auto-generated method stub
		
	}

}
