package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Context;

public class ContextDAO implements IDAO<Context>{

	//SINGLETON 
			private static ContextDAO contextDAO = null;
			private ContextDAO(){}
			public static ContextDAO getContextDAO(){
				if(contextDAO ==  null){contextDAO = new ContextDAO();}
				return contextDAO;
			}
			

	//METHODS
			@Override
			public List<Context> getList() {
				DBConector conector = DBConector.getDBConector();
				conector.connect();
				List<Context> resultList = new ArrayList<Context>();
				try {
					String sql = "SELECT * FROM context";
					PreparedStatement query = conector.getConnection().prepareStatement(sql);
					ResultSet result = query.executeQuery();
					while(result.next()){resultList.add(new Context(result.getInt("id_context"), result.getString("description")));}
					query.close();
					result.close();
					return resultList;
					
				} 
				catch (SQLException e) {e.printStackTrace(); return resultList;}
				finally{conector.close();}
			}
			
			
			
			@Override
			public Context getByText(String text) {
				DBConector conector = DBConector.getDBConector();
				conector.connect();
				Context context =null;
				try {
					String sql = "SELECT * FROM context WHERE description=?;";
					PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
					preparedStatement.setString(1, text);
					ResultSet result = preparedStatement.executeQuery();
					if(result.next()){context = new Context(result.getInt("id_context"), result.getString("description"));}
					preparedStatement.close();
					result.close();
					return context;		
				} 
				catch (SQLException e) {
					e.printStackTrace();
					return context;}
				finally {conector.close();}
			}
			
			
			@Override
			public void save(Context c) {
				DBConector conector = DBConector.getDBConector();
				conector.connect();
				try {
					String sql = "INSERT INTO context (description) VALUES (?);";
					PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
					preparedStatement.setString(1, c.getDescription());
					preparedStatement.executeUpdate();
					preparedStatement.close();
				} 
				catch (SQLException e) {e.printStackTrace();}
				finally{conector.close();}
				
			}
			@Override
			public void delete(Context selectDelete) {
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
