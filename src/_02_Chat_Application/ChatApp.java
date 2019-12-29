package _02_Chat_Application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _01_Intro_To_Sockets.client.ClientGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp implements ActionListener, KeyListener {
	static JFrame frame;
	static JPanel panel;
	static JPanel textPanel;
	static JLabel textView;
	static JTextField textInput;
	static JButton sender;
	static ClientGreeter client;
	static ArrayList<String> messages = new ArrayList<String>();
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static ArrayList<Boolean> colors = new ArrayList<Boolean>();
	public static void main(String[] args) {
		ChatApp app = new ChatApp();
		app.makeFrame();
		app.start();
	}
	
	public void makeFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500,200));
		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(500,800));
		
		textInput = new JTextField();
		textInput.addKeyListener(this);
		textInput.setPreferredSize(new Dimension(100,20));
		for(int i = 0;i<20;i++) {
			labels.add(new JLabel(""+i));
			labels.get(i).setPreferredSize(new Dimension(480,35));
			textPanel.add(labels.get(i));
		}
		sender = new JButton("Send");
		sender.addActionListener(this);
		
		panel.add(textPanel);
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
		String name = JOptionPane.showInputDialog("Username:");
		if(name.length()>15) {
			name = name.substring(0,15);
		}
			client = new ClientGreeter(name);
			client.start();
		
			while(client.sock.isConnected()) {
				//work here
				
				
			}
			
	}
	public static void addMessage(String in, Boolean you) {
		/*int lineLength = 70;
		System.out.println(in.length()-client.username.length());
		if(in.length()>lineLength) {
			in = "<html>"+in.substring(0,lineLength)+"<br>"+in.substring(lineLength, in.length())+"</html>";
		}*/
		int length = in.length();
		if(length-70>0) {
			addMessage(in.substring(70,in.length()),you);
			in = in.substring(0,70);
		}
		
		messages.add(in);
		colors.add(you);
		if(messages.size()>20) {
			messages.remove(0);
			colors.remove(0);
		}
		drawText(messages);
	}
	
	
	
	public static void drawText(ArrayList<String> messages) {
		for(int i = messages.size()-1;i>=0;i--) {
			labels.get(20-messages.size()+i).setText(messages.get(i));
			labels.get(20-messages.size()+i).setOpaque(true);
			if(colors.get(i)) {
			labels.get(20-messages.size()+i).setBackground(new Color(102,255,102));
			}else {
			labels.get(20-messages.size()+i).setBackground(new Color(102,255,255));	
			}
		}
		frame.pack();
		textPanel.paintImmediately(0, 0, 500, 1000);
	}

	public void click() {
		client.send(textInput.getText());
		textInput.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(sender)) {
			client.send(textInput.getText());
			textInput.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10) {
			click();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
