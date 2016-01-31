package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Child;

public class ChildDAO implements IDAO<Child> {

	//SINGLETON 
		private static ChildDAO childDAO = null;
		private ChildDAO(){}
		public static ChildDAO getChildDAO(){
			if(childDAO ==  null){childDAO = new ChildDAO();}
			return childDAO;
		}
		
	//METHODS
	
	
	public List<Child> getList() {
			DBConector conector = DBConector.getDBConector();
			conector.connect();
			List<Child> resultList = new ArrayList<Child>();
			try {
				String sql = "SELECT * FROM child";
				PreparedStatement query = conector.getConnection().prepareStatement(sql);
				ResultSet result = query.executeQuery();
				while(result.next()){resultList.add(new Child(result.getInt("id_child"), result.getString("name")));}
				query.close();
				result.close();
				return resultList;
			} 
			catch (SQLException e) {e.printStackTrace(); return resultList;}
			finally{conector.close();}
	}
	
	public void save(Child c) {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO child (name) VALUES (?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, c.getName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
	}

	public Child getByText(String text) {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		Child child =null;
		try {
			String sql = "SELECT * FROM child WHERE name=?;";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, text);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()){child = new Child(result.getInt("id_child"), result.getString("name"));}	
			preparedStatement.close();
			result.close();
			return child;		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return child;}
		finally {conector.close();}
		
	}


	public void delete(Child selectDelete) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void modify(String selectModify, String string) {
		// TODO Auto-generated method stub
		
	}


	public String getByID(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

}
