package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements IDAO<Category> {

	//SINGLETON 
	private static CategoryDAO categoryDAO = null;
	private CategoryDAO(){}
	public static CategoryDAO getCategoryDAO(){
		if(categoryDAO ==  null){categoryDAO = new CategoryDAO();}
		return categoryDAO;
	}
	
	//METHODS
	@Override
	public List<Category> getList() {
		DBConector conector = new DBConector();
		conector.connect();
		List<Category> resultList = new ArrayList<Category>();
		try {
			String sql = "SELECT * FROM category";
			PreparedStatement query = conector.getConnection().prepareStatement(sql);
			ResultSet result = query.executeQuery();
			while(result.next()){resultList.add(new Category(result.getInt("id_category"), result.getString("description")));}
			query.close();
			result.close();
			return resultList;
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
	}
	
	
	
	@Override
	public Category getByText(String text) {
		DBConector conector = new DBConector();
		conector.connect();
		Category category =null;
		try {
			String sql = "SELECT * FROM category WHERE description=?;";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, text);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()){category = new Category(result.getInt("id_category"), result.getString("description"));}
			preparedStatement.close();
			result.close();
			return category;		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return category;}
		finally {conector.close();}
	}
	@Override
	public void save(Category c) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO category (description) VALUES (?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, c.getDescription());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
		
	}
	
	
}
