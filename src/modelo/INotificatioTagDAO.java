package modelo;

import java.util.Date;
import java.util.List;

public interface INotificatioTagDAO {
	public String getList(int id_notification);
	public void save(int notification, int tag);
	public void delete(int tag);
	public List<Date> getListFechas();
	public Date getFecha(Date sent);
	

}
