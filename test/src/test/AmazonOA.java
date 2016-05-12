package test;
import java.util.*;
public class AmazonOA {
	public int findGCD(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res = findGCDInTwoNumber(res, nums[i]);
		}
		return res;
	}
	
	private int findGCDInTwoNumber(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		if (a == 1 || b == 1) {
			return 1;
		}
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		if (max % min != 0) {
			return findGCDInTwoNumber(min, max % min);
		}
		else {
			return min;
		}
	}
	
	private int[] dx = {1, 0, -1, 0};
	private int[] dy = {0, 1, 0, -1};
	private class Step {
		int x;
		int y;
		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public int findPathInMaze(int[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return 0;
		}
		if (maze[0][0] == 0) {
			return 0;
		}
		if (maze[0][0] == 9) {
			return 1;
		}
		int m = maze.length;
		int n = maze[0].length;
		Step start = new Step(0, 0);
		Queue<Step> queue = new LinkedList<Step>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Step cur = queue.poll();
				for (int j = 0; j < 4; j++) {
					int nextXPos = cur.x + dx[j];
					int nextYPos = cur.y + dy[j];
					if (nextXPos < 0 || nextXPos >= m || nextYPos < 0 || nextYPos >= n || maze[nextXPos][nextYPos] == 0) {
						continue;
					}
					else if (maze[nextXPos][nextYPos] == 9) {
						return 1;
					}
					else {
						queue.offer(new Step(nextXPos, nextYPos));
					}
				}
				maze[cur.x][cur.y] = 0;
			}
		}
		return 0;
	}
	
	public int[] flipStatus(int[] initial, int days) {
		if (days == 0 || initial == null || initial.length == 0) {
			return null;
		}
		int[][] result = new int[2][initial.length];
		result[0] = initial;
		for (int i = 1; i <= days; i++) {
			int today = i % 2;
			int yesterday = (i - 1) % 2;
			for (int j = 0; j < initial.length; j++) {
				if (j == 0) {
					result[today][j] = 0 ^ result[yesterday][j + 1];
				}
				else if (j == initial.length - 1) {
					result[today][j] = result[yesterday][j - 1] ^ 0;
				}
				else {
					result[today][j] = result[yesterday][j - 1] ^ result[yesterday][j + 1];
				}
			}
		}
		
		return result[days % 2];
	}
	
	private class Process {
		int arri;
		int exe;
		public Process(int arri, int exe) {
			this.arri = arri;
			this.exe = exe;
		}
	}
	
	private Comparator<Process> processComparator = new Comparator<Process>() {
		public int compare(Process p1, Process p2) {
			if (p1.exe != p2.exe) {
				return p1.exe - p2.exe;
			}
			else {
				return p1.arri - p2.arri;
			}
		}
	};
	
	public double robinRound(int[] arrival, int[] execute, int q) {
		if (arrival == null || arrival.length == 0 || execute == null || execute.length == 0) {
			return 0.0;
		}
		int currentTime = 0;
		int waitingTime = 0;
		int nextProIndex = 0;
		Queue<Process> queue = new LinkedList<Process>();
		while (!queue.isEmpty() || nextProIndex < arrival.length) {
			if (!queue.isEmpty()) {
				Process cur = queue.poll();
				waitingTime += currentTime - cur.arri;
				currentTime += Math.min(cur.exe, q);
				for (int i = nextProIndex; i < arrival.length; i++) {
					if (arrival[i] <= currentTime) {
						queue.offer(new Process(arrival[i], execute[i]));
						nextProIndex++;
					}
					else {
						break;
					}
				}
				if (cur.exe > q) {
					queue.offer(new Process(currentTime, cur.exe - q));
				}
			}
			else {
				queue.offer(new Process(arrival[nextProIndex], execute[nextProIndex]));
				currentTime += arrival[nextProIndex++];
			}
		}
		return (double)waitingTime / arrival.length;
	}
	
	
	public double shortestJobFirst(int[] arrival, int[] execute) {
		if (arrival == null || arrival.length == 0 || execute == null || execute.length == 0) {
			return 0.0;
		}
		int currentTime = 0;
		int waitingTime = 0;
		int numberOfWaiting = 0;
		Queue<Process> queue = new LinkedList<Process>();
		while (!queue.isEmpty() || numberOfWaiting < arrival.length) {
			if (!queue.isEmpty()) {
				Process cur = queue.poll();
				waitingTime += currentTime - cur.arri;
				currentTime += cur.exe;
				Vector<Process> waitingList = new Vector<Process>();
				for (int i = numberOfWaiting; i < arrival.length; i++) {
					if (arrival[i] < currentTime) {
						waitingList.add(new Process(arrival[i], execute[i]));
						numberOfWaiting++;
					}
					else {
						break;
					}
				}
				Collections.sort(waitingList, processComparator);
				for (int i = 0; i < waitingList.size(); i++) {
					queue.offer(waitingList.get(i));
				}
				waitingList.clear();
			}
			else {
				queue.offer(new Process(arrival[numberOfWaiting], execute[numberOfWaiting]));
				currentTime += arrival[numberOfWaiting++];
			}
		}
		return (double)waitingTime / arrival.length;
	}
	
	public int[][] rotateMatrix(int[][] matrix, int flag) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] result = new int[n][m];
		if (flag == 0) {
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < (n + 1) / 2; j++) {
					result[n - j - 1][i] = matrix[i][j];
					result[n - i - 1][m - j - 1] = matrix[m - j - 1][i];
					result[j][m - i - 1] = matrix[m - i - 1][n - j - 1];
					result[i][j] = matrix[j][n - i - 1];
				}
			}
		}
		else {
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < (n + 1) / 2; j++) {
					result[j][m - i - 1] = matrix[i][j];
					result[n - i - 1][m - j - 1] = matrix[j][n - i - 1];
					result[n - j - 1][i] = matrix[m - i - 1][n - j - 1];
					result[i][j] = matrix[m - j - 1][i];
				}
			}
		}
		return matrix;
	}
	
	public int findMinPath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		else if (root.left == null) {
			return findMinPath(root.right) + root.val;
		}
		else if (root.right == null) {
			return findMinPath(root.left) + root.val;
		}
		else {
			return Math.min(findMinPath(root.left), findMinPath(root.right)) + root.val;
		}
	}
	
	public int findMiss(int[] input, int size) {
		if (input == null || input.length == 0 || size == 0) {
			return 0;
		}
		Vector<Integer> cache = new Vector<Integer>();
		int miss = 0;
		for (int i = 0; i < input.length; i++) {
			int pos = cache.indexOf(input[i]);
			if (pos != -1) {
				cache.remove(pos);
				cache.add(input[i]);
			}
			else {
				miss++;
				if (cache.size() == size) {
					cache.remove(0);
				}
				cache.add(input[i]);
			}
		}
		return miss;
	}
	
	public CNode insertCNode(CNode head, CNode node) {
		if (head == null) {
			return head;
		}
		//find the smallest one
		CNode prev = head;
		CNode cur = head.next;
		while (cur != head) {
			if (cur.val < head.val) {
				break;
			}
			cur = cur.next;
			prev = prev.next;
		}
		CNode dummy = prev;
		head = cur;
		if (node.val > prev.val || node.val <= cur.val) {
			prev.next = node;
			node.next = head;
			if (node.val > prev.val) {
				dummy = node;
			}
			return dummy.next;
		}
		while (cur != dummy) {
			if (prev.val < node.val && node.val <= cur.val) {
				prev.next = node;
				node.next = cur;
				break;
			}
			cur = cur.next;
			prev = prev.next;
		} 
		
		return dummy.next;
	}
	
	public int findPathInMazeDFS(int[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return 0;
		}
		if (maze[0][0] == 0) {
			return 0;
		}
		if (maze[0][0] == 9) {
			return 1;
		}
		return dfs(maze, 0, 0) ? 1 : 0;
	}
	
	private boolean dfs(int[][] maze, int x, int y) {
		if (x < 0 || x >= maze.length || y < 0 || y >= maze[x].length || maze[x][y] == 0) {
			return false;
		}
		if (maze[x][y] == 9) {
			return true;
		}
		else {
			maze[x][y] = 0;
			return dfs(maze, x + 1, y) || dfs(maze, x, y + 1) || dfs(maze, x - 1, y) || dfs(maze, x, y - 1);
		}
	}
	
	
}



class CNode {
	int val;
	CNode next;
	public CNode(int val) {
		this.val = val;
		this.next = null;
	}
}
