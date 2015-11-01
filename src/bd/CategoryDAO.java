package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends AbstractDAO<Category> {
	public CategoryDAO(){
		this.tableName = "category";
		this.idName = "id_category";
	}

	public List<Category> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Category> resultList = new ArrayList<Category>();
		
		while(result.next()){
			resultList.add(new Category(result));
		}
			
		return resultList;
	}

	public void save(Category t) {		
		super.save(t);
	}
	
	public PreparedStatement prepareSaveStatement(Connection con, Category t) throws SQLException{
		String query = "INSERT INTO "+tableName+" (description) VALUES (?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);;
		preparedStatement.setString(1, t.getDescription());
		
		return preparedStatement;
	}
	
	public Category getOrSave(String text) throws SQLException, ParseException{
		return new Category(super.getOrSave(new Category(text), text, "description"));
	}

	public Boolean delete(Category t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Category t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Category getById(int id) throws SQLException, ParseException {
		return new Category(super.getByID(id));
	}
	
	public Category getByText(String text) throws SQLException, ParseException {
		return new Category(super.getByText(text, "description"));
	}

	@Override
	public Category getOrSave(Category t) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
