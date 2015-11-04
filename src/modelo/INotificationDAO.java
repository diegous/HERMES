package modelo;

import java.util.List;

public interface INotificationDAO {
	public void save(Notification n);
	public List<Notification> getList(Filter f);
	void update(Tag selectDelete);
	public List<Notification> getList();
	public void addTag(String selectNotification, String selectAsignar);
	

}
