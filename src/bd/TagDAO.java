package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends AbstractDAO<Tag> {
	public TagDAO(){
		this.tableName = "tag";
		this.idName = "id_tag";
	}

	public List<Tag> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Tag> resultList = new ArrayList<Tag>();
		
		while(result.next()){
			resultList.add(new Tag(result));
		}
			
		return resultList;
	}

	public void save(Tag t) {		
		super.save(t);
	}
	
	public PreparedStatement prepareSaveStatement(Connection con, Tag tag) throws SQLException{
		String query = "INSERT INTO "+tableName+" (description) VALUES (?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);;
		preparedStatement.setString(1, tag.getDescription());
		
		return preparedStatement;
	}
	
	public Tag getOrSave(String text) throws SQLException, ParseException{
		return new Tag(super.getOrSave(new Tag(text), text, "description"));
	}


	public Boolean delete(Tag t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Tag t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Tag getById(int id) throws SQLException, ParseException {
		return new Tag(super.getByID(id));
	}
	
	public Tag getByText(String text) throws SQLException, ParseException {
		return new Tag(super.getByText(text, "description"));
	}

	@Override
	public Tag getOrSave(Tag t) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
