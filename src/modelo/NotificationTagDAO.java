package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NotificationTagDAO implements INotificatioTagDAO {

	//SINGLETON 
			private static NotificationTagDAO notificatioTagDAO = null;
			private NotificationTagDAO(){}
			public static NotificationTagDAO getNotificatioTagDAO(){
				if(notificatioTagDAO ==  null){notificatioTagDAO = new NotificationTagDAO();}
				return notificatioTagDAO;
			}
	
	//METHODS	@Override
			@Override
			public String getList(int id_notification) {
				DBConector conector = new DBConector();
				conector.connect();
				String tagList = "";
				try {
					String sql="SELECT id_tag FROM notificationtag WHERE id_notification=?;";
				    PreparedStatement query = conector.getConnection().prepareStatement(sql);
				    query.setInt(1, id_notification);
				    ResultSet result = query.executeQuery();
			        if(result.next())
			        	tagList=tagList +FactoriaDAO.getTagDAO().getByID(result.getInt("id_tag"));
			        
			        while(result.next())
			        	tagList=tagList +", "+FactoriaDAO.getTagDAO().getByID(result.getInt("id_tag"));

					query.close();
					result.close();
					return tagList;
				} 
				catch (SQLException e) {e.printStackTrace(); return tagList;}
				finally{conector.close();}
			}
			
			@Override
			public void save(int notification ,int tag ) {
				DBConector conector = new DBConector();
				conector.connect();
				if(! this.existe(notification, tag)){
					try {
						String sql = "INSERT INTO notificationtag (id_tag, id_notification) VALUES (?,?);";
						PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
						preparedStatement.setInt(1, tag);
						preparedStatement.setInt(2, notification);
						preparedStatement.executeUpdate();
						preparedStatement.close();
					} 
					catch (SQLException e) {e.printStackTrace();}
					finally{conector.close();}
				}
				else{
					try {
						String sql = "DELETE FROM notificationtag WHERE id_notification=? and id_tag=?;";
						PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
						preparedStatement.setInt(1, notification);
						preparedStatement.setInt(2, tag);
						preparedStatement.executeUpdate();
						preparedStatement.close();
					} 
					catch (SQLException e) {e.printStackTrace();}
					finally{conector.close();}
				}
			}
			
			private boolean existe(int notification ,int tag){
				DBConector conector = new DBConector();
				conector.connect();
				boolean b=false;
				try {
					String sql = "SELECT id_tag FROM notificationtag WHERE id_notification=? and id_tag=?;";
					PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
					preparedStatement.setInt(1, notification);
					preparedStatement.setInt(2, tag);
					ResultSet result = preparedStatement.executeQuery();
					b=result.next();
					result.close();
					preparedStatement.close();
					return b;
				} 
				catch (SQLException e) {e.printStackTrace();return b;}
				finally{conector.close();}
			} 
			
			@Override
			public void delete(int tag) {
				DBConector conector = new DBConector();
				conector.connect();
				try {
						String sql = "DELETE FROM notificationtag WHERE id_tag=?;";
						PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
						preparedStatement.setInt(1, tag);
						preparedStatement.executeUpdate();
						preparedStatement.close();
						
					} 
					catch (SQLException e) {e.printStackTrace();}
					finally{conector.close();}
				}
			}
