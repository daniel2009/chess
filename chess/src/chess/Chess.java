package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Chess extends JFrame implements ActionListener {
	public static final Color backgroundColor = new Color(245, 250, 160);
	public static final Color focusBackground = new Color(242, 242, 242);
	public static final Color focusChar = new Color(96, 95, 91);
	public static final Color color1 = new Color(249, 183, 173);
	public static final Color color2 = Color.white;
	JLabel jlHost = new JLabel("Host");
	JLabel jlPort = new JLabel("Port");
	JLabel jlNickName = new JLabel("NickName");
	JTextField jtfHost = new JTextField("192.168.1.171");
	JTextField jtfPort = new JTextField("30");
	JTextField jtfNickName = new JTextField("New Player");
	JButton jbConnect = new JButton("CONNECT");
	JButton jbDisconnect = new JButton("DISCONNECT");
	JButton jbFail = new JButton("SURRENDER");
	JButton jbChallenge = new JButton("CHALLENGE");
	JComboBox jcbNickList = new JComboBox();
	JButton jbYChallenge = new JButton("ACCEPT");
	JButton jbNChallenge = new JButton("REJECT");
	
	int width = 60;
	ChessPiece[][] chessPiece = new ChessPiece[9][10];
	ChessBoard chessBoard = new ChessBoard(chessPiece, width, this);
	JPanel jpy = new JPanel();
	JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chessBoard, jpy);
	boolean referee = false;
	int color = 0;
	Socket sc;
	ClientAgentThread cat;
	
	public Chess() {
		this.initialComponent();
		this.addListener();
		this.initialState();
		this.initialChessPiece();
		this.initialFrame();
	}
	
	public void initialComponent() {
		jpy.setLayout(null);
		this.jlHost.setBounds(10, 10, 80, 20);
		jpy.add(this.jlHost);
		this.jtfHost.setBounds(90, 10, 80, 20);
		jpy.add(this.jtfHost);
		this.jlPort.setBounds(10, 40, 80, 20);
		jpy.add(this.jlPort);
		this.jtfPort.setBounds(90, 40, 50, 20);
		jpy.add(this.jtfPort);
		this.jlNickName.setBounds(10, 70, 80, 20);
		jpy.add(this.jlNickName);
		this.jtfNickName.setBounds(90, 70, 80, 20);
		jpy.add(this.jtfNickName);
		this.jbConnect.setBounds(10, 100, 80, 20);
		jpy.add(this.jbConnect);
		this.jbDisconnect.setBounds(100, 100, 80, 20);
		jpy.add(this.jbDisconnect);
		this.jcbNickList.setBounds(5, 130, 130, 20);
		jpy.add(this.jcbNickList);
		this.jbChallenge.setBounds(10, 160, 80, 20);
		jpy.add(this.jbChallenge);
		this.jbFail.setBounds(100, 160, 80, 20);
		jpy.add(this.jbFail);
		this.jbYChallenge.setBounds(10, 190, 80, 20);
		jpy.add(this.jbYChallenge);
		this.jbNChallenge.setBounds(100, 190, 80, 20);
		jpy.add(this.jbNChallenge);
	}
	
	public void addListener() {
		this.jbConnect.addActionListener(this);
		this.jbDisconnect.addActionListener(this);
		this.jbChallenge.addActionListener(this);
		this.jbFail.addActionListener(this);
		this.jbYChallenge.addActionListener(this);
		this.jbNChallenge.addActionListener(this);
	}
	
	public void initialState() {
		this.jbDisconnect.setEnabled(false);
		this.jbChallenge.setEnabled(false);
		this.jbYChallenge.setEnabled(false);
		this.jbNChallenge.setEnabled(false);
		this.jbFail.setEnabled(false);
	}
	
	public void initialChessPiece() {
		chessPiece[0][0] = new ChessPiece(color1, "車", 0, 0);
		chessPiece[1][0] = new ChessPiece(color1, "馬", 1, 0);
		chessPiece[2][0] = new ChessPiece(color1, "相", 2, 0);
		chessPiece[3][0] = new ChessPiece(color1, "仕", 3, 0);
		chessPiece[4][0] = new ChessPiece(color1, "帥", 4, 0);
		chessPiece[5][0] = new ChessPiece(color1, "仕", 5, 0);
		chessPiece[6][0] = new ChessPiece(color1, "相", 6, 0);
		chessPiece[7][0] = new ChessPiece(color1, "馬", 7, 0);
		chessPiece[8][0] = new ChessPiece(color1, "車", 8, 0);
		chessPiece[1][2] = new ChessPiece(color1, "砲", 1, 2);
		chessPiece[7][2] = new ChessPiece(color1, "砲", 7, 2);
		chessPiece[0][3] = new ChessPiece(color1, "兵", 0, 3);
		chessPiece[2][3] = new ChessPiece(color1, "兵", 2, 3);
		chessPiece[4][3] = new ChessPiece(color1, "兵", 4, 3);
		chessPiece[6][3] = new ChessPiece(color1, "兵", 6, 3);
		chessPiece[8][3] = new ChessPiece(color1, "兵", 8, 3);
		chessPiece[0][9] = new ChessPiece(color2, "車", 0, 9);
		chessPiece[1][9] = new ChessPiece(color2, "馬", 1, 9);
		chessPiece[2][9] = new ChessPiece(color2, "象", 2, 9);
		chessPiece[3][9] = new ChessPiece(color2, "士", 3, 9);
		chessPiece[4][9] = new ChessPiece(color2, "将", 4, 9);
		chessPiece[5][9] = new ChessPiece(color2, "士", 5, 9);
		chessPiece[6][9] = new ChessPiece(color2, "象", 6, 9);
		chessPiece[7][9] = new ChessPiece(color2, "馬", 7, 9);
		chessPiece[8][9] = new ChessPiece(color2, "車", 8, 9);
		chessPiece[1][7] = new ChessPiece(color2, "炮", 1, 7);
		chessPiece[7][7] = new ChessPiece(color2, "炮", 7, 7);
		chessPiece[0][6] = new ChessPiece(color2, "卒", 0, 6);
		chessPiece[2][6] = new ChessPiece(color2, "卒", 2, 6);
		chessPiece[4][6] = new ChessPiece(color2, "卒", 4, 6);
		chessPiece[6][6] = new ChessPiece(color2, "卒", 6, 6);
		chessPiece[8][6] = new ChessPiece(color2, "卒", 8, 6);	
	}
	
	public void initialFrame() {
		this.setTitle("Chess");
		Image image = new ImageIcon("ico.gif").getImage();
		this.setIconImage(image);
		this.add(this.jsp);
		jsp.setDividerLocation(730);
		jsp.setDividerSize(4);
		this.setBounds(30, 30, 930, 730);
		this.setVisible(true);
		this.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						if (cat == null) {
							System.exit(0);
							return;
						}
						try {
							if (cat.opponent != null) {
								try {
									cat.dout.writeUTF("<#SURRENDER#>" + cat.opponent);
								}
								catch(Exception ee) {
									ee.printStackTrace();
								}
							}
							cat.dout.writeUTF("<#CLIENT_LEAVE#>");
							cat.flag = false;
							cat = null;
						}
						catch(Exception ee) {
							ee.printStackTrace();
						}
						System.exit(0);
					}
				});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.jbConnect) {
			this.jbConnect_event();
		}
		else if (e.getSource() == this.jbDisconnect) {
			this.jbDisconnect_event();
		}
		else if (e.getSource() == this.jbChallenge) {
			this.jbChallenge_event();
		}
		else if (e.getSource() == this.jbFail) {
			this.jbFail_event();
		}
		else if (e.getSource() == this.jbYChallenge) {
			this.jbYChallenge_event();
		}
		else if (e.getSource() == this.jbNChallenge) {
			this.jbNChallenge_event();
		}
	}
	
	public void jbConnect_event() {
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
		String name = this.jtfNickName.getText().trim();
		if (name == null || name.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please input a valid name", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			sc = new Socket(this.jtfHost.getText().trim(), port);
			cat = new ClientAgentThread(this);
			cat.start();
			this.jtfHost.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jtfNickName.setEnabled(false);
			this.jbConnect.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallenge.setEnabled(true);
			this.jbFail.setEnabled(false);
			this.jbYChallenge.setEnabled(false);
			this.jbNChallenge.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Successfully connected!", "NOTE", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(this, "Failed to connect", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public void jbDisconnect_event() {
		try {
			this.cat.dout.writeUTF("<#CLIENT_LEAVE#>");
			this.cat.flag = false;
			this.cat = null;
			this.jtfHost.setEnabled(true);
			this.jtfPort.setEnabled(true);
			this.jtfNickName.setEnabled(true);
			this.jbConnect.setEnabled(true);
			this.jbDisconnect.setEnabled(false);
			this.jbChallenge.setEnabled(false);
			this.jbFail.setEnabled(false);
			this.jbYChallenge.setEnabled(false);
			this.jbNChallenge.setEnabled(false);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void jbChallenge_event() {
		Object o = this.jcbNickList.getSelectedItem();
		if (o == null || ((String)o).length() == 0) {
			JOptionPane.showMessageDialog(this, "Please select a player", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			String name2 = (String)this.jcbNickList.getSelectedItem();
			try {
				this.jtfHost.setEnabled(false);
				this.jtfPort.setEnabled(false);
				this.jtfNickName.setEnabled(false);
				this.jbConnect.setEnabled(false);
				this.jbDisconnect.setEnabled(false);
				this.jbChallenge.setEnabled(false);
				this.jbFail.setEnabled(false);
				this.jbYChallenge.setEnabled(false);
				this.jbNChallenge.setEnabled(false);
				this.cat.opponent = name2;
				this.cat.dout.writeUTF("<#CHALLENGE#>" + name2);
				this.referee = true;
				this.color = 0;
				JOptionPane.showMessageDialog(this, "Waiting for reply", "Note", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
	}
	
	public void jbYChallenge_event() {
		try {
			this.cat.dout.writeUTF("<#AGREE#>"+this.cat.opponent);
			this.referee = false;
			this.color = 1;
			this.jtfHost.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jtfNickName.setEnabled(false);
			this.jbConnect.setEnabled(false);
			this.jbDisconnect.setEnabled(false);
			this.jbChallenge.setEnabled(false);
			this.jbFail.setEnabled(true);
			this.jbYChallenge.setEnabled(false);
			this.jbNChallenge.setEnabled(false);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void jbNChallenge_event() {
		try {
			this.cat.dout.writeUTF("<#DISAGREE#>" + this.cat.opponent);
			this.cat.opponent = null;
			this.jtfHost.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jtfNickName.setEnabled(false);
			this.jbConnect.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallenge.setEnabled(true);
			this.jbFail.setEnabled(false);
			this.jbYChallenge.setEnabled(false);
			this.jbNChallenge.setEnabled(false);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void jbFail_event() {
		try {
			this.cat.dout.writeUTF("<#SURRENDER#>" + this.cat.opponent);
			this.cat.opponent = null;
			this.color = 0;
			this.referee = false;
			this.next();
			this.jtfHost.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jtfNickName.setEnabled(false);
			this.jbConnect.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallenge.setEnabled(true);
			this.jbFail.setEnabled(false);
			this.jbYChallenge.setEnabled(false);
			this.jbNChallenge.setEnabled(false);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void next() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				this.chessPiece = null;
			}
		}
		this.referee = false;
		this.initialChessPiece();
		this.repaint();
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
