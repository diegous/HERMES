package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PictogramDAO extends AbstractDAO<Pictogram> {
	public PictogramDAO(){
		this.tableName = "pictogram";
		this.idName = "id_pictogram";
	}

	public List<Pictogram> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Pictogram> resultList = new ArrayList<Pictogram>();
		
		while(result.next()){
			resultList.add(new Pictogram(result));
		}
			
		return resultList;
	}

	public void save(Pictogram t) {super.save(t);}
	
	public PreparedStatement prepareSaveStatement(Connection con, Pictogram t) throws SQLException{
		String query = "INSERT INTO "+tableName+" (description) VALUES (?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);;
		preparedStatement.setString(1, t.getContent());
		
		return preparedStatement;
	}
	
	public Pictogram getOrSave(String text) throws SQLException, ParseException{
		return new Pictogram(super.getOrSave(new Pictogram(text), text, "content"));
	}


	public Boolean delete(Pictogram t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Pictogram t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Pictogram getById(int id) throws SQLException, ParseException {
		return new Pictogram(super.getByID(id));
	}
	
	public Pictogram getByText(String text) throws SQLException, ParseException {
		return new Pictogram(super.getByText(text, "content"));
	}

	@Override
	public Pictogram getOrSave(Pictogram t) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
