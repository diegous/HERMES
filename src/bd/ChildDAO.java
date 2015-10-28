package bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ChildDAO extends AbstractDAO<Child> {
	public ChildDAO(){
		this.tableName = "child";
		this.idName = "id_child";
	}

	public List<Child> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Child> resultList = new ArrayList<Child>();
		
		while(result.next()){
			resultList.add(new Child(result));
		}
			
		return resultList;
	}

	public Boolean save(Child t) {		
		return super.save(t);
	}
	
	public PreparedStatement prepareSaveStatement(Connection con, Child child) throws SQLException{
		String query = "INSERT INTO "+tableName+" (dni, name, lastname, birthday) VALUES (?,?,?,?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, child.getDni());
		preparedStatement.setString(2, child.getName());
		preparedStatement.setString(3, child.getLastname());
		preparedStatement.setDate(4, new Date(child.getBirthday().getTime()));
		
		return preparedStatement;
	}


	public Boolean delete(Child t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Child t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Child getById(int id) throws SQLException, ParseException {
		return new Child(super.getByID(id));
	}

}
