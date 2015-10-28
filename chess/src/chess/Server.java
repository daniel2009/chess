package chess;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
public class Server extends JFrame implements ActionListener {
	JLabel jlPort = new JLabel("Port #");
	JTextField jtfPort = new JTextField("30");
	JButton jbStart = new JButton("Start");
	JButton jbStop = new JButton("Stop");
	JPanel panel = new JPanel();
	JList<String> jlUserOnline = new JList<String>();
	JScrollPane DisplayPanel = new JScrollPane(jlUserOnline);
	JSplitPane spliter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, DisplayPanel, panel);
	ServerSocket ss;
	ServerThread st;
	Vector onlineList = new Vector();
	
	public Server() {
		this.initialComponent();
		this.addListener();
		this.initialFrame();
	}
	
	public void initialComponent() {
		panel.setLayout(null);
		jlPort.setBounds(20, 20, 50, 20);
		panel.add(jlPort);
		this.jtfPort.setBounds(85, 20, 60, 20);
		panel.add(jtfPort);
		this.jbStart.setBounds(18, 50, 60, 20);
		panel.add(jbStart);
		this.jbStop.setBounds(85, 50, 60, 20);
		panel.add(jbStop);
		this.jbStop.setEnabled(false);
	}
	
	public void addListener() {
		this.jbStart.addActionListener(this);
		this.jbStop.addActionListener(this);
	}
	
	public void initialFrame() {
		this.setTitle("ChessServer");
		this.setIconImage(new ImageIcon("ico.gif").getImage());
		this.add(spliter);
		spliter.setDividerLocation(250);
		spliter.setDividerSize(4);
		this.setBounds(20, 20, 420, 320);
		this.setVisible(true);
		this.addWindowListener(
				new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (st == null) {
					System.exit(0);
					return;
				}
				try {
					Vector v = onlineList;
					int size = v.size();
					for (int i = 0; i < size; i++) {
						ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
						tempSat.dout.writeUTF("<#SERVER_DOWN#>");
						tempSat.flag = false;
					}
					st.flag = false;
					st = null;
					ss.close();
					v.clear();
					refreshList();
				}
				catch(Exception ee) {
					ee.printStackTrace();
				}
				System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.jbStart) {
			System.out.println("Server Starts");
			this.jbStop.setEnabled(true);
			this.jbStart.setEnabled(false);
		}
		if (e.getSource() == this.jbStop) {
			System.out.println("Server Shuts down");
			this.jbStop.setEnabled(false);
			this.jbStart.setEnabled(true);
		}
	}
	
	public void jbStart_event() {
		int port = 0;
		try {
			port = Integer.parseInt(this.jtfPort.getText().trim());
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(this, "Please input integer between 0 and 65535", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (port > 65535 || port < 0) {
			JOptionPane.showMessageDialog(this, "Please input integer between 0 and 65535", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			this.jbStart.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jbStop.setEnabled(true);
			ss = new ServerSocket(port);
			st = new ServerThread(this);
			st.start();
			JOptionPane.showMessageDialog(this,  "Server starts successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(this, "Server fails to start", "Errot", JOptionPane.ERROR_MESSAGE);
			this.jbStart.setEnabled(true);
			this.jtfPort.setEnabled(true);
			this.jbStop.setEnabled(false);;
		}
		
	}
	
	public void jbStop_event() {
		try {
			Vector v = this.onlineList;
			int size = v.size();
			for (int i = 0; i < size; i++) {
				ServerAgentThread tempSat = (ServerAgentThread)v.get(i);
				tempSat.dout.writeUTF("<#SERVER_DOWN#>");
				tempSat.flag = false;
			}
			st.flag = false;
			st = null;
			ss.close();
			refreshList();
			this.jbStart.setEnabled(true);
			this.jbStop.setEnabled(false);
			this.jtfPort.setEnabled(true);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void refreshList() {
		Vector<String> v = new Vector<String>();
		int size = this.onlineList.size();
		for (int i = 0; i < size; i++) {
			ServerAgentThread tempSat = (ServerAgentThread)this.onlineList.get(i);
			String temps = tempSat.sc.getInetAddress().toString();
			temps += "|" + tempSat.getName();
			v.add(temps);
		}
		this.jlUserOnline.setListData(v);
	}
	
	public static void main(String args[]) {
		new Server();
	}
}
