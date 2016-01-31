package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Category;
import entities.Child;
import entities.Context;
import entities.Notification;
import entities.Pictogram;
import entities.Tag;

public class MonitorInformationDAO implements IMonitorInformationDAO {
	
	//SINGLETON 
	private static MonitorInformationDAO monitorInformationDAO = null;
	private MonitorInformationDAO(){}
	public static MonitorInformationDAO getMonitorInformationDAO(){
		if(monitorInformationDAO ==  null){monitorInformationDAO = new MonitorInformationDAO();}
		return monitorInformationDAO;
	}
			
	//METHODS
	
	public MonitorInformation getMonitorInformation() {
		List<Child> child= FactoriaDAO.getChildDAO().getList();
		List<Context> context= FactoriaDAO.getContextDAO().getList();
		List<Category> category= FactoriaDAO.getCategoryDAO().getList(); 
		List<Tag> tag= FactoriaDAO.getTagDAO().getList(); 
		List<Pictogram> pictogram= FactoriaDAO.getPictogramDAO().getList();
		Filter filter=new Filter();
		List<Notification> notification= FactoriaDAO.getNotificationDAO().getList();
		
		List<Date> fechas=FactoriaDAO.getNotificatioTagDAO().getListFechas();
		if((fechas!=null)){
			List<Date> f = new ArrayList<Date>();
			f.add(fechas.get(0));
			String fechaActual=fechas.get(0).toString();
			String fecha;
			for(int i=1; i<fechas.size();i++){
				fecha=fechas.get(i).toString();
				if(!fechaActual.equals(fecha)){
					f.add(fechas.get(i));
					fechaActual=fecha;
				}
			}
			fechas=f;
		}
			
		MonitorInformation mi = new MonitorInformation(child, context, category, tag, pictogram, notification,filter, fechas);
		
		return mi;
	}
}
