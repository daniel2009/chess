package chess;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class ServerAgentThread extends Thread {
	Server father;
	Socket sc;
	DataOutputStream dout;
	DataInputStream din;
	boolean flag = true;
	
	public ServerAgentThread(Server father, Socket sc) {
		this.father = father;
		this.sc = sc;
		try {
			din = new DataInputStream(sc.getInputStream());
			dout = new DataOutputStream(sc.getOutputStream());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (flag) {
			try {
				String msg = din.readUTF().trim();
				if (msg.startsWith("<#NICK_NAME#>")) {
					this.nick_name(msg);
				}
				else if (msg.startsWith("<#CLIENT_LEAVE#>")) {
					this.client_leave(msg);
				}
				else if (msg.startsWith("<#CHALLENGE#>")) {
					this.challenge(msg);
				}
				else if (msg.startsWith("<#AGREE#>")) {
					this.agree(msg);
				}
				else if (msg.startsWith("<#DISAGREE#>")) {
					this.disagree(msg);
				}
				else if (msg.startsWith("<#BUSY#>")) {
					this.busy(msg);
				}
				else if (msg.startsWith("<#MOVE#>")) {
					this.move(msg);
				}
				else if (msg.startsWith("<#SURRENDER#>")) {
					this.surrender(msg);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void nick_name(String msg) {
		try {
			String name = msg.substring(13);
			this.setName(name);
			Vector v = father.onlineList;
			boolean isRepeatedName = false;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					isRepeatedName = true;
					break;
				}
			}
			if (isRepeatedName == true) {
				dout.writeUTF("<#NAME_REPEATED#>");
				din.close();
				dout.close();
				sc.close();
				flag = false;
			}
			else {
				v.add(this);
				father.refreshList();
				String nickListMsg = "";
				StringBuilder nickListMsgSb = new StringBuilder();
				size = v.size();
				for (int i = 0; i < size; i++) {
					ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
					nickListMsgSb.append("!");
					nickListMsgSb.append(tempSat.getName());
				}
				nickListMsgSb.append("<#NICK_LIST#>");
				nickListMsg = nickListMsgSb.toString();
				Vector tempv = father.onlineList;
				size = tempv.size();
				for (int i = 0; i < size; i++) {
					ServerAgentThread tempSat = (ServerAgentThread)tempv.get(i);
					tempSat.dout.writeUTF(nickListMsg);
					if (tempSat != this) {
						tempSat.dout.writeUTF("<#MSG#>" + this.getName() + "is now online....");
					}
				}
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void client_leave(String msg) {
		try {
			Vector tempv = father.onlineList;
			tempv.remove(this);
			int size = tempv.size();
			String nl = "<#NICK_LIST#>";
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)tempv.get(i);
				tempSat.dout.writeUTF("<#MSG#>" + this.getName() + "is offline....");
				nl = nl + "|" + tempSat.getName();
			}
			
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)tempv.get(i);
				tempSat.dout.writeUTF(nl);
			}
			this.flag = false;
			father.refreshList();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void challenge(String msg) {
		try {
			String name1 = this.getName();
			String name2 = msg.substring(13);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name2)) {
					tempSat.dout.writeUTF("<#CHALLENGE#>" + name1);
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void agree(String msg) {
		try {
			String name = msg.substring(11);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					tempSat.dout.writeUTF("<#AGREE#>");
					break;
				}
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disagree(String msg) {
		try {
			String name = msg.substring(13);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					tempSat.dout.writeUTF("<#DISAGREE#>");
					break;
				}
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void busy(String msg) {
		try {
			String name = msg.substring(8);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					tempSat.dout.writeUTF("<#BUSY#>");
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void move(String msg) {
		try {
			String name = msg.substring(8, msg.length() - 4);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					tempSat.dout.writeUTF(msg);
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void surrender(String msg) {
		try {
			String name = msg.substring(10);
			Vector v = father.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				if (tempSat.getName().equals(name)) {
					tempSat.dout.writeUTF(msg);
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
