package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictogramDAO implements IDAO<Pictogram> {

	//SINGLETON 
			private static PictogramDAO pictogramDAO = null;
			private PictogramDAO(){}
			public static PictogramDAO getPictogramDAO(){
				if(pictogramDAO ==  null){pictogramDAO = new PictogramDAO();}
				return pictogramDAO;
			}
			
		
	//METHODS
	@Override
	public List<Pictogram> getList() {
		DBConector conector = new DBConector();
		conector.connect();
		List<Pictogram> resultList = new ArrayList<Pictogram>();
		try {
			String sql = "SELECT * FROM pictogram";
			PreparedStatement query = conector.getConnection().prepareStatement(sql);
			ResultSet result = query.executeQuery();
			while(result.next()){resultList.add(new Pictogram(result.getInt("id_pictogram"), result.getString("content")));}
			query.close();
			result.close();
			return resultList;
			
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
	}

	@Override
	public Pictogram getByText(String text) {
		DBConector conector = new DBConector();
		conector.connect();
		Pictogram pictogram =null;
		try {
			String sql = "SELECT * FROM pictogram WHERE content=?;";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, text);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()){pictogram = new Pictogram(result.getInt("id_pictogram"), result.getString("content"));}
			preparedStatement.close();
			result.close();
			return pictogram;		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return pictogram;}
		finally {conector.close();}
	}

	@Override
	public void save(Pictogram p) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO pictogram (content) VALUES (?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, p.getContent());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
	}
	@Override
	public void delete(Pictogram selectDelete) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void modify(String selectModify, String string) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getByID(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

}
