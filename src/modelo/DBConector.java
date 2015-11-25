package modelo;

import java.sql.*;

public class DBConector {
	private String url = "hermes.db";
	private Connection connection;
	private static DBConector dbconector;
	
	public static DBConector getDBConector() {
		if (null == dbconector){
			dbconector = new DBConector();
		}
		return dbconector;
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
		} catch (SQLException ex) {
			System.err.println("DB connection failed: " + ex.getMessage());
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void close() {
		try {this.connection.close();} 
		catch (SQLException ex) {System.err.println(ex);}
	}

	public synchronized Connection getConnection() {return this.connection;}

	public void setConnection(Connection connection) {this.connection = connection;}
}
