package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class NotificationDAO implements INotificationDAO {

	//SINGLETON 
		private static NotificationDAO notificationDAO = null;
		private NotificationDAO(){}
		public static NotificationDAO getNotificationDAO(){
			if(notificationDAO ==  null){notificationDAO = new NotificationDAO();}
			return notificationDAO;
		}
		
	
	//METHODS
	public void save(Notification n) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO category (id_category,id_child,id_context, id_tag, id_pictogram,sent_date, received_date) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, n.getCategory().getId());
			preparedStatement.setInt(2, n.getChild().getId());
			preparedStatement.setInt(3, n.getContext().getId());
			preparedStatement.setInt(4, n.getTag().getId());
			preparedStatement.setInt(5, n.getPictogram().getId());
			preparedStatement.setDate(6, (Date) n.getSent());
			preparedStatement.setDate(7, (Date) n.getRecieved());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
	}



}
