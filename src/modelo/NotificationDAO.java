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
import entities.Tag;


public class NotificationDAO implements INotificationDAO {
	private String textForAll = "Todos";

	//SINGLETON 
	private static NotificationDAO notificationDAO = null;
	private NotificationDAO(){}
	public static NotificationDAO getNotificationDAO(){
		if(notificationDAO ==  null)
			notificationDAO = new NotificationDAO();
		return notificationDAO;
	}		
	
	//METHODS
	public void save(Notification n) {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		try {
			String sql = "INSERT INTO notification (id_category,id_child,id_context, id_pictogram,sent_date, received_date) VALUES (?,?,?,?,?,?);";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, n.getCategory().getId());
			preparedStatement.setInt(2, n.getChild().getId());
			preparedStatement.setInt(3, n.getContext().getId());
			preparedStatement.setInt(4, n.getPictogram().getId());
			preparedStatement.setLong(5, n.getSent().getTime());
			preparedStatement.setLong(6, n.getRecieved().getTime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
		
	}	
	
	@Override
	public List<Notification> getList(Filter f) {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		List<Notification> resultList = new ArrayList<Notification>();
		try {
			
			//CONSULTA
			String sql="SELECT  n.id_notification, cat.id_category, cat.description as Dcategory, c.id_child, name, cont.id_context, cont.description as Dcontext, p.id_pictogram, p.content, sent_date, received_date, nt.id_tag " 
					+" FROM notification as n inner join category as cat on (n.id_category=cat.id_category)" 
	                   +" inner join child as c on (n.id_child=c.id_child)" 
	                   +" inner join context as cont on (n.id_context=cont.id_context)" 
	                   +" inner join pictogram as p on (n.id_pictogram=p.id_pictogram)"
	                   +" left join notificationtag as nt on (n.id_notification=nt.id_notification)"
	                   +" WHERE 1=1";
	        if(f.getCategory() != textForAll){sql=sql+" and cat.description=?";}
	        if(f.getChild() != textForAll){sql=sql+" and name=?";}
	        if(f.getContext() != textForAll){sql=sql+" and cont.description=?";}
	        if(f.getPictogram() != textForAll){sql=sql+" and p.content=?";}
	        if(f.getTag() != textForAll){sql=sql+" and nt.id_tag=?";}
	      
	        if(f.getSince() != 0){sql=sql+" and sent_date>=?";}
	        //La fecha "hasta" va siempre
	        sql=sql+" and sent_date<=?";
	        
	        sql=sql+" GROUP BY n.id_notification";
	        sql=sql+" ORDER BY n.sent_date DESC";
	        sql=sql+" ;";
	       
	        PreparedStatement query = conector.getConnection().prepareStatement(sql);
	        
	        //PARAMETROS
	        int i=1;
	        if(f.getCategory() != textForAll){query.setString(i, f.getCategory()); i++;}
	        if(f.getChild() != textForAll){query.setString(i, f.getChild()); i++;}
	        if(f.getContext() != textForAll){query.setString(i, f.getContext()); i++;}
	        if(f.getPictogram() != textForAll){query.setString(i, f.getPictogram()); i++;}
	        if(f.getTag() != textForAll){
	        	Integer id = FactoriaDAO.getTagDAO().getByText(f.getTag()).getId();
	        	query.setInt(i, id);
	        	i++;
	        }
	        if(f.getSince() != 0){query.setLong(i, f.getSince()); i++;}
	        //La fecha "hasta" de búsqueda va siempre
	        // Hay que sumarle 86400000 (un día en milisegundos) para que la busqueda incluya al dia seleccionado 
	        query.setLong(i, f.getUntil()+86400000);
	        
	        ResultSet result = query.executeQuery();
        	
			//PROCESO RESULTADOS
	        while(result.next()){
	        	Notification tmp = new Notification(
		        			result.getInt("id_notification"),
		        			new Child(result.getInt("id_child"), result.getString("name")),
		        			new Context(result.getInt("id_context"), result.getString("Dcontext")),
		        			new Category(result.getInt("id_category"), result.getString("Dcategory")),
		        			FactoriaDAO.getNotificatioTagDAO().getList(result.getInt("id_notification")),
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
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		try {
			String sql = "UPDATE notification SET id_tag=0 WHERE id_tag=? ";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1,selectDelete.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
	}
	
	public List<Notification> getList() {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		List<Notification> resultList = new ArrayList<Notification>();
		try {
			String sql="SELECT  id_notification, cat.id_category, cat.description as Dcategory, c.id_child, name, cont.id_context, cont.description as Dcontext, p.id_pictogram, p.content, sent_date, received_date" 
					+" FROM notification as n inner join category as cat on (n.id_category=cat.id_category)" 
	                   +" inner join child as c on (n.id_child=c.id_child)" 
	                   +" inner join context as cont on (n.id_context=cont.id_context)" 
	                   +" inner join pictogram as p on (n.id_pictogram=p.id_pictogram)"
	       	           +" ORDER BY n.sent_date DESC;"
	                   + ";";
	                 

		   PreparedStatement query = conector.getConnection().prepareStatement(sql);
	       ResultSet result = query.executeQuery();
			
			//PROCESO RESULTADOS
	        while(result.next()){resultList.add(new Notification(result.getInt("id_notification"),
	        													 new Child(result.getInt("id_child"), result.getString("name")),
	        													 new Context(result.getInt("id_context"), result.getString("Dcontext")),
	        													 new Category(result.getInt("id_category"), result.getString("Dcategory")),
	        													 FactoriaDAO.getNotificatioTagDAO().getList(result.getInt("id_notification")),
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
	
	@Override
	public void addTag(String selectNotification, String selectAsignar) {
		DBConector conector = DBConector.getDBConector();
		conector.connect();
		try {
			String sql = "UPDATE notification SET id_tag=? WHERE id_notification=? ";
			PreparedStatement preparedStatement = conector.getConnection().prepareStatement(sql);
			if(selectAsignar==""){
				
			}
			preparedStatement.setInt(1,FactoriaDAO.getTagDAO().getByText(selectAsignar).getId());
			preparedStatement.setInt(2, Integer.parseInt(selectNotification));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		finally{conector.close();}
	}
}

