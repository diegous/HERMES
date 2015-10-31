package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO extends AbstractDAO<Notification> {
	public NotificationDAO(){
		this.tableName = "notification";
		this.idName = "id_notification";
	}

	public List<Notification> getList() throws SQLException, ParseException {
		ResultSet result = super.getAll();
		List<Notification> resultList = new ArrayList<Notification>();
		
		while(result.next()){
			resultList.add(new Notification(result));
		}
			
		return resultList;
	}

	public ResultSet save(Notification t) {		
		return super.save(t);
	}
	
	public PreparedStatement prepareSaveStatement(Connection con, Notification t) throws SQLException{
		String query = "INSERT INTO "+tableName+" (id_child, id_context, id_category, sent, recieved, id_tag, id_pictogram) VALUES (?,?,?,?,?,?,?);";

		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, t.getChild().getId());
		preparedStatement.setInt(2, t.getContext().getId());
		preparedStatement.setInt(3, t.getCategory().getId());
		preparedStatement.setLong(4,t.getSent().getTime());
		preparedStatement.setLong(5,t.getRecieved().getTime());
		preparedStatement.setInt(6, t.getTag().getId());
		preparedStatement.setInt(7, t.getPictogram().getId());
		
		return preparedStatement;
	}


	public Boolean delete(Notification t) {
		// TODO Auto-generated method stub
		return false;		
	}

	public Boolean modify(Notification t) {
		// TODO Auto-generated method stub
		return false;		
	}

	@Override
	public Notification getById(int id) throws SQLException, ParseException {
		return new Notification(super.getByID(id));
	}
	
	public Notification getByText(String text) throws SQLException, ParseException {
//		return new Notification(super.getByText(text, "description"));
		return null;
	}
}
