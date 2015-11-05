package modelo;

public class FactoriaDAO {
	
	//SINGLETON 
	private static FactoriaDAO factoria = null;
	
	private FactoriaDAO(){}
	
	public static FactoriaDAO getFactoriaDAO(){
		if(factoria ==  null){factoria = new FactoriaDAO();}
		return factoria;
	}
	
	//METHODS
	public static IDAO<Child> getChildDAO(){return ChildDAO.getChildDAO();}
	public static IDAO<Category> getCategoryDAO(){return CategoryDAO.getCategoryDAO();}
	public static IDAO<Context> getContextDAO(){return ContextDAO.getContextDAO();}
	public static INotificationDAO getNotificationDAO(){return NotificationDAO.getNotificationDAO();}
	public static IDAO<Tag> getTagDAO(){return TagDAO.getTagDAO();}
	public static IDAO<Pictogram> getPictogramDAO(){return PictogramDAO.getPictogramDAO();}
	public static IMonitorInformationDAO getMonitorInformationDAO(){return MonitorInformationDAO.getMonitorInformationDAO();};
	public static INotificatioTagDAO getNotificatioTagDAO(){ return NotificatioTagDAO.getNotificatioTagDAO();};
}
