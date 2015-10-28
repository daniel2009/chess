package chess;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
	Server father;
	ServerSocket ss;
	boolean flag = true;
	public ServerThread(Server father) {
		this.father = father;
		this.ss = father.ss;
	}
	
	public void run() {
		while (flag) {
			try {
				Socket sc = ss.accept();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
