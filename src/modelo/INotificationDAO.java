package modelo;

import java.util.List;

public interface INotificationDAO {
	public void save(Notification n);
	public List<Notification> getList(Filter f);
	

}
