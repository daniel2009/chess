package chess;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class ClientAgentThread extends Thread {
	Chess father;
	boolean flag = true;
	DataOutputStream dout;
	DataInputStream din;
	String opponent = null;
	public ClientAgentThread(Chess father) {
		this.father = father;
		System.out.print(1);
		try {
			din = new DataInputStream(father.sc.getInputStream());
			dout = new DataOutputStream(father.sc.getOutputStream());
			
			String name = father.jtfNickName.getText().trim();
			dout.writeUTF("<#NICK_NAME#>" + name);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (flag) {
			try {
				String msg = din.readUTF().trim();
				if (msg.startsWith("<#NAME_REPEATED#>")) {
					this.name_repeated();
				}
				else if (msg.startsWith("<#NICK_LIST#>")) {
					this.nick_list(msg);
				}
				else if (msg.startsWith("<#SERVER_DOWN#>")) {
					this.server_down();
				}
				else if (msg.startsWith("<#CHALLENGE#>")) {
					this.challenge(msg);
				}
				else if (msg.startsWith("<#AGREE#>")) {
					this.agree();
				}
				else if (msg.startsWith("<#DISAGREE#>")) {
					this.disagree();
				}
				else if (msg.startsWith("<#BUSY#>")) {
					this.busy();
				}
				else if (msg.startsWith("<#MOVE#>")) {
					this.move(msg);
				}
				else if (msg.startsWith("<#SURRENDER#>")) {
					this.surrender();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void name_repeated() {
		try {
			JOptionPane.showMessageDialog(this.father, "This player has logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
			din.close();
			dout.close();
			this.father.jtfHost.setEnabled(false);
			this.father.jtfPort.setEnabled(false);
			this.father.jtfNickName.setEnabled(false);
			this.father.jbConnect.setEnabled(false);
			this.father.jbDisconnect.setEnabled(true);
			this.father.jbChallenge.setEnabled(false);
			this.father.jbFail.setEnabled(false);
			this.father.jbYChallenge.setEnabled(false);
			this.father.jbNChallenge.setEnabled(false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nick_list(String msg) {
		String s = msg.substring(13);
		String[] na = s.split("\\|");
		Vector v = new Vector();
		for (int i = 0; i < na.length; i++) {
			if (na[i].trim().length() != 0 && (!na[i].trim().equals(father.jtfNickName.getText().trim()))) {
				v.add(na[i]);
			}
		}
		father.jcbNickList.setModel(new DefaultComboBoxModel(v));
	}
	
	public void server_down() {
		this.father.jtfHost.setEnabled(!false);
		this.father.jtfPort.setEnabled(!false);
		this.father.jtfNickName.setEnabled(!false);
		this.father.jbConnect.setEnabled(!false);
		this.father.jbDisconnect.setEnabled(true);
		this.father.jbChallenge.setEnabled(false);
		this.father.jbFail.setEnabled(false);
		this.father.jbYChallenge.setEnabled(false);
		this.father.jbNChallenge.setEnabled(false);
		this.flag = false;
		father.cat = null;
		JOptionPane.showMessageDialog(this.father, "Server Down!", "Note", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void challenge(String msg) {
		try {
			String name = msg.substring(13);
			if (this.opponent == null) {
				opponent = name;
				this.father.jtfHost.setEnabled(false);
				this.father.jtfPort.setEnabled(false);
				this.father.jtfNickName.setEnabled(false);
				this.father.jbConnect.setEnabled(false);
				this.father.jbDisconnect.setEnabled(!true);
				this.father.jbChallenge.setEnabled(false);
				this.father.jbFail.setEnabled(false);
				this.father.jbYChallenge.setEnabled(!false);
				this.father.jbNChallenge.setEnabled(!false);
				JOptionPane.showMessageDialog(this.father, opponent + "challenges you!!", "Note", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				this.dout.writeUTF("<#BUSY#>" + name);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void agree() {
		this.father.jtfHost.setEnabled(false);
		this.father.jtfPort.setEnabled(false);
		this.father.jtfNickName.setEnabled(false);
		this.father.jbConnect.setEnabled(false);
		this.father.jbDisconnect.setEnabled(!true);
		this.father.jbChallenge.setEnabled(false);
		this.father.jbFail.setEnabled(false);
		this.father.jbYChallenge.setEnabled(false);
		this.father.jbNChallenge.setEnabled(false);
		JOptionPane.showMessageDialog(this.father, "He accepts your challenge", "Note", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void disagree() {
		this.father.referee = false;
		this.father.color = 0;
		this.father.jtfHost.setEnabled(false);
		this.father.jtfPort.setEnabled(false);
		this.father.jtfNickName.setEnabled(false);
		this.father.jbConnect.setEnabled(false);
		this.father.jbDisconnect.setEnabled(true);
		this.father.jbChallenge.setEnabled(!false);
		this.father.jbFail.setEnabled(false);
		this.father.jbYChallenge.setEnabled(false);
		this.father.jbNChallenge.setEnabled(false);
		JOptionPane.showMessageDialog(this.father, "He does not accept your challenge", "Note", JOptionPane.INFORMATION_MESSAGE);
		this.opponent = null;
	}
	
	public void busy() {
		this.father.referee = false;
		this.father.color = 0;
		this.father.jtfHost.setEnabled(false);
		this.father.jtfPort.setEnabled(false);
		this.father.jtfNickName.setEnabled(false);
		this.father.jbConnect.setEnabled(false);
		this.father.jbDisconnect.setEnabled(true);
		this.father.jbChallenge.setEnabled(!false);
		this.father.jbFail.setEnabled(false);
		this.father.jbYChallenge.setEnabled(false);
		this.father.jbNChallenge.setEnabled(false);
		JOptionPane.showMessageDialog(this.father, "He is busy now!!!", "Note", JOptionPane.INFORMATION_MESSAGE);
		this.opponent = null;
	}
	
	public void surrender() {
		this.father.referee = false;
		this.father.color = 0;
		this.father.next();
		this.father.jtfHost.setEnabled(false);
		this.father.jtfPort.setEnabled(false);
		this.father.jtfNickName.setEnabled(false);
		this.father.jbConnect.setEnabled(false);
		this.father.jbDisconnect.setEnabled(true);
		this.father.jbChallenge.setEnabled(!false);
		this.father.jbFail.setEnabled(false);
		this.father.jbYChallenge.setEnabled(false);
		this.father.jbNChallenge.setEnabled(false);
		JOptionPane.showMessageDialog(this.father, "Congratulation!! Your Opponent has surrendered now!!!", "Note", JOptionPane.INFORMATION_MESSAGE);
		this.opponent = null;
	}
	
	public void move(String msg) {
		int length = msg.length();
		int startI = Integer.parseInt(msg.substring(length - 4, length - 3));
		int startJ = Integer.parseInt(msg.substring(length - 3, length - 2));
		int endI = Integer.parseInt(msg.substring(length - 2, length - 1));
		int endJ = Integer.parseInt(msg.substring(length - 1));
		this.father.chessBoard.move(startI, startJ, endI, endJ);
		this.father.referee = true;
	}
}
