package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ContextDAO extends AbstractDAO<Context> {
	public ContextDAO(){
		this.tableName = "context";
		this.idName = "id_context";
	}

	public List<Context> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Context> resultList = new ArrayList<Context>();
		
		while(result.next()){
			resultList.add(new Context(result));
		}
			
		return resultList;
	}

	public ResultSet save(Context t) {		
		return super.save(t);
	}
	
	public PreparedStatement prepareSaveStatement(Connection con, Context t) throws SQLException{
		String query = "INSERT INTO "+tableName+" (description) VALUES (?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);;
		preparedStatement.setString(1, t.getDescription());
		
		return preparedStatement;
	}
	
	public Context getOrSave(String text) throws SQLException, ParseException{
		return new Context(super.getOrSave(new Context(text), text, "description"));
	}


	public Boolean delete(Context t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Context t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Context getById(int id) throws SQLException, ParseException {
		return new Context(super.getByID(id));
	}
	
	public Context getByText(String text) throws SQLException, ParseException {
		return new Context(super.getByText(text, "description"));
	}
}
