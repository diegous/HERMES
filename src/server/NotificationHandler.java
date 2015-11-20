package server;

import java.io.*;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class NotificationHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		/*
		InputStream is = exchange.getRequestBody();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		StringBuffer stringBuffer = new StringBuffer();

		
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
*/
		System.out.println("oooo");
		
		
		String response = "OK";
		exchange.sendResponseHeaders(200, (long)response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}

}
