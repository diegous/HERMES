package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ChildDAO extends AbstractDAO<Child> {
	
	//SINGLETON 
	private static ChildDAO childDAO = null;
	
	private ChildDAO(){
		this.tableName = "child";
		this.idName = "id_child";
	}
	
	public static ChildDAO getChildDAO(){
		if(childDAO ==  null){childDAO = new ChildDAO();}
		return childDAO;
	}
	
	//METHODS
	public List<Child> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Child> resultList = new ArrayList<Child>();
		while(result.next()){
			resultList.add(new Child(result));
		}
		return resultList;
	}

	public void save(Child t) {super.save(t);}
	
	public PreparedStatement prepareSaveStatement(Connection con, Child child) throws SQLException{
		String query = "INSERT INTO "+tableName+" (name) VALUES (?);";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, child.getName());
		return preparedStatement;
	}
	
	public Child getOrSave(Child t) throws SQLException, ParseException{
		return new Child(super.getOrSave(t, t.getName(), "name"));
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
	
	public Child getByText(String text) throws SQLException, ParseException {
		return new Child(super.getByText(text, "name"));
	}

}
