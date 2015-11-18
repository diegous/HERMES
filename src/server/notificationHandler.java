package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.ws.spi.http.HttpExchange;
import javax.xml.ws.spi.http.HttpHandler;

public class notificationHandler extends HttpHandler {
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		InputStream is = exchange.getRequestBody();
		read(is); 
		String response = "Esta es la respuesta";
		
		 
		exchange.sendResponseHeaders(200, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}

}
