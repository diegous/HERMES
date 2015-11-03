package modelo;

import java.util.List;

public class MonitorInformationDAO implements IMonitorInformationDAO {
	
	//SINGLETON 
			private static MonitorInformationDAO monitorInformationDAO = null;
			private MonitorInformationDAO(){}
			public static MonitorInformationDAO getMonitorInformationDAO(){
				if(monitorInformationDAO ==  null){monitorInformationDAO = new MonitorInformationDAO();}
				return monitorInformationDAO;
			}
			
	//METHODS
	@Override
	public MonitorInformation getMonitorInformation() {
		List<Child> child= FactoriaDAO.getChildDAO().getList();
		List<Context> context= FactoriaDAO.getContextDAO().getList();
		List<Category> category= FactoriaDAO.getCategoryDAO().getList(); 
		List<Tag> tag= FactoriaDAO.getTagDAO().getList(); 
		List<Pictogram> pictogram= FactoriaDAO.getPictogramDAO().getList();
		Filter filter=new Filter();
		List<Notification> notification= FactoriaDAO.getNotificationDAO().getList(filter);
		MonitorInformation mi = new MonitorInformation(child, context, category, tag, pictogram, notification,filter);
		return mi;
	}


}
