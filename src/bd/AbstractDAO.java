package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements IDAO<T> {
	protected String tableName;

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

	public Boolean save(T t, String[] parameterNames) {
		DBConector conector = new DBConector();
		conector.connect();

		String parametersString="", values="";
		
		for(Object parameter : parameterNames){
			parametersString = parametersString.concat(parameter.toString()+",");
			values = values.concat("?,");
		}
		
		// Getting rid of extra ","
		parametersString = parametersString.substring(0, parametersString.length()-1);
		values = values.substring(0, values.length()-1);
		
		Boolean success = false;
		
		try {
			String sql = "INSERT INTO "+tableName+" ("+parametersString+") VALUES ("+values+");";

			PreparedStatement query = conector.getConnection().prepareStatement(sql);
			query = putSaveParametersOnQuery(query, t);

			success = query.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			conector.close();
		}

		conector.close();
		return success;
	}
	
	public abstract PreparedStatement putSaveParametersOnQuery(PreparedStatement query, T t) throws SQLException;

	public Boolean delete(T t) {
		return null;
	}

	public Boolean modify(T t) {
		return null;
	}
}
