package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
			String sql = "INSERT INTO notification (id_category,id_child,id_context, id_tag, id_pictogram,sent_date, received_date) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, n.getCategory().getId());
			preparedStatement.setInt(2, n.getChild().getId());
			preparedStatement.setInt(3, n.getContext().getId());
			preparedStatement.setInt(4, n.getTag().getId());
			preparedStatement.setInt(5, n.getPictogram().getId());
			preparedStatement.setLong(6, n.getSent().getTime());
			preparedStatement.setLong(7, n.getRecieved().getTime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
	}
	
	
	@Override
	public List<Notification> getList(Filter f) {
		DBConector conector = new DBConector();
		conector.connect();
		List<Notification> resultList = new ArrayList<Notification>();
		try {
			
			//CONSULTA
			String sql="SELECT  id_notification, cat.id_category, cat.description as Dcategory, c.id_child, name, cont.id_context, cont.description as Dcontext, p.id_pictogram, p.content, t.id_tag, t.description as Dtag, sent_date, received_date" 
					+" FROM notification as n inner join category as cat on (n.id_category=cat.id_category)" 
	                   +" inner join child as c on (n.id_child=c.id_child)" 
	                   +" inner join context as cont on (n.id_context=cont.id_context)" 
	                   +" inner join pictogram as p on (n.id_pictogram=p.id_pictogram)"
	                   +" inner join tag as t on (n.id_tag=t.id_tag)"
	                   +" WHERE 1=1";
	        if(f.getCategory() != "Todo"){sql=sql+" and cat.description=?";}
	        if(f.getChild() != "Todo"){sql=sql+" and name=?";}
	        if(f.getContext() != "Todo"){sql=sql+" and cont.description=?";}
	        if(f.getPictogram() != "Todo"){sql=sql+" and p.content=?";}
	        if(f.getTag() != "Todo"){sql=sql+" and t.description=?";}
	        if(f.getSince() != "Todo"){sql=sql+" and sent_date>=?";}
	        if(f.getUntil() != "Todo" ){sql=sql+" and sent_date<=?";}
	        sql=sql+";";
	       
	        PreparedStatement query = conector.getConnection().prepareStatement(sql);
	        
	        //PARAMETROS
	        int i=1;
	        if(f.getCategory() != "Todo"){query.setString(i, f.getCategory()); i++;}
	        if(f.getChild() != "Todo"){query.setString(i, f.getChild()); i++;}
	        if(f.getContext() != "Todo"){query.setString(i, f.getContext()); i++;}
	        if(f.getPictogram() != "Todo"){query.setString(i, f.getPictogram()); i++;}
	        if(f.getTag() != "Todo"){query.setString(i, f.getTag()); i++;}
	        if(f.getSince() != "Todo"){query.setString(i, f.getSince()); i++;}
	        if(f.getUntil() != "Todo" ){query.setString(i, f.getUntil());}
	        
	        ResultSet result = query.executeQuery();
        	
			//PROCESO RESULTADOS
	        while(result.next()){
	        	Notification tmp = new Notification(
		        			result.getInt("id_notification"),
		        			new Child(result.getInt("id_child"), result.getString("name")),
		        			new Context(result.getInt("id_context"), result.getString("Dcontext")),
		        			new Category(result.getInt("id_category"), result.getString("Dcategory")),
		        			new Tag(result.getInt("id_tag"), result.getString("Dtag")),
		        			new Pictogram(result.getInt("id_pictogram"), result.getString("content")),
		        			new Date(result.getLong("sent_date")),
		        			new Date (result.getLong("received_date"))
	        			);
	        	resultList.add(tmp);
	        	}
			query.close();
			result.close();
			return resultList;
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
		
		
	}
	@Override
	public void update(Tag selectDelete) {
		DBConector conector = new DBConector();
		conector.connect();
		try {
			String sql = "UPDATE notification SET id_tag=null WHERE id_tag=? ";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1,selectDelete.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		

		
		
	}
	
	public List<Notification> getList() {
		DBConector conector = new DBConector();
		conector.connect();
		List<Notification> resultList = new ArrayList<Notification>();
		try {
			String sql="SELECT  id_notification, cat.id_category, cat.description as Dcategory, c.id_child, name, cont.id_context, cont.description as Dcontext, p.id_pictogram, p.content, t.id_tag, t.description as Dtag, sent_date, received_date" 
					+" FROM notification as n inner join category as cat on (n.id_category=cat.id_category)" 
	                   +" inner join child as c on (n.id_child=c.id_child)" 
	                   +" inner join context as cont on (n.id_context=cont.id_context)" 
	                   +" inner join pictogram as p on (n.id_pictogram=p.id_pictogram)"
	                   +" inner join tag as t on (n.id_tag=t.id_tag);";

		   PreparedStatement query = conector.getConnection().prepareStatement(sql);
	       ResultSet result = query.executeQuery();
			
			//PROCESO RESULTADOS
	        while(result.next()){resultList.add(new Notification(result.getInt("id_notification"),
	        													 new Child(result.getInt("id_child"), result.getString("name")),
	        													 new Context(result.getInt("id_context"), result.getString("Dcontext")),
	        													 new Category(result.getInt("id_category"), result.getString("Dcategory")),
	        													 new Tag(result.getInt("id_tag"), result.getString("Dtag")),
	        													 new Pictogram(result.getInt("id_pictogram"), result.getString("content")),
	        													 new Date(result.getLong("sent_date")),
	        													 new Date (result.getLong("received_date"))));}
			query.close();
			result.close();
			return resultList;
		} 
		catch (SQLException e) {e.printStackTrace(); return resultList;}
		finally{conector.close();}
		
		
	}
	
	
	



}
