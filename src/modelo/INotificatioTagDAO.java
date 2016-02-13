package modelo;

public interface INotificatioTagDAO {
	public String getList(int id_notification);
	public void save(int notification, int tag);
	public void delete(int tag);
	

}
