package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _01_Intro_To_Sockets.client.ClientGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp implements ActionListener {
	JFrame frame;
	JPanel panel;
	JLabel textView;
	JTextField textInput;
	JButton sender;
	ClientGreeter client;
	public static void main(String[] args) {
		ChatApp app = new ChatApp();
		app.makeFrame();
		app.start();
	}
	
	public void makeFrame() {
		frame = new JFrame();
		panel = new JPanel();
		textView = new JLabel();
		textView.setPreferredSize(new Dimension(500,800));
		textInput = new JTextField();
		textInput.setPreferredSize(new Dimension(100,20));
		sender = new JButton("Send");
		sender.addActionListener(this);
		panel.add(textView);
		panel.add(textInput);
		panel.add(sender);
		frame.add(panel);
		frame.setPreferredSize(new Dimension(500,1000));
		frame.setVisible(true);
		frame.pack();
	}
	
	public void start() {
		
			//String ip = JOptionPane.showInputDialog("Enter the IP Address");
			//int port = Integer.parseInt(JOptionPane.showInputDialog("Enter the port number"));
			client = new ClientGreeter();
			client.start();
		
			while(client.sock.isConnected()) {
				//work here
				//I need to make the sysos of input go into the jframe
			}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(sender)) {
			client.send(textInput.getText());
			textInput.setText("");
		}
	}
}
