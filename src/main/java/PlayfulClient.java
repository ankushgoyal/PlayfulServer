package main.java;

import java.net.*;
import java.io.*;

public class PlayfulClient {
	
	public static void main(String args[]) throws Exception {
		
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			socket = new Socket("localhost", 4444);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch(UnknownHostException e) {
			System.out.println("Could not connect to localhost:4444");
			System.out.println(e.getMessage());
			System.exit(1);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String inMsg, outMsg;
		while((inMsg = in.readLine()) != null) {
			System.out.println("Server: " + inMsg);
			if(inMsg.toLowerCase().contains("bye")) {
				break;
			}
			outMsg = stdIn.readLine();
			if(outMsg != null) {
				System.out.println("Client: " + outMsg);
				out.println(outMsg);
			}
		}
		
		stdIn.close();
		out.close();
		in.close();
		socket.close();
	}

}
