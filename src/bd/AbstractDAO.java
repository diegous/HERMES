package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements IDAO<T> {
	protected String tableName;
	protected String idName;

	protected ResultSet getAll() {
		DBConector conector = new DBConector();
		conector.connect();
		ResultSet result = null;
		try {
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement query = conector.getConnection().prepareStatement(sql);
			result = query.executeQuery();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}
		conector.close();
		return result;
	}
	
	public ResultSet getByID(int id){
		DBConector conector = new DBConector();
		conector.connect();
		
		ResultSet result = null;

		try {
			String query = "SELECT * FROM "+tableName+" WHERE "+idName+"='?';";

			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}

		conector.close();
		return result;
	}

	public ResultSet getByText(String text, String fieldName){
		DBConector conector = new DBConector();
		conector.connect();
		ResultSet result = null;
		try {
			String query = "SELECT * FROM "+tableName+" WHERE "+fieldName+"=?;";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(query);
			preparedStatement.setString(1, text);
			result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}
		conector.close();
		return result;
	}
	
	public ResultSet getOrSave(T t, String text, String fieldName) throws SQLException{
		ResultSet result = this.getByText(text, fieldName);
		System.out.println(result.getFetchSize());
		try {
			if(result.getFetchSize() == 0){
				this.save(t);
				result = this.getByText(text, fieldName);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return result;
	}
	
	public void save(T t) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			PreparedStatement preparedStatement = prepareSaveStatement(conector.getConnection(), t);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}
		conector.close();
	}

	protected abstract PreparedStatement prepareSaveStatement(Connection con, T t)throws SQLException;

	public Boolean delete(int id) {
		DBConector conector = new DBConector();
		conector.connect();
		
		Boolean success = false;
		
		try {
			String query = "DELETE FROM "+tableName+" WHERE "+idName+"='?'";

			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			success = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}

		conector.close();
		return success;
	}

	public Boolean modify(T t) {
		return null;
	}
}
