package main.java;

import java.net.*;
import java.io.*;

public class PlayfulServerThread extends Thread {
	
	private Socket socket = null;
	
	public PlayfulServerThread(Socket socket) {
		super("PlayfulServerThread");
		this.socket = socket;
	}
	
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String inMsg, outMsg;
			PlayfulProtocol pp = new PlayfulProtocol();
			outMsg = pp.processInput(null);
			out.println(outMsg);
			while((inMsg = in.readLine()) != null) {
				outMsg = pp.processInput(inMsg);
				out.println(outMsg);
				if(outMsg.contains("Bye")) {
					break;
				}
			}
			
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
