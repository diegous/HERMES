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
			return resultList;
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
	}
	
	
}
