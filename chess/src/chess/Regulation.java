package chess;

public class Regulation {
	ChessPiece[][] chessPiece;
	boolean canMove = false;
	int i; 
	int j;
	
	public Regulation(ChessPiece[][] chessPiece) {
		this.chessPiece = chessPiece;
	}
	
	public boolean canMove(int startI, int startJ, int endI, int endJ, String name) {
		int maxI = -1;
		int minI = -1;
		int maxJ = -1;
		int minJ = -1;
		canMove = true;
		if (startI >= endI) {
			maxI = startI;
			minI = endI;
		}
		else {
			maxI = endI;
			minI = startI;
		}
		if (startJ >= endJ) {
			maxJ = startJ;
			minJ = endJ;
		}
		else {
			maxJ = endJ;
			minJ = startJ;
		}
		
		if (name.equals("車")) {
			this.ju(maxI, minI, maxJ, minJ);
		}
		else if (name.equals("馬")) {
			this.ma(startI, startJ, endI, endJ);
		}
		else if (name.equals("相")) {
			this.xiang1(startI, startJ, endI, endJ);
		}
		else if (name.equals("象")) {
			this.xiang2(startI, startJ, endI, endJ);
		}
		else if (name.equals("士") || name.equals("仕")) {
			this.shi(startI, startJ, endI, endJ);
		}
		else if (name.equals("将") || name.equals("帥")) {
			this.jiang(startI, startJ, endI, endJ);
		}
		else if (name.equals("炮") || name.equals("砲")) {
			this.pao(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
		}
		else if (name.equals("兵")) {
			this.bing(startI, startJ, endI, endJ);
		}
		else if (name.equals("卒")) {
			this.zu(startI, startJ, endI, endJ);
		}
		return canMove;
 	}
	
	public void ju(int maxI, int minI, int maxJ, int minJ) {
		if (maxI == minI) {
			for(j = minJ + 1; j < maxJ; j++) {
				if(chessPiece[maxI][j] != null) {
					canMove = false;
					break;
				}
			}
		}
		else if (maxJ == minJ) {
			for(i = minJ + 1; i < maxJ; i++) {
				if(chessPiece[i][maxJ] != null) {
					canMove = false;
					break;
				}
			}
		}
		else if (maxI != minI && maxJ != minJ) {
			canMove = false;
		}
	}
	
	public void ma(int startI, int startJ, int endI, int endJ) {
		int a = Math.abs(startI - endI);
		int b = Math.abs(startJ - endJ);
		if (a == 1 && b == 2) {
			if (startJ > endJ) {
				if(chessPiece[startI][startJ - 1] != null) {
					canMove = false;
				}	
			}
			else {
				if(chessPiece[startI][startJ + 1] != null) {
					canMove = false;
				}
			}
		}
		else if (a == 2 && b == 1) {
			if (startI > endI) {
				if(chessPiece[startI - 1][startJ]!=null) {
					canMove = false;
				}	
			}
			else {
				if(chessPiece[startI + 1][startJ] != null) {
					canMove = false;
				}
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void xiang1(int startI, int startJ, int endI, int endJ) {
		int a = Math.abs(startI - endI);
		int b = Math.abs(startJ - endJ);
		if (a == 2 && b == 2) {
			if (endJ > 4) {
				canMove = false;
			}
			if(chessPiece[(startI + endI) / 2][(startJ + endJ) / 2] != null) {
				canMove = false;
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void xiang2(int startI, int startJ, int endI, int endJ) {
		int a = Math.abs(startI - endI);
		int b = Math.abs(startJ - endJ);
		if (a == 2 && b == 2) {
			if (endJ < 5) {
				canMove = false;
			}
			if(chessPiece[(startI + endI) / 2][(startJ + endJ) / 2] != null) {
				canMove = false;
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void shi(int startI, int startJ,int endI,int endJ) {
		int a = Math.abs(startI - endI);
		int b = Math.abs(startJ - endJ);
		if(a == 1 && b == 1) {
			if (startJ > 4) {
				if(endJ < 7) {
					canMove = false;
				}
			}
			else {
				if(endJ > 2) {
					canMove = false;
				}
			}
			if (endI > 5 || endI < 3) {
				canMove = false;
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void jiang(int startI, int startJ, int endI, int endJ) {
		int a = Math.abs(startI - endI);
		int b = Math.abs(startJ - endJ);
		if ((a == 1 && b == 0) || (a == 0 && b == 1)) {
			if (startJ > 4) {
				if (endJ < 7) {
					canMove = false;
				}
			}
			else {
				if (endJ > 2) {
					canMove = false;
				}
			}
			if (endI > 5 || endI < 3) {
				canMove = false;
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void pao(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {
		if(maxI == minI) {
			if(chessPiece[endI][endJ] != null) {
				int count = 0;
				for(j = minJ + 1; j < maxJ; j++) {
					if(chessPiece[minI][j] != null) {
						count++;
					}
				}
				if (count != 1) {
					canMove = false;
				}
			}
			else if (chessPiece[endI][endJ] == null) {
				for (j = minJ + 1; j < maxJ; j++) {
					if (chessPiece[minI][j] != null) {
						canMove = false;
						break;
					}
				}
			}
		}
		else if (maxJ == minJ) {
			if (chessPiece[endI][endJ] != null) {
				int count = 0;
				for (i = minI + 1; i < maxI; i++) {
					if (chessPiece[i][minJ] != null) {
						count++;
					}
				}
				if (count != 1) {
					canMove = false;
				}
			}
			else if (chessPiece[endI][endJ] == null) {
				for (i = minI + 1; i < maxI; i++) {
					if(chessPiece[i][minJ] != null) {
						canMove = false;
						break;
					}
				}
			}
		}
		else {
			canMove = false;
		}
	}
	
	public void bing(int startI,int startJ,int endI,int endJ) {
		if (startJ < 5) {
			if (startI != endI) {
				canMove = false; 
			}
			if (endJ - startJ != 1) {
				canMove = false;
			}
		}
		else {
			if (startI == endI) {
				if (endJ - startJ != 1) {
					canMove = false;
				}
			}
			else if (startJ == endJ) {
				if (Math.abs(startI - endI) != 1) {
					canMove = false;
				}
			}
			else {
				canMove = false;
			}
		}
	}
	
	public void zu(int startI,int startJ,int endI,int endJ) {
		if (startJ > 4) {
			if (startI != endI) {
				canMove = false; 
			}
			if (endJ - startJ != 1) {
				canMove = false;
			}
		}
		else {
			if (startI == endI) {
				if (endJ - startJ != -1) {
					canMove = false;
				}
			}
			else if (startJ == endJ) {
				if (Math.abs(startI - endI) != 1) {
					canMove = false;
				}
			}
			else {
				canMove = false;
			}
		}
	}
}
