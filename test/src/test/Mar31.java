package test;

import java.util.*;

public class Mar31 {
	public class GridNode {
		int x;
		int y;
		Set<GridNode> neighbors;
		public GridNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Object obj) {
			GridNode other = (GridNode)obj;
			return other.x == this.x && other.y == this.y;
		}
		
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.x;
			result = prime * result + this.y;
			return result;
		}
	}
	
	static class Direction {
		public static final int[] dx = {1, 0, -1, 0};
		public static final int[] dy = {0, 1, 0, -1};
		public static final int DOWN = 0;
		public static final int RIGHT = 1;
		public static final int UP = 2;
		public static final int LEFT = 3;
		
		public static int turnRight(int direction) {
    		if (direction == RIGHT) {
    			return DOWN;
    		} else if (direction == DOWN) {
    			return LEFT;
    		} else if (direction == LEFT) {
    			return UP;
    		} else {
    			return RIGHT;
    		}
    	}
	}
	
	public int numofSquare(Set<GridNode> grid, GridNode cur, int m, int n) {
		int maxLen = Math.max(m, n);
		int count = 0;
		int direction = 0;
		
		for (int step = 1; step <= maxLen; step++) {
			cur.x = 0;
			cur.y = 0;
			
			while (cur.x < m && cur.y < n) {
				GridNode temp = cur;
				int edge = 0;
				
				for (int i = 0; i <= step && edge < 4; i++) {
					if (i == step) {
						direction = Direction.turnRight(direction);
						i = 0;
						edge++;
						continue;
					}
					int nextX = temp.x + Direction.dx[direction];
					int nextY = temp.y + Direction.dy[direction];
					GridNode next = new GridNode(nextX, nextY);
					if (temp.neighbors.contains(next)) {
						temp = next;
					} else {
						break;
					}
				}
				if (temp.equals(cur)) {
					count++;
				}
				
				cur.y++;
				if (cur.y == n) {
					cur.x++;
					cur.y = 0;
					if (cur.x == m) {
						break;
					}
				}
			}
		}
		return count;
	}
	
	
	public void question2a(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				System.out.print(' ');
				System.out.print(' ');
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print('*');
				System.out.print(' ');
			}
			for (int j = 0; j < n - 1 - i; j++) {
				System.out.print(' ');
				if (j < n - 2 - i) {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
	
	public void question2b(int n) {
		question2a(n);
		n--;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n - i; j++) {
				System.out.print(' ');
				System.out.print(' ');
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print('*');
				System.out.print(' ');
			}
			for (int j = 0; j < n - i; j++) {
				System.out.print(' ');
				if (j < n - 2 - i) {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
	
}
