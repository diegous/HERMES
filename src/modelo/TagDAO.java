package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO implements IDAO<Tag> {

	//SINGLETON 
			private static TagDAO tagDAO = null;
			private TagDAO(){}
			public static TagDAO getTagDAO(){
				if(tagDAO ==  null){tagDAO = new TagDAO();}
				return tagDAO;
			}
			
		
	//METHODS

	@Override
	public List<Tag> getList() {
		DBConector conector = new DBConector();
		conector.connect();
		List<Tag> resultList = new ArrayList<Tag>();
		try {
			String sql = "SELECT * FROM tag";
			PreparedStatement query = conector.getConnection().prepareStatement(sql);
			ResultSet result = query.executeQuery();
			while(result.next()){resultList.add(new Tag(result.getInt("id_tag"), result.getString("description")));}
			query.close();
			result.close();
			return resultList;
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
	}

	@Override
	public Tag getByText(String text) {
		DBConector conector = new DBConector();
		conector.connect();
		Tag tag =null;
		try {
			String sql = "SELECT * FROM tag WHERE description=?;";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, text);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()){tag = new Tag(result.getInt("id_tag"), result.getString("description"));}
			preparedStatement.close();
			result.close();
			return tag;		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return tag;}
		finally {conector.close();}
	}

	@Override
	public void save(Tag t) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO tag (description) VALUES (?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, t.getDescription());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
	}

}
