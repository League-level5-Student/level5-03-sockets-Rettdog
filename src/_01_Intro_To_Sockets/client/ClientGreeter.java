package _01_Intro_To_Sockets.client;

import java.net.*;

import _02_Chat_Application.ChatApp;

import java.io.*;

public class ClientGreeter {
	DataOutputStream output;
	DataInputStream input;
	public String Input;
	public Socket sock;
	public static String username;
	
public ClientGreeter(String user) {	
	username = user;
}
public void start() {
//#1 192.168.7.71
	
	 String ip = "192.168.7.217";
        int port =80;
        //80
       try{
    	 sock = new Socket(ip,port);
         output = new DataOutputStream(sock.getOutputStream());
         
         input = new DataInputStream(sock.getInputStream());
         while(sock.isConnected()) {
        	 
         String in = input.readUTF();
         if(!in.equals("")) {
        
        	ChatApp.addMessage(in,false);
        	System.out.println(in);
        	
         }
         }
         sock.close();
    }catch(IOException e) {
    	e.printStackTrace();
    }
   }

public void send(String text) {
	try {
		//System.out.println(username);
		output.writeUTF(username+": "+text);
		
		ChatApp.addMessage(username+": "+text,true);
		
		System.out.println("You: "+text);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
