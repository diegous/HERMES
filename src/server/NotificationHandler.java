package server;

import java.io.*;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.Controller;
import modelo.SynchronizerNotifications;

public class NotificationHandler implements HttpHandler {
	
	private SynchronizerNotifications sn;
	
	public NotificationHandler(SynchronizerNotifications sn2){
		this.sn=sn2;
	}
	
	
	public void handle(HttpExchange exchange) throws IOException {
		
		
		InputStream is = exchange.getRequestBody();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		StringBuffer stringBuffer = new StringBuffer();
		
		while ((inputLine = bufferedReader.readLine()) != null) {stringBuffer.append(inputLine);}
		bufferedReader.close();

		
		
		
		
		//RESPUESTA
		String response = "OK";
		exchange.sendResponseHeaders(200, (long)response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
		
		//AGREGAR NOTIFICACION
		Controller controller = new Controller();
		controller.processIncomingJson(stringBuffer.toString(), sn);
		
	
	}

}
