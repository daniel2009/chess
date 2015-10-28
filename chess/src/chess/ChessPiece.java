package chess;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ChessPiece {
	private Color color;
	private String name;
	private int x;
	private int y;
	private boolean focus = false;
	public ChessPiece() {}
	public ChessPiece(Color color, String name, int x, int y) {
		this.color = color;
		this.name = name;
		this.x = x;
		this.y = y;
		this.focus = false;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getXPos() {
		return this.x;
	}
	
	public void setXPos(int x) {
		this.x = x;
	}
	public int getYPos() {
		return this.y;
	}
	
	public void setYPos(int y) {
		this.y = y;
	}
	public boolean getFocus() {
		return this.focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
}
