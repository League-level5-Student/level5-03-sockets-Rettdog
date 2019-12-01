package _02_Chat_Application;

import java.io.IOException;

import javax.swing.JOptionPane;

import _01_Intro_To_Sockets.server.ServerGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	ServerGreeter server;
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		System.out.println(response);
		//no is 1
		//yes is 0
		if(response==0) {
			try {
				server = new ServerGreeter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			server.run();
		}else{
			String ip = JOptionPane.showInputDialog("Enter the IP Address");
			String port = JOptionPane.showInputDialog("Enter the port number");
		}
	}
}
