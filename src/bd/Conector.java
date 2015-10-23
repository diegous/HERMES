package bd;

import java.sql.*;

public class Conector {
	String url = "C:\\Users\\Carolina\\Desktop\\AyED - 2014\\workspace\\HERMES";
	Connection connect;

	public void connect(){
		 try {
		     connect = DriverManager.getConnection("jdbc:sqlite:"+url);
		     if (connect!=null) {
		         System.out.println("Conectado");
		     }
		 }catch (SQLException ex) {
		     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
		 }
		}
		 public void close(){
		        try {
		            connect.close();
		        } catch (SQLException ex) {
		            System.err.println(ex);
		        }
		 }
}
