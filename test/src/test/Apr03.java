package test;

import java.util.*;

public class Apr03 {
	/*
	 * 最近在练amazon面经
	 * */
	/*
	 * 
	 * b. A function to find the number of paths going from top left to bottom right in

0 0 0 0 0
1 0 0 1 0
0 0 1 0 0
0 0 0 1 0
0 means fine to pass, 1 means it’s blocked. You can replace 1 with 1 so you deal with only 
binary numeric input matrix. The path can only go from left to right and from top to bottom
	 * */
	public int findPath(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		boolean isBlocked = false;
		for (int i = 0; i < m; i++) {
			if (grid[i][0] == 1) {
				isBlocked = true;
			}
			dp[i][0] = (isBlocked) ? 0 : 1; 
		}
		isBlocked = false;
		for (int j = 0; j < n; j++) {
			if (grid[0][j] == 1) {
				isBlocked = true;
			}
			dp[0][j] = (isBlocked) ? 0 : 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = (grid[i][j] == 1) ? 0 : dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}
	
	
	/*
	 * Given a list of integers and a window size, return a new list of integers where each 
	 * integer is the sum of all integers in the kth window of the input list. 
	 * E.g. [4, 2, 73, 11, -5] and window size 2 should return [6, 75, 84, 6].
	 * 
	 * */
	public List<Integer> findWindowSum(List<Integer> nums, int size) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.size() == 0 || size <= 0) {
			return result;
		}
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			if (i >= size) {
				sum -= nums.get(i - size);
			}
			sum += nums.get(i);
			result.add(sum);
		}
		return result;
	}
	
	public ListNode pairSwap(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		ListNode curr = head;
		while (curr != null && curr.next != null) {
			ListNode next = curr.next;
			prev.next = next;
			curr.next = next.next;
			next.next = curr;
			prev = curr;
			curr = prev.next;
		}
		return dummy.next;
	}
}
