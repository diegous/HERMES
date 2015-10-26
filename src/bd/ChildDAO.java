package bd;

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
		String[] parameterNames = {
				"dni",
				"name",
				"lastname",
				"birthday",
		};
		
		Boolean success = super.save(t, parameterNames);
		return success;
	}
	
	public PreparedStatement putSaveParametersOnQuery(PreparedStatement query, Child child) throws SQLException{
		query.setString(1, child.getDni());
		query.setString(2, child.getName());
		query.setString(3, child.getLastname());
		query.setDate(4, new Date(child.getBirthday().getTime()));
		
		return query;
	}


	public Boolean delete(Child t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Child t) {
		// TODO Auto-generated method stub
		return false;		
	}

}
