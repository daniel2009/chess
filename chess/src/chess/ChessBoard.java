package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ChessBoard extends JPanel implements MouseListener {
	private int width;
	boolean focus = false;
	int jiang1_i = 4;
	int jiang1_j = 0;
	int jiang2_i = 4;
	int jiang2_j = 9;
	int startI = -1;
	int startJ = -1;
	int endI = -1;
	int endJ = -1;
	public ChessPiece[][] chessPiece;
	Chess chess = null;
	Regulation regulation;
	
	public ChessBoard(ChessPiece[][] chessPiece, int width, Chess chess){
		this.chess = chess;
		this.chessPiece = chessPiece;
		this.width = width;
		this.regulation = new Regulation(chessPiece);
		this.addMouseListener(this);
		this.setBounds(0,0,700,700);
		this.setLayout(null);
	}
	
	public void paint(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color c = g.getColor();
		g.setColor(Chess.backgroundColor);
		g.fill3DRect(60,30,580,630,false);
		g.setColor(Color.black);
		for(int i = 80; i <= 620; i += width) {
			g.drawLine(110,i,590,i);
		}
		g.drawLine(110,80,110,620);
		g.drawLine(590,80,590,620);
		for(int i = 170;i <= 530;i += width){
			g.drawLine(i,80,i,320);
			g.drawLine(i,380,i,620);
		}
		g.drawLine(290,80,410,200);
		g.drawLine(290,200,410,80);
		g.drawLine(290,500,410,620);
		g.drawLine(290,620,410,500);
		this.smallLine(g,1,2);
		this.smallLine(g,7,2);
		this.smallLine(g,0,3);
		this.smallLine(g,2,3);
		this.smallLine(g,4,3);
		this.smallLine(g,6,3);
		this.smallLine(g,8,3);
		this.smallLine(g,0,6);
		this.smallLine(g,2,6);
		this.smallLine(g,4,6);
		this.smallLine(g,6,6);
		this.smallLine(g,8,6);
		this.smallLine(g,1,7);
		this.smallLine(g,7,7);
		g.setColor(Color.black);
		Font font1 = new Font("宋体",Font.BOLD,50);
		g.setFont(font1);
		g.drawString("楚 河",170,365);
		g.drawString("漢 界",400,365);
		Font font = new Font("宋体",Font.BOLD,30);
		g.setFont(font);
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 10; j++) {
				if (chessPiece[i][j] != null){
					if(this.chessPiece[i][j].getFocus() != false) {
						g.setColor(Chess.focusBackground);
						g.fillOval(110 + i * 60 - 25, 80 + j * 60 - 25, 50, 50);
						g.setColor(Chess.focusChar);
					}
					else{
						g.fillOval(110 + i * 60 - 25, 80 + j * 60 - 25, 50, 50);
						g.setColor(chessPiece[i][j].getColor());
					}
				    g.drawString(chessPiece[i][j].getName(), 110 + i * 60 - 15, 80 + j * 60 + 10);
				    g.setColor(Color.black);
				}
			}
		}
		g.setColor(c);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (this.chess.referee == true) {
			int i = -1, j = -1;
			int[] pos = getPos(e);
			i = pos[0];
			j = pos[1];
			if (i >= 0 && i <= 8 && j >= 0 && j <= 9) {
				if (focus == false) {
					this.noFocus(i,j);
				}
				else {
					if (chessPiece[i][j] != null) {
						if (chessPiece[i][j].getColor() == chessPiece[startI][startJ].getColor()) {
							chessPiece[startI][startJ].setFocus(false);
							chessPiece[i][j].setFocus(true);
							startI = i;
							startJ = j;
						}
						else{ 
							endI = i;
							endJ = j;
							String name = chessPiece[startI][startJ].getName();
							boolean canMove = regulation.canMove(startI, startJ, endI, endJ, name);
							if (canMove) {
								try {
									this.chess.cat.dout.writeUTF("<#MOVE#>" + this.chess.cat.opponent + startI + startJ + endI + endJ);
									this.chess.referee = false;
								    if (chessPiece[endI][endJ].getName().equals("帥") || chessPiece[endI][endJ].getName().equals("將")) {
								    	this.success();
								    }
								    else {
								    	this.noJiang();
								    }
								}
								catch(Exception ee) {
									ee.printStackTrace();
								}
							}
						}
					}
					else {
						endI = i;
						endJ = j;
						String name = chessPiece[startI][startJ].getName();
						boolean canMove=regulation.canMove(startI, startJ, endI, endJ, name);
						if (canMove) {
							this.noQiZi();
						}
					}
				}
			}
			this.chess.repaint();
		}
	}
	public int[] getPos(MouseEvent e) {
		int[] pos = new int[2];
		pos[0] = -1;
		pos[1] = -1;
		Point p = e.getPoint();
		double x = p.getX();
		double y = p.getY();
		if(Math.abs((x-110) / 1 % 60) <= 25) {
			pos[0] = Math.round((float)(x - 110)) / 60;
		}
		else if(Math.abs((x-110) / 1 % 60) >= 35) {
			pos[0] = Math.round((float)(x - 110)) / 60 + 1;
		}
		if(Math.abs((y - 80) / 1 % 60) <= 25) {
			pos[1] = Math.round((float)(y - 80)) / 60;
		}
		else if(Math.abs((y - 80) / 1 % 60) >= 35) {
			pos[1] = Math.round((float)(y - 80)) / 60 + 1;
		}
		return pos;
	}
	
	public void noFocus(int i,int j) {
		if(this.chessPiece[i][j] != null) {
			if(this.chess.color == 0) {
				if(this.chessPiece[i][j].getColor().equals(Chess.color1)) {
					this.chessPiece[i][j].setFocus(true);
					focus = true;
					startI = i;
					startJ = j;
				}
			}
			else {
				if(this.chessPiece[i][j].getColor().equals(Chess.color2)) {
					this.chessPiece[i][j].setFocus(true);
					focus = true;
					startI = i;
		            startJ = j;
				}
			}
		}
	}
	
	public void success() { 
		chessPiece[endI][endJ] = chessPiece[startI][startJ];
		chessPiece[startI][startJ] = null;
		this.chess.repaint();
		JOptionPane.showMessageDialog(this.chess,"Congratulation! You Win!","Note", JOptionPane.INFORMATION_MESSAGE);
		this.chess.cat.opponent = null;
		this.chess.color = 0;
		this.chess.referee = false;
		this.chess.next();
		this.chess.jtfHost.setEnabled(false);
		this.chess.jtfPort.setEnabled(false);
		this.chess.jtfNickName.setEnabled(false);
		this.chess.jbConnect.setEnabled(false);
		this.chess.jbDisconnect.setEnabled(true);
		this.chess.jbChallenge.setEnabled(true);
		this.chess.jbYChallenge.setEnabled(false);
		this.chess.jbNChallenge.setEnabled(false);
		this.chess.jbFail.setEnabled(false);
		startI = -1;
		startJ = -1;
		endI = -1;
		endJ = -1;
		jiang1_i = 4;
		jiang1_j = 0;
		jiang2_i = 4;
		jiang2_j = 9;
		focus = false;
	}
	
	public void noJiang() {
		chessPiece[endI][endJ] = chessPiece[startI][startJ];
		chessPiece[startI][startJ] = null;
		chessPiece[endI][endJ].setFocus(false);
		this.chess.repaint();
		if (chessPiece[endI][endJ].getName().equals("帥")) {
			jiang1_i=endI;
			jiang1_j=endJ;
		}
		else if (chessPiece[endI][endJ].getName().equals("將")) {
			jiang2_i=endI;
			jiang2_j=endJ;
		}
		if (jiang1_i == jiang2_i) {
			int count=0;
			for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
				if(chessPiece[jiang1_i][jiang_j] != null) {
					count++;
					break;
				}
			}
			if (count == 0) {
		    	JOptionPane.showMessageDialog(this.chess,"You King is Killed by the opposite king! You Lose!","Note", JOptionPane.INFORMATION_MESSAGE);
		    	this.chess.cat.opponent = null;
				this.chess.color = 0;
				this.chess.referee = false;
				this.chess.next();
				this.chess.jtfHost.setEnabled(false);
				this.chess.jtfPort.setEnabled(false);
				this.chess.jtfNickName.setEnabled(false);
				this.chess.jbConnect.setEnabled(false);
				this.chess.jbDisconnect.setEnabled(true);
				this.chess.jbChallenge.setEnabled(true);
				this.chess.jbYChallenge.setEnabled(false);
				this.chess.jbNChallenge.setEnabled(false);
				this.chess.jbFail.setEnabled(false);
				jiang1_i = 4;
				jiang1_j = 0;
				jiang2_i = 4;
				jiang2_j = 9;
			}
		}
		startI = -1;
		startJ = -1;
		endI = -1;
		endJ = -1;
		focus = false;
	}
	
	
	public void noQiZi() {
		try {
			this.chess.cat.dout.writeUTF("<#MOVE#>" + this.chess.cat.opponent + startI + startJ + endI + endJ);
			this.chess.referee = false;
			chessPiece[endI][endJ] = chessPiece[startI][startJ];
			chessPiece[startI][startJ] = null;
			chessPiece[endI][endJ].setFocus(false);
			this.chess.repaint();
			if (chessPiece[endI][endJ].getName().equals("帥")) {
				jiang1_i = endI;
				jiang1_j = endJ;
			}
			else if (chessPiece[endI][endJ].getName().equals("將")) {
				jiang2_i = endI;
				jiang2_j = endJ;
			}
			if (jiang1_i == jiang2_i) {
				int count = 0;
				for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
					if (chessPiece[jiang1_i][jiang_j] != null) {
						count++;
						break;
					}
				}
				if (count == 0) {
			    	JOptionPane.showMessageDialog(this.chess,"You King is Killed by the opposite king! You Lose!","Note", JOptionPane.INFORMATION_MESSAGE);
			    	this.chess.cat.opponent = null;
					this.chess.color = 0;
					this.chess.referee = false;
					this.chess.next();
					this.chess.jtfHost.setEnabled(false);
					this.chess.jtfPort.setEnabled(false);
					this.chess.jtfNickName.setEnabled(false);
					this.chess.jbConnect.setEnabled(false);
					this.chess.jbDisconnect.setEnabled(true);
					this.chess.jbChallenge.setEnabled(true);
					this.chess.jbYChallenge.setEnabled(false);
					this.chess.jbNChallenge.setEnabled(false);
					this.chess.jbFail.setEnabled(false);
					jiang1_i = 4;
					jiang1_j = 0;
					jiang2_i = 4;
					jiang2_j = 9;				}
			}
			startI = -1;
			startJ = -1;
			endI = -1;
			endJ = -1;
			focus = false;
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	
public void move(int startI, int startJ, int endI, int endJ) {
	if (chessPiece[endI][endJ] != null && (chessPiece[endI][endJ].getName().equals("帥") || chessPiece[endI][endJ].getName().equals("將"))) {
    	chessPiece[endI][endJ] = chessPiece[startI][startJ];
    	chessPiece[startI][startJ] = null;
	    this.chess.repaint();
    	JOptionPane.showMessageDialog(this.chess, "Sorry.You Lose!!", "Note", JOptionPane.INFORMATION_MESSAGE);
    	this.chess.cat.opponent = null;
		this.chess.color = 0;
		this.chess.referee = false;
		this.chess.next();
		this.chess.jtfHost.setEnabled(false);
		this.chess.jtfPort.setEnabled(false);
		this.chess.jtfNickName.setEnabled(false);
		this.chess.jbConnect.setEnabled(false);
		this.chess.jbDisconnect.setEnabled(true);
		this.chess.jbChallenge.setEnabled(true);
		this.chess.jbYChallenge.setEnabled(false);
		this.chess.jbNChallenge.setEnabled(false);
		this.chess.jbFail.setEnabled(false);
		jiang1_i = 4;
		jiang1_j = 0;
		jiang2_i = 4;
		jiang2_j = 9;	
    }
    else {
    	chessPiece[endI][endJ] = chessPiece[startI][startJ];
	    chessPiece[startI][startJ] = null;
	    this.chess.repaint();
	    if (chessPiece[endI][endJ].getName().equals("帥")) {
			jiang1_i = endI;
			jiang1_j = endJ;
		}
		else if(chessPiece[endI][endJ].getName().equals("將")) {
			jiang2_i = endI;
			jiang2_j = endJ;
		}
		if (jiang1_i == jiang2_i) { 
			int count = 0;
			for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
				if (chessPiece[jiang1_i][jiang_j] != null) {
					count++;
					break;
				}
			}
			if (count == 0){
		    	JOptionPane.showMessageDialog(this.chess, "The opponent's King is killed by your king!! You Win!!", "Note", JOptionPane.INFORMATION_MESSAGE);
		    	this.chess.cat.opponent = null;
				this.chess.color = 0;
				this.chess.referee = false;
				this.chess.next();
				this.chess.jtfHost.setEnabled(false);
				this.chess.jtfPort.setEnabled(false);
				this.chess.jtfNickName.setEnabled(false);
				this.chess.jbConnect.setEnabled(false);
				this.chess.jbDisconnect.setEnabled(true);
				this.chess.jbChallenge.setEnabled(true);
				this.chess.jbYChallenge.setEnabled(false);
				this.chess.jbNChallenge.setEnabled(false);
				this.chess.jbFail.setEnabled(false);
				jiang1_i = 4;
				jiang1_j = 0;
				jiang2_i = 4;
				jiang2_j = 9;	
			}
		}
    }
	this.chess.repaint();
}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void smallLine(Graphics2D g,int i,int j) {
		int x = 110 + 60 * i;
		int y = 80 + 60 * j;
		if (i > 0) {
			g.drawLine(x - 3, y - 3, x - 20, y - 3);
			g.drawLine(x - 3, y - 3, x - 3, y - 20);
		}
		if (i < 8) {
			g.drawLine(x + 3, y - 3, x + 20, y - 3);
			g.drawLine(x + 3, y - 3, x + 3, y - 20);
		}
		if (i > 0) {
			g.drawLine(x - 3, y + 3, x - 20, y + 3);
			g.drawLine(x - 3, y + 3, x - 3, y + 20);
		}
		if (i < 8){
			g.drawLine(x + 3, y + 3, x + 20, y + 3);
			g.drawLine(x + 3, y + 3, x + 3, y + 20);
		}
	}
}
