package test;

import java.util.*;

public class Apr16 {
	/* 
	 * serialize and deserialize a tree 
	 */
	public String serialize(TreeNode root) {
		List<String> list = new ArrayList<String>();
		if (root == null) {
			return list.toString();
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		list.add(root.val + "");
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();
				if (curr.left != null) {
					queue.offer(curr.left);
					list.add(curr.left.val + ""); 
				} else {
					list.add("#");
				}
				if (curr.right != null) {
					queue.offer(curr.right);
					list.add(curr.right.val + "");
				} else {
					list.add("#");
				}
			}
		}
		return list.toString();
	}
	
	public TreeNode deserialize(String serial) {
		if (serial == null || serial.length() == 0) {
			return null;
		}
		serial = serial.substring(1, serial.length() - 1);
		String[] values = serial.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int index = 1;
		while (index < values.length) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();
				if (!values[index].equals("#")) {
					curr.left = new TreeNode(Integer.parseInt(values[index]));
					queue.offer(curr.left);
				}
				index++;
				if (!values[index].equals("#")) {
					curr.right = new TreeNode(Integer.parseInt(values[index]));
					queue.offer(curr.right);
				}
				index++;
			}
		}
		return root;
	}
	/*
	 * find the node with least depth in a tree
	 * */
	public TreeNode findLeastDepthNode(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return root;
		}
		return findNodeHelper(root, 0).node; 
	}
	
	private ResultType findNodeHelper(TreeNode root, int depth) {
		if (root.left == null && root.right == null) {
			return new ResultType(root, depth);
		} else if (root.left == null) {
			return findNodeHelper(root.right, depth + 1);
		} else if (root.right == null) {
			return findNodeHelper(root.left, depth + 1);
		}
		ResultType left = findNodeHelper(root.left, depth + 1);
		ResultType right = findNodeHelper(root.right, depth + 1);
		return (left.depth < right.depth) ? left : right;
	}
	
	class ResultType {
		TreeNode node;
		int depth;
		public ResultType(TreeNode node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}
	
	
	/*
	 * find the node with least depth in a tree, return its depth
	 * */
	public int findLeastDepth(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return 0;
		}
		return helper(root, 0);
	}
	
	private int helper(TreeNode node, int depth) {
		if (node.left != null && node.right != null) {
			int left = helper(node.left, depth + 1);
			int right = helper(node.right, depth + 1);
			return Math.min(left, right);
		} else if (node.left != null) {
			return helper(node.left, depth + 1);
		} else if (node.right != null) {
			return helper(node.right, depth + 1);
		} else {
			return 0;
		}
	}
	
	/*
	 * remove duplicate values in a linkedlist
	 * */
	
	public void removeDuplicateInLinkedlist(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode cur = head;
		Set<Integer> set = new HashSet<Integer>();
		ListNode pre = new ListNode(-1);
		pre.next = cur;
		while (cur != null) {
			ListNode next = cur.next;
			if (!set.contains(cur.val)) {
				set.add(cur.val);
			} else {
				pre.next = next;
				cur.next = null;
			}
			cur = next;
		}
	}
	
	/*
	 * longest increasing path in a matrix
	 * */
	public int longestPathInMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		int[][] cache = new int[m][n];
		int maxLen = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int len = dfs(matrix, i, j, cache, dx, dy);
				maxLen = Math.max(len, maxLen);
			}
		}
		return maxLen;
	}
	
	private int dfs(int[][] matrix, int curX, int curY, int[][] cache, int[] dx, int[] dy) {
		if (cache[curX][curY] != 0) {
			return cache[curX][curY];
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int maxLen = 1;
		for (int i = 0; i < 4; i++) {
			int nextX = curX + dx[i];
			int nextY = curY + dy[i];
			if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n || matrix[nextX][nextY] <= matrix[curX][curY]) {
				continue;
			} else {
				maxLen = 1 + dfs(matrix, nextX, nextY, cache, dx, dy);
			}
		}
		cache[curX][curY] = maxLen;
		return maxLen;
	}
}
