package _01_Intro_To_Sockets.client;

import java.net.*;
import java.io.*;

public class ClientGreeter {
	DataOutputStream output;
	DataInputStream input;
	String Input;
	public Socket sock;
public void start() {

	 String ip = "192.168.7.71";
        int port =80;
       try{
    	 sock = new Socket(ip,port);
         output = new DataOutputStream(sock.getOutputStream());
         
         input = new DataInputStream(sock.getInputStream());
         while(sock.isConnected()) {
        	 
         String in = input.readUTF();
         if(!in.equals("")) {
        	Input = in;
        	System.out.println(in);
         }
         }
         sock.close();
    }catch(IOException e) {
    	
    }
   }

public void send(String text) {
	try {
		output.writeUTF(text);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
