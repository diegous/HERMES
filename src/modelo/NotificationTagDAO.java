package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;


public class NotificationTagDAO implements INotificatioTagDAO {

	//SINGLETON 
			private static NotificationTagDAO notificatioTagDAO = null;
			private NotificationTagDAO(){}
			public static NotificationTagDAO getNotificatioTagDAO(){
				if(notificatioTagDAO ==  null){notificatioTagDAO = new NotificationTagDAO();}
				return notificatioTagDAO;
			}
	
	//METHODS	@Override
			
			public String getList(int id_notification) {
				DBConector conector = DBConector.getDBConector();
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
			
		
			public void save(int notification ,int tag ) {
				DBConector conector = DBConector.getDBConector();
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
				DBConector conector = DBConector.getDBConector();
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
			
			
			public void delete(int tag) {
				DBConector conector = DBConector.getDBConector();
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
			
			
			
			
			
			
			
			
			public List<Date> getListFechas() {
				DBConector conector = DBConector.getDBConector();
				conector.connect();
				List<Date> resultList = new ArrayList<Date>();
				try {
					String sql="SELECT distinct(sent_date) FROM notification ORDER BY  sent_date;";
			        PreparedStatement query = conector.getConnection().prepareStatement(sql);
			        ResultSet result = query.executeQuery();
		        	while(result.next()){resultList.add(new Date(result.getLong("sent_date")));}
					query.close();
					result.close();
					return resultList;
				} 
				catch (SQLException e) {e.printStackTrace(); return resultList;}
				finally{conector.close();}
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			public Date getFecha(Date sent) {
				DBConector conector = DBConector.getDBConector();
				conector.connect();
				Date resultDate=null;
				try {
					String sql="SELECT sent_date FROM notification WHERE  sent_date=?;";
			        PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			        preparedStatement.setLong(1, sent.getTime());
			        ResultSet result = preparedStatement.executeQuery();
			       	if(result.next()){resultDate = new Date(result.getLong("sent_date"));}
			       	preparedStatement.close();
					result.close();
					return resultDate;
				} 
				catch (SQLException e) {e.printStackTrace(); return resultDate;}
				finally{conector.close();}
			}
			}
