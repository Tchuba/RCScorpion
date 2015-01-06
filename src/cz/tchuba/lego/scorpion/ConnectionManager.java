package cz.tchuba.lego.scorpion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {
	
	private final int PORT_NUMBER = 8080;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public void hostConnection() throws IOException{
		
		serverSocket = new ServerSocket(PORT_NUMBER);
		clientSocket = serverSocket.accept();
		
	}
	
	public void closeConnection(){
		try {
			clientSocket.getInputStream().close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InputStream getInputStream() throws IOException{
		return clientSocket.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException{
		return clientSocket.getOutputStream();
	}

}
