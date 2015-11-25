package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;
import modelo.SynchronizerNotifications;

public class ServerHandlerThread extends Thread {
	private SynchronizerNotifications sn;
	private int portNumber;
	
	public ServerHandlerThread(SynchronizerNotifications sn, int portNumber){
		super("server");
		this.sn = sn;
		this.portNumber = portNumber;
	}
	
	public void run(){
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(portNumber), 0);
	 	    server.createContext("/get", new NotificationHandler(sn));
	    	server.setExecutor(Executors.newCachedThreadPool());
	    	server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
