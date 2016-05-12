package test;
import java.util.*;


import java.math.*;

public class Solution {
	public int firstMissingPositive(int[] A) {
        // write your code here   
        if (A == null) {
            return 1;
        }
        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= A.length && A[i] != A[A[i] - 1]) {
                swap(A, i, A[i] - 1);
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
    }
    
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return result;
        }
        Collections.sort(nums);
        Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        
        Queue<ArrayList<Integer>> isUsed = new LinkedList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.size(); i++) {
        	list.add(0);
        }
        
        queue.offer(new ArrayList<Integer>());
        isUsed.offer(new ArrayList<Integer>(list));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Integer> cur = queue.poll();
                ArrayList<Integer> curUsed = isUsed.poll();
                if (cur.size() == nums.size()) {
                    result.add(cur);
                }
                for (int j = 0; j < nums.size(); j++) {
                	ArrayList<Integer> temp = new ArrayList<Integer>(cur);
                	ArrayList<Integer> tempUsed = new ArrayList<Integer>(curUsed);
                	if (tempUsed.get(j).intValue() == 1) {
                		continue;
                	}
                	else {
                		temp.add(nums.get(j));
                		if (!set.contains(temp)) {
                			set.add(temp);
                			queue.offer(temp);
                			tempUsed.set(j, 1);
                			isUsed.offer(tempUsed);
                		}
                	}
                }
                set.clear();
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return result;
        }
        
        Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
        queue.offer(new ArrayList<Integer>());
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Integer> cur = queue.poll();
                for (int j = 0; j < nums.size(); j++) {
                    if (!cur.contains(nums.get(j))) {
                        ArrayList<Integer> temp = new ArrayList<Integer>(cur);
                        temp.add(nums.get(j));
                        queue.offer(temp);
                    }
                }
                if (cur.size() == nums.size()) {
                    result.add(cur);
                }
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        // write your code here
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (n <= 0) {
            return result;
        }
        
        Queue<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        String s = sb.toString();
        queue.offer(new ArrayList<String>());
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                ArrayList<String> cur = queue.poll();
                
                if (cur.size() == n) {
                    result.add(cur);
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    ArrayList<String> temp = new ArrayList<String>(cur);
                    if (isSafe(temp, j, n)) {
                        String newLine = s.substring(0, j) + 'Q' + s.substring(j + 1);
                        temp.add(newLine);
                        queue.offer(temp);
                    }
                }
            }
        }
        return result;
    }
    
    public boolean isSafe(ArrayList<String> array, int pos, int n) {
        int size = array.size();
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).length(); j++) {
                if (array.get(i).charAt(j) == 'Q') {
                    if (!set.contains(j)) {
                        set.add(j);
                    }
                    if (j - (size - i) >= 0 && !set.contains(j - (size - i))) {
                        set.add(j - (size - i));
                    }
                    if (j + (size - i) < n && !set.contains(j + (size - i))) {
                        set.add(j + (size - i));
                    }
                }
            }
        }
        for (Integer i : set) {
        	System.out.println(i);
        }
        if (set.contains(pos)) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean[][] getIsPalindrome(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j < len; j++) {
                isPalindrome[i][j] = (isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
            }
        }
        
        return isPalindrome;
    }
    
    public boolean[][] getIsPalindrome1(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                    = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }
    
    public Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
    	public int compare(ListNode left, ListNode right) {
    		if (left == null) {
    			return -1;
    		}
    		else if (right == null) {
    			return 1;
    		}
    		else {
    			return left.val - right.val;
    		}
    	}
    };
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length <= 2) {
            return;
        }
        int start = 0;
        int end = A.length - 1;
        while (start < end - 1) {
            while (A[start] < 0) {
                start++;
            }
            while (A[end] > 0) {
                end--;
            }
            if (start < end) {
                swap(A, start, end);
            }
        }
        if (2 * start > A.length) {
        	start = 1;
        	end = A.length - 1;
        }
        else if (2 * start == A.length){
        	start = 0;
            end = A.length - 1;
        }
        else {
            start = 0;
            end = A.length - 2;
        }
        while (start < end - 1) {
            swap(A, start, end);
            start += 2;
            end -= 2;
        }
        for (int i = 0; i < A.length; i++) {
        	System.out.print(A[i]);
        	System.out.print(' ');
        }
        System.out.print('\n');
        if (A[start] < A[end] && start < end) {
            swap(A, start, end);
        }
    }
    
    public int findMinWeightPath(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	if (root.left != null && root.right != null){
    		int leftMin = findMinWeightPath(root.left);
        	int rightMin = findMinWeightPath(root.right);
        	return Math.min(leftMin, rightMin) + root.val;
    	}
    	else if (root.left == null) {
    		return findMinWeightPath(root.right) + root.val;
    	}
    	else {
    		return findMinWeightPath(root.left) + root.val;
    	}
    	
    }
    
    public int longestPalindromeSubstring(String s) {
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	int len = s.length();
    	boolean[][] isPalindrome = getIsPalindrome(s);
    	int maxLen = Integer.MIN_VALUE;
    	for (int i = 0; i < len; i++) {
    		for (int j = i; j < len; j++) {
    			if (isPalindrome[i][j]) {
    				maxLen = Math.max(maxLen, j - i + 1);
    			}
    		}
    	}
    	return maxLen;
    }
    
    public boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        int step = 0;
        MyBoolean canWin = new MyBoolean(true);
        helper(s, canWin, step);
        return canWin.value;
    }
    
    private void helper(String s, MyBoolean canWin, int step) {
    	if (s.length() == 0) {
    		return;
    	}
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < s.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == '+') {
                sb.append(s.substring(0, i));
                sb.append("--");
                sb.append(s.substring(i + 2));
                list.add(sb.toString());
                System.out.println(sb);
                helper(sb.toString(), canWin, step + 1);
            }
            
        }
        System.out.print("step:");
        System.out.print(step);
        if (list.size() == 0 && step % 2 == 0) {
        	canWin.value = false;
        }
        System.out.println(canWin.value);
    }
    
    class TrieNode {
        String s;
        boolean isString;
        HashMap<Character, TrieNode> subtree;
        public TrieNode() {
            this.s = "";
            this.isString = false; 
            this.subtree = new HashMap<Character, TrieNode>();
        }
    };
    
    class TrieTree {
        TrieNode root;
        public TrieTree(TrieNode node) {
            root = node;
        }
        public void insert(String s) {
            TrieNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                if (!cur.subtree.containsKey(s.charAt(i))) {
                    cur.subtree.put(s.charAt(i), new TrieNode());
                }
                cur = cur.subtree.get(s.charAt(i));
            }
            cur.s = s;
            cur.isString = true;
            
        }
    };
    
    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public void search(char[][] board, ArrayList<String> result, int x, int y, TrieNode node) {
        
        if (node.isString == true) {
            if (!result.contains(node.s)) {
                result.add(node.s);
            }
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#' || node == null) {
            return;
        }
        if (node.subtree.containsKey(board[x][y])) {
            
            for (int i = 0; i < 4; i++) {
                char c = board[x][y];
                board[x][y] = '#';
                search(board, result, x + dx[i], y + dy[i], node.subtree.get(c));
                board[x][y] = c;
            }
        }
    }
    
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        if (board == null || words == null || words.size() == 0) {
            return result;
        } 
        TrieTree tree = new TrieTree(new TrieNode());
        for (int i = 0; i < words.size(); i++) {
            tree.insert(words.get(i));
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, result, i, j, tree.root);
            }
        }
        
        return result;
    }
    public int findMin(int[] num) {
        // write your code here
        if (num == null || num.length == 0) {
            return 0;
        }
        int start = 0; 
        int end = num.length - 1;
        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            if (num[start] >= num[end]) {
                if (num[mid] >= num[start]) {
                    start = mid;
                }
                else if (num[mid] <= num[end]) {
                    end = mid;
                }
                
            }
            else {
                end = mid;
            }
        }
        return Math.min(num[start], num[end]);
    }
    
    public ListNode reverseLinkedListII(ListNode head, int m, int n) {
    	if (head == null || m == n) {
    		return head;
    	}
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	head = dummy;
    	for (int i = 1; i < m; i++) {
    		head = head.next;
    	}
    	ListNode premNode = head;
    	ListNode mNode = premNode.next;
    	ListNode nNode = mNode;
    	ListNode postnNode = nNode.next;
    	
    	for (int i = m; i < n; i++) {
    		ListNode temp = postnNode.next;
    		postnNode.next = nNode;
    		nNode = postnNode;
    		postnNode = temp;
    	}
    	
    	premNode.next = nNode;
    	mNode.next = postnNode;
    	return dummy.next;
    }
    
    public ListNode reverseHalf(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode slow = head;
    	ListNode fast = slow.next;
    	while (fast.next != null && fast.next.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}	
    	ListNode preEnd = slow;
    	ListNode End = slow.next;
    	ListNode Last = End;
    	ListNode postEnd = End.next;
    	while (postEnd != null) {
    		ListNode temp = postEnd.next;
    		postEnd.next = End;
    		End = postEnd;
    		postEnd = temp;
    	}
    	preEnd.next = End;
    	Last.next = postEnd;
    	return dummy.next;
    }
    
    public int CountTwoSumPair(int[] nums, int target) {
    	int count = 0;
    	if (nums == null || nums.length == 0) {
    		return count;
    	}
    	Set<Integer> set = new HashSet<Integer>();
    	for (int i = 0; i < nums.length; i++) {
    		if (!set.contains(target - nums[i])) {
    			set.add(nums[i]);
    		}
    		else {
    			count++;
    		}
    	}
    	return count;
    }
    
    public int[] TwoSum(int[] nums, int target) {
    	if (nums == null || nums.length <= 1) {
    		return null;
    	}
    	int[] res = new int[2];
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i = 0; i < nums.length; i++) {
    		if (!map.containsKey(target - nums[i])) {
    			map.put(nums[i], i);
    		}
    		else {
    			res[0] = map.get(target - nums[i]) + 1;
    			res[1] = i + 1;
    		}
    	}
    	return res;
    }
    
    public ListNode mergeLinkedList(ListNode l1, ListNode l2) {
    	if (l1 == null && l2 == null) {
    		return null;
    	}
    	else if (l1 == null || l2 == null) {
    		return (l2 == null) ? l1 : l2;
    	}
    	else {
    		ListNode dummy = new ListNode(0);
    		ListNode head = dummy;
    		while (l1 != null && l2 != null) {
    			if (l1.val <= l2.val) {
    				head.next = l1;
    				l1 = l1.next;
    			}
    			else {
    				head.next = l2;
    				l2 = l2.next;
    			}
    			head = head.next;
    		}
    		while (l1 != null) {
    			head.next = l1;
    			l1 = l1.next;
    		}
    		while (l2 != null) {
    			head.next = l2;
    			l2 = l2.next;
    		}
    		return dummy.next;
    	}
    }
    public int findPathInMaze(int[][] maze, int StartX, int StartY) {
    	if (maze == null || maze[0].length == 0) {
    		return 0;
    	}
    	int m = maze.length;
    	int n = maze[0].length;
    	int len = 1;
    	Queue<Step> queue = new LinkedList<Step>();
    	Step start = new Step(StartX, StartY);
    	
    	if (maze[0][0] == 9 || maze[0][0] == 1) {
    		return 0;
    	}
    	
    	queue.offer(start);
    	while (!queue.isEmpty()) {
    		 int size = queue.size();
    		 for (int j = 0; j < size; j++) {
    			 Step cur = queue.poll();
    			 for (int i = 0; i < 4; i++) {
    				 int nextXPos = cur.x + dx[i];
    				 int nextYPos = cur.y + dy[i];
    				 if (nextXPos >= 0 && nextXPos < n && nextYPos >= 0 && nextYPos < m) {
    					 if (maze[nextXPos][nextYPos] == 0) {
    						 Step next = new Step(nextXPos, nextYPos);
    						 queue.offer(next);
    					 }
    					 else if (maze[nextXPos][nextYPos] == 9) {
    						 return len++;
    					 } 
    				 }
    			 }
    			 maze[cur.x][cur.y] = 1;
    		 }
    		 len++;
    	}
    	return 0;
    }
    
    public List<Step> findLeastPathInMaze(int[][] maze, int startX, int startY) {
    	List<Step> resultPath = new ArrayList<Step>();
    	List<Step> possiblePath = new ArrayList<Step>();
    	if (maze == null || maze[0].length == 0) {
    		return resultPath;
    	}
    	
    	int m = maze.length;
    	int n = maze[0].length;
    	int endX = -1;
    	int endY = -1;
    	Step[][] mazeObjectArray = new Step[m][n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			mazeObjectArray[i][j] = new Step(i, j);
    			mazeObjectArray[i][j].val = maze[i][j];
    			if (maze[i][j] == 9) {
    				endX = i;
    				endY = j;
    			}
    		}
    	}
    	
    	if (endX == -1 || endY == -1) {
    		return resultPath;
    	}
    	Step start = mazeObjectArray[startX][startY];
    	Step end = mazeObjectArray[endX][endY];
    	Map<Step, Integer> distance = new HashMap<Step, Integer>();
    	Map<Step, ArrayList<Step>> map = new HashMap<Step, ArrayList<Step>>();
    	bfsMaze(mazeObjectArray, m, n, distance, map, start);
    	dfsMaze(map, distance, resultPath, possiblePath, end, start);
    	return resultPath;
    }
    
    public void bfsMaze(Step[][] mazeObjectArray, int m, int n, Map<Step, Integer> distance, Map<Step, ArrayList<Step>> map, Step start) {
    	Queue<Step> queue = new LinkedList<Step>();
    	queue.offer(start);
    	distance.put(start, 0);
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			map.put(mazeObjectArray[i][j], new ArrayList<Step>());
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			Step cur = queue.poll();
    			List<Step> nextList = findNextList(cur, mazeObjectArray, m, n);
    			for (Step next : nextList) {
    				map.get(next).add(cur);
    				if (!distance.containsKey(next)) {
    					distance.put(next, distance.get(cur) + 1);
    					queue.offer(next);
    				}
    			}
    		}
    	}
    }
    
    public void dfsMaze(Map<Step, ArrayList<Step>> map, Map<Step, Integer> distance, List<Step> resultPath, List<Step> possiblePath, Step cur, Step start) {
    	possiblePath.add(cur);
    	if (cur.x == start.x && cur.y == start.y) {
    		Collections.reverse(possiblePath);
    		resultPath.addAll(possiblePath);
    		Collections.reverse(possiblePath);
    	}
    	else {
    		for (Step next : map.get(cur)) {
    			if (distance.get(next) == distance.get(cur) - 1) {
    				dfsMaze(map, distance, resultPath, possiblePath, next, start);
    			}
    		}
    		
    	}
    	possiblePath.remove(possiblePath.size() - 1);
    }
    
    public List<Step> findNextList(Step cur, Step[][] mazeObjectArray, int m, int n) {
    	List<Step> nextList = new ArrayList<Step>();
    	for (int i = 0; i < 4; i++) {
    		int nextXPos = cur.x + dx[i];
			int nextYPos = cur.y + dy[i];
			if (nextXPos >= 0 && nextXPos < m && nextYPos >= 0 && nextYPos < n) {
				if (mazeObjectArray[nextXPos][nextYPos].val == 0 || mazeObjectArray[nextXPos][nextYPos].val == 9) {
					nextList.add(mazeObjectArray[nextXPos][nextYPos]);
				}
			}
    	}
    	return nextList;
    }
    
    public int[][] rotateII(int[][] matrix, int flag) {
    	//if flag equals to 0 turn left, else turn right.
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return null;
    	}
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int[][] result = new int[n][m];
    	if (flag == 1) {
    		for (int i = 0; i < (m + 1) / 2; i++) {
    			for (int j = 0; j < n / 2; j++) {
    				result[j][m - i - 1] = matrix[i][j];
    				result[n - i - 1][m - j - 1] = matrix[j][n - i - 1];
    				result[n - 1 - j][i] = matrix[m - 1 - i][n - 1 - j];
    				result[i][j] = matrix[m - 1 - j][i];
    			}
    		}
    	}
    	else {
    		for (int i = 0; i < (m + 1) / 2; i++) {
    			for (int j = 0; j < n / 2; j++) {
    				result[n - j - 1][i] = matrix[i][j];
    				result[n - 1 - i][m - 1 - j] = matrix[m - 1 - j][i];
    				result[j][m - 1 - i] = matrix[m - 1 - i][n - 1 - j];
    				result[i][j] = matrix[j][n - 1 - i];
    			}
    		}
    	}
    	return result;
    }
    
    public ListNode insertCycleList(ListNode head, ListNode node) {
    	ListNode prev = head;
    	ListNode cur = head.next;
    	while (cur != head) {
    		if (cur.val <= head.val && prev.val > cur.val) {
    			break;
    		}
    		cur = cur.next;
    		prev = prev.next;
    	}
    	ListNode dummy = prev;
    	head = cur;
    	if (node.val >= prev.val || node.val <= head.val) {
    		prev.next = node;
    		node.next = head;
    		if (node.val >= prev.val) {
    			dummy = node;
    		}
    		return dummy.next;
    	}
    	while (cur != dummy) {
    		if (prev.val <= node.val && cur.val >= node.val) {
    			prev.next = node;
    			node.next = cur;
    			break;
    		}
    		cur = cur.next;
    		prev = prev.next;
    	}
    	return dummy.next;
    }
    
    public int[] flipStatus(int[] initial, int days) {
    	if (initial == null || initial.length == 0 || days <= 0) {
    		return initial;
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
    
    public boolean isSubTree(TreeNode T1, TreeNode T2) {
    	if (T2 == null) {
    		return true;
    	}
    	else if (T1 == null) {
    		return false;
    	}
    	else if (T1.val != T2.val) {
    		return isSubTree(T1.left, T2) || isSubTree(T1.right, T2);
    	}
    	else {
    		return isSameTree(T1, T2) || isSubTree(T1.left, T2.left) || isSubTree(T1.right, T2.right);
    	}
    }
    
    private boolean isSameTree(TreeNode T1, TreeNode T2) {
    	if (T1 == null && T2 == null) {
    		return true;
    	}
    	else if (T1 == null || T2 == null) {
    		return false;
    	}
    	else {
    		return (T1.val == T2.val) && isSameTree(T1.left, T2.left) && isSameTree(T1.right, T2.right);
    	}
    }
    
    public double robinWaitingTime(int[] arri, int[] exe, int q) {
    	if (arri == null || arri.length == 0 || exe == null || exe.length == 0) {
    		return Double.MAX_VALUE;
    	}
    	Queue<Process> queue = new LinkedList<Process>();
    	int currentTime = 0;
    	int waitingTime = 0;
    	int nextProIndex = 0;
    	while (!queue.isEmpty() || nextProIndex < arri.length) {
    		if (!queue.isEmpty()) {
    			Process cur = queue.poll();
    			waitingTime += currentTime - cur.arriTime;
    			currentTime += Math.min(cur.exeTime, q);
    			for (int i = nextProIndex; i < arri.length; i++) {
    				if (arri[i] <= currentTime) {
    					queue.offer(new Process(arri[i], exe[i]));
    					nextProIndex = i + 1;
    				}
    				else {
    					break;
    				}
    			}
    			if (cur.exeTime > q) {
    				queue.offer(new Process(currentTime, cur.exeTime - q));
    			}
    		}
    		else {
    			queue.offer(new Process(arri[nextProIndex], exe[nextProIndex]));
    			currentTime += arri[nextProIndex++];
    		}
    	}  
    	return (double)waitingTime / arri.length;
    }
    
    public double shortestTimeFirst(int[] arri, int[] exe) {
    	if (arri == null || arri.length == 0 || exe == null || exe.length == 0) {
    		return Double.MAX_VALUE;
    	}
    	Queue<Process> queue = new LinkedList<Process>();
    	int currentTime = 0;
    	int waitingTime = 0;
    	int numberOfWaiting = 0;
    	while (!queue.isEmpty() || numberOfWaiting < arri.length) {
    		if (!queue.isEmpty()) {
    			Process cur = queue.poll();
    			waitingTime += currentTime - cur.arriTime;
    			currentTime += cur.exeTime;
    			List<Process> temp = new ArrayList<Process>();
    			for (int i = numberOfWaiting; i < arri.length && arri[i] < currentTime; i++) {
    				temp.add(new Process(arri[i], exe[i]));
    			} 
    			Collections.sort(temp, ProcessComparator);
    			for (int i = 0; i < temp.size(); i++) {
    				queue.offer(temp.get(i));
    				numberOfWaiting++;
    			}
    		}
    		else {
    			queue.offer(new Process(arri[numberOfWaiting], exe[numberOfWaiting]));
    			currentTime = arri[numberOfWaiting++];
    		}
    	}
    	return (double)waitingTime / arri.length;
    }
    
    public Comparator<Process> ProcessComparator = new Comparator<Process>() {
    	public int compare(Process p1, Process p2) {
    		return p1.exeTime - p2.exeTime;
    	}
    };
    
    
    public int rightRotate(String word1, String word2) {
    	 if (word1 == null || word2 == null || word1.length() == 0 || word2.length()
    	== 0 || word1.length() != word2.length()) {
    	 return -1;
    	 }
    	 String str = word1 + word1;
    	 return str.indexOf(word2) != -1 ? 1 : -1;
    }
    
    public int findGCD(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		throw new NullPointerException();
    	}
    	int res = nums[0];
    	for (int i = 1; i < nums.length; i++) {
    		res = findGCDofTwoNumbers(res, nums[i]);
    	}
    	return res;
    }
    
    public int findLCM(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		throw new NullPointerException();
    	}
    	int res = nums[0];
    	for (int i = 1; i < nums.length; i++) {
    		res = (res * nums[i]) / findGCDofTwoNumbers(res, nums[i]);
    	}
    	return res;
    }
    private int findGCDofTwoNumbers(int a, int b) {
    	if (a == 0 || b == 0) {
    		return (a == 0) ? a : b;
    	}
    	else if (a == 1 || b == 1) {
    		return (a == 1) ? a : b;
    	}
    	else {
    		int max = Math.max(a, b);
    		int min = Math.min(a, b);
    		if (max % min != 0) {
    			return findGCDofTwoNumbers(min, max % min);
    		}
    		else {
    			return min;
    		}
    	}
    }
    
    public void moveZeroesToLeft(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return;
    	}
    	int slow = nums.length - 1;
    	int fast = nums.length - 1;
    	while (fast >= 0) {
    		if (nums[slow] != 0) {
    			slow--;
    			fast--;
    		}
    		else {
    			while (fast >= 0) {
    				if (nums[fast] != 0) {
    					break;
    				}
    				fast--;
    			}
    			if (fast >= 0) {
    				swap(nums, slow, fast);
    			}
    		}
    	}
    }
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) {
            return true;
        }
        return verifyHelper(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean verifyHelper(int[] preorder, int start, int end, int lowerbound, int higherbound) {
        if (start > end) {
            return true;
        }
        int rootVal = preorder[start];
        if (rootVal > higherbound || rootVal < lowerbound) {
            return false;
        }
        int rightSubTreeIndex = start + 1;
        while (rightSubTreeIndex <= end) {
            if (preorder[rightSubTreeIndex] > rootVal) {
                break;
            }
            rightSubTreeIndex++;
        }
        if (rightSubTreeIndex == end + 1) {
        	return verifyHelper(preorder, start + 1, end, lowerbound, rootVal);
        }
        else {
        	return verifyHelper(preorder, start + 1, rightSubTreeIndex - 1, lowerbound, rootVal) && verifyHelper(preorder, rightSubTreeIndex, end, rootVal, higherbound);
        }
    }
    
    public int findMiss(int[] input, int size) {
    	if (input == null || input.length == 0) {
    		return 0;
    	}
    	else {
    		int res = 0;
    		Vector<Integer> cache = new Vector<Integer>();
    		for (int i = 0; i < input.length; i++) {
    			int pos = cache.indexOf(input[i]);
    			if (pos != -1) {
    				cache.remove(pos);
    				cache.add(input[i]);
    			}
    			else {
    				res++;
    				if (cache.size() == size) {
    					cache.remove(0);
    				}
    				cache.add(input[i]);
    			}
    		}
    		return res;
    	}
    }
    
    public int findMinPath(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int res  = 0;
    	res += root.val;
    	int left = findMinPath(root.left);
    	int right = findMinPath(root.right);
    	res += Math.min(left, right);
    	return res;
    }
    
    public boolean findPathInMaze(int[][] maze) {
    	if (maze == null || maze.length == 0 || maze[0].length == 0) {
    		return false;
    	}
    	Queue<Step> queue = new LinkedList<Step>();
    	queue.offer(new Step(0, 0));
    	if (maze[0][0] == 9) {
    		return true;
    	}
    	if (maze[0][0] == 0) {
    		return false;
    	}
    	while (!queue.isEmpty()) {
    		Step cur = queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int nextX = cur.x + dx[i];
    			int nextY = cur.y + dy[i];
    			if (nextX >= 0 && nextX < maze.length && nextY >= 0 && nextY < maze[cur.x].length && maze[cur.x][cur.y] == 1) {
    				if (maze[nextX][nextY] == 1) {
    					queue.offer(new Step(nextX, nextY));
    				}
    				if (maze[nextX][nextY] == 9) {
        				return true;
        			}
    			}
    			
    		}
    		maze[cur.x][cur.y] = 0;
    	}
    	return false;
    }
    
    
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return result;
        }
        int[] sum = new int[A.length + 1];
        sum[0] = 0;
        for (int i = 0; i < A.length; i++) {
            sum[i + 1] = sum[i] + A[i];
            
        }
        for (int i = 0; i < sum.length; i++) {
        	System.out.print(sum[i]);
        	System.out.print(' ');
        }
        int min = 0;
        int max = 0;
        int maxSum = 0;
        int resultMin = 0;
        int resultMax = 0;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] < sum[min]) {
                min = i;
                max = i;
            }
            if (maxSum < sum[i] - sum[min]) {
                max = i - 1;
                maxSum = sum[i] - sum[min];
                resultMin = min;
                resultMax = max;
            }
        }
        result.add(resultMin);
        result.add(resultMax);
        return result;
    }
    
    public int[] plusOne(int[] digits) {
        // Write your code here
        if (digits == null || digits.length == 0) {
            return null;
        }
        int carries = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carries;
            if (i == digits.length - 1) {
                sum++;
            }
            stack.push(sum % 10);
            carries = sum / 10;
        }
        if (carries == 1) {
            stack.push(carries);
        }
        
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
        	res[i] = stack.pop();
        	System.out.println(res[i]);
        	i++;
        }
        return res;
    }
    
    public int atoi(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean sign = (str.charAt(0) != '-');
        int res = 0;
        int start = 0;
        if (sign) {
            start = 0;
        }
        else {
            start = 1;
        }
        
        while (start < str.length() && str.charAt(start) != '.') {
            if (res < Integer.MAX_VALUE / 10) {
                res = 10 * res + (str.charAt(start) - '0');
            }
            else {
                return (sign) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            start++;
        }
        return (sign) ? res : -res;
    }
    public List<Integer> numbersByRecursion(int n) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();
        if (n <= 0) {
            return result;
        }
        printNumber(n, result, 0, 0);
        return result;
        
    }
    
    private void printNumber(int n, List<Integer> result, int num, int index) {
    	if (index == n) {
    		if (num > 0) {
    			result.add(num);
    		}
    		return;
    	}
    	for (int i = 0; i < 10; i++) {
    		printNumber(n, result, num * 10 + i, index + 1);
    	}
    }
    
    
    public boolean isMatch(String s, String p) {
        // write your code here
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() == 1) {
            if (s.length() < 1) {
                return false;
            }
            else if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        if (p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            }
            else if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        else {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            else {
                int i = 0;
                while (i < s.length() && (p.charAt(0) == s.charAt(i) || p.charAt(0) == '.')) {
                    if (isMatch(s.substring(i + 1), p.substring(2))) {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        }
    }
    
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null && l2 == null) {
            return l1;
        }
        else if (l1 == null || l2 == null) {
            return (l1 == null) ? l2 : l1;
        }
        long num1 = 0;
        long num2 = 0;
        long factorial1 = 1;
        long factorial2 = 1;
        
        while (l1 != null) {
            num1 = (long)l1.val * factorial1 + num1;
            factorial1 *= 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            num2 = (long)l2.val * factorial2 + num2;
            factorial2 *= 10;
            l2 = l2.next;
        }
        long sum = num1 + num2;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        if (sum == 0) {
            return dummy;
        }
        while (sum != 0) {
            ListNode cur = new ListNode((int)sum % 10);
            System.out.println((int)(sum % 10));
            sum /= 10;
            prev.next = cur;
            prev = prev.next;
        }
        return dummy.next;
    }
    
    public int evaluateExpression(String[] expression) {
        // write your code here
        ArrayList<String> result = expressionToPolish(expression);
        if (expression == null || expression.length == 0 || result.size() == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<String>();
        Set<String> operation = new HashSet<String>();
        operation.add("+");
        operation.add("-");
        operation.add("*");
        operation.add("/");
        int i = 0;
        while (!stack.isEmpty() || i < result.size()) {
            if (!stack.isEmpty() && !operation.contains(stack.peek())) {
                int num2 = Integer.parseInt(stack.pop());
                if (!stack.isEmpty() && !operation.contains(stack.peek())) {
                    int num1 = Integer.parseInt(stack.pop());
                    String op = stack.pop();
                    String temp = "";
                    if (op.equals("+")) {
                        temp = String.valueOf(num1 + num2);
                    }
                    else if (op.equals("-")) {
                        temp = String.valueOf(num1 - num2);
                    }
                    else if (op.equals("*")) {
                        temp = String.valueOf(num1 * num2);
                    }
                    else {
                        temp = String.valueOf(num1 / num2);
                    }
                    if (!stack.isEmpty() && !operation.contains(stack.peek())) {
                    	stack.push(temp);
                    	continue;
                    }
                    else {
                    	stack.push(temp);
                    }
                }
                else {
                    stack.push(String.valueOf(num2));
                }
            }
            
            if (i < result.size()) {
                stack.push(result.get(i));
                i++;
            }
            if (i == result.size() && stack.size() == 1) {
                break;
            }
        }
        
        return Integer.parseInt(stack.peek());
    }
    
    
    public ArrayList<String> expressionToPolish(String[] expression) {
        ArrayList<String> result = new ArrayList<String>();
        ExpressionTreeNode root = expressionTreeBuild(expression);
        dfsExpressionTree(root, result);
        return result;
    }
    
    private void dfsExpressionTree(ExpressionTreeNode root, ArrayList<String> result) {
        if (root == null) {
            return;
        }
        result.add(root.ss);
        dfsExpressionTree(root.left, result);
        dfsExpressionTree(root.right, result);
    }
    
    private ExpressionTreeNode expressionTreeBuild(String[] expression) {
        int val = 0;
        int base = 0;
        Stack<NewTreeNode> stack = new Stack<NewTreeNode>();
        for (int i = 0; i <= expression.length; i++) {
            if (i != expression.length) {
                if (expression[i].equals("(")) {
                    base += 10;
                    continue;
                }
                if (expression[i].equals(")")) {
                    base -= 10;
                    continue;
                }
                val = getBase(expression[i], base);
            }
            NewTreeNode right = (i == expression.length) ? new NewTreeNode(Integer.MIN_VALUE, "") : new NewTreeNode(val, expression[i]);
            while (!stack.isEmpty()) {
                if (right.val <= stack.peek().val) {
                    NewTreeNode nodeNow = stack.pop();
                    if (stack.isEmpty()) {
                        right.node.left = nodeNow.node;
                    }
                    else {
                        NewTreeNode left = stack.peek();
                        if (left.val < right.val) {
                            right.node.left = nodeNow.node;
                        }
                        else {
                            left.node.right = nodeNow.node;
                        }
                    }
                }
                else {
                    break;
                }
            }
            stack.push(right);
        }
        return stack.peek().node.left;
    }
    
    private int getBase(String s, int base) {
        if (s.equals("+") || s.equals("-")) {
            return base + 1;
        }
        if (s.equals("*") || s.equals("/")) {
            return base + 2;
        }
        return Integer.MAX_VALUE;
    }
    
    public class ExpressionTreeNode {
        String ss;
        ExpressionTreeNode left, right;
        public ExpressionTreeNode(String ss) {
            this.ss = ss;
            this.left = this.right = null;
        }
    }
    
    public class NewTreeNode {
        int val;
        ExpressionTreeNode node;
        public NewTreeNode(int val, String ss) {
            this.val = val;
            this.node = new ExpressionTreeNode(ss);
        }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        // Write your code here
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        cur = head;
        int remain = length % k;
        length -= remain;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode groupHead = dummy;
        ListNode groupTail = head;
        ListNode prev = dummy;
        
        int count = 0;
        while (count < length) {
            for (int i = 0; i < k; i++) {
            	//System.out.println(cur.val);
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
                count++;
            }
            groupHead.next = prev;
            prev = groupTail;
            groupTail.next = cur;
            groupHead = groupTail;
            groupTail = cur;
            //System.out.println(cur.val);
        }
        return dummy.next;
    }
    
    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        else if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (!sortString(s1).equals(sortString(s2))) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        for (int i = 1; i <= s1.length() - 1; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);
            String s31 = s2.substring(s2.length() - i);
            String s32 = s2.substring(0, s2.length() - i);
            
            if (isScramble(s11, s21) && isScramble(s21, s22) || isScramble(s11, s31) && isScramble(s12, s32)) {
                return true;
            }
        }
        return false;
    }
    
    private String sortString(String source) {
        char[] charArr = source.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }
    
    
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // Write your code here
        if (root == null) {
            return null;
        }
        List<Integer> inorder = traversal(root);
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode pre = new DoublyListNode(inorder.get(0));
        dummy.next = pre;
        for (int i = 1; i < inorder.size(); i++) {
            DoublyListNode cur = new DoublyListNode(inorder.get(i));
            pre.next = cur;
            cur.prev = pre;
            pre = pre.next;
            cur = cur.next;
        }
        return dummy.next;
    }
    
    public List<Integer> traversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        } 
        
        List<Integer> left = traversal(root.left);
        List<Integer> right = traversal(root.right);
        
        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        return result;
    }
    
    
    public String permutationIndexII(String s) {
        // Write your code here
		HashMap<Character, BigInteger> hash = new HashMap<Character, BigInteger>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (hash.containsKey(c)) {
				
				hash.put(c, hash.get(c).add(BigInteger.ONE));
			} else {
				hash.put(c, BigInteger.ONE);
			}
		}
		BigInteger ans = BigInteger.ZERO;
		
		for (int i = 0; i < len; i++) {
			HashMap<Character, Integer> flag = new HashMap<Character, Integer>();
			char ci = s.charAt(i);
			for (int j = i + 1; j < len; j++) {
				char cj = s.charAt(j);
				if (cj < ci && !flag.containsKey(cj)) {
				    flag.put(cj, 1);
				
					hash.put(cj, hash.get(cj).subtract(BigInteger.ONE));
					ans = ans.add(generateNum(hash));
					hash.put(cj, hash.get(cj).add(BigInteger.ONE));
					
				}
			
			}
			hash.put(ci, hash.get(ci).subtract(BigInteger.ONE));
		}
		ans = ans.add(BigInteger.ONE);
		return ans.toString();
    }
    
    public BigInteger fac(BigInteger numerator) {
			
		BigInteger now = new BigInteger("1");
		BigInteger it = new BigInteger("1");
		for (; it.compareTo(numerator) <= 0; it = it.add(BigInteger.ONE)) {
			
			now = now.multiply(it);
		}
		return now;
	}
	private BigInteger generateNum(HashMap<Character, BigInteger> hash) {
		BigInteger denominator = new BigInteger("1");
		BigInteger sum = BigInteger.ZERO;
		for (BigInteger val : hash.values()) {
			if (val.equals(BigInteger.ZERO)) {	
				continue;
			}
			denominator = denominator.multiply(fac(val));
			sum = sum.add(val);
		}
		if (sum.equals(BigInteger.ZERO)) {
			return sum;
		}
		return fac(sum).divide(denominator);
	}
	
	public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int hIndex = 1;
        Arrays.sort(citations);
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= hIndex) {
                hIndex++;
            } else {
                return hIndex - 1;
            }
        }
        return citations.length;
    }
	
	public boolean canWinNim(int n) {
		if (n <= 3) {
            return true;
        }
        boolean[] f = new boolean[3];
        for (int i = 1; i <= n; i++) {
            f[i % 3] = !f[(i - 1) % 3] || !f[(i - 2) % 3] || !f[(i - 3) % 3];
        }
        return f[n % 3];
    }
	
	public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        ArrayList<Integer> rst = new ArrayList<Integer>();
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (maxElement < A[i]) {
                maxElement = A[i];
            }
        }
        SegmentTreeNode root = buildSegmentTree(0, maxElement);
        for (int i = 0; i < A.length; i++) {
            modify(root, A[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            rst.add(query(root, 0, queries[i] - 1));
        }
        return rst;
    }
    
    private int query(SegmentTreeNode root, int start, int end) {
    	/*
    	if (root == null || start > end) {
            return 0;
        }
        */
        if (start <= root.start && root.end <= end) {
            return root.count;
        }
        int left = root.start + (root.end - root.start) / 2;
        int right = left + 1;
        if (start <= left && right <= end) {
            return query(root.left, start, left) + query(root.right, right, end);
        } 
        else if (end <= left) {
            return query(root.left, start, end);
        } else {
            return query(root.right, start, end);
        }
        
    }
    
    private void modify(SegmentTreeNode node, int val) {
        if (node.start == val && node.end == val) {
            node.count++;
            return;
        }
        if (val < node.start || val > node.end) {
            return;
        }
        modify(node.left, val);
        modify(node.right, val);
        node.count = node.left.count + node.right.count;
        System.out.print(node.count);
        System.out.print(' ');
        System.out.print(node.start);
        System.out.print(' ');
        System.out.println(node.end);
    }
    
    private SegmentTreeNode buildSegmentTree(int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(0, start, end);
        }
        SegmentTreeNode root = new SegmentTreeNode(0, start, end);
        root.left = buildSegmentTree(start, (start + end) / 2);
        root.right = buildSegmentTree((start + end) / 2 + 1, end);
        return root;
    }
    
    class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int start;
        int end;
        int count;
        public SegmentTreeNode(int count, int start, int end) {
            this.left = this.right = null;
            this.count = count;
            this.start = start;
            this.end = end;
        }
    }
    
    public String countAndSay(int n) {
        // Write your code here
        String[] res = new String[2];
        res[0] = "1";
        for (int i = 1; i < n; i++) {
            int count = 1;
            int j = 0;
            int cur = i % 2;
            int pre = (i - 1) % 2;
            res[cur] = "";
            while (j < res[pre].length()) {
                
                    int k = j;
                    while (k < res[pre].length() - 1) {
                        if (res[pre].charAt(k) == res[pre].charAt(k + 1)) {
                            count++;
                        } else {
                        	break;
                        }
                        k++;
                    }
                    res[cur] += Integer.toString(count) + res[pre].charAt(j);
                    j += count;
                    count = 1;
            }
        }
        return res[(n - 1) % 2];
    }
    
    public int wrongSearch(int[] array, int target) {
    	int left = 0;
    	int right = array.length - 1;
    	while (left < right) {
    		int mid = (left + right + 1) / 2;
    		
    		if (array[mid] > target) {
    			right = mid - 1;
    		} else {
    			left = mid + 1;
    		}
    	}
    	if (array[right] == target) {
    		return right;
    	}
    	return -2;
    }
    
    public int count() {
    	Scanner in = new Scanner(System.in);
    	int size = in.nextInt();
    	int[] nums = new int[size];
    	for (int i = 0; i < size; i++) {
    		nums[i] = in.nextInt();
    	}
    	in.close();
    	int count = 0;
        int pivot = nums[0];
        for (int i = 1; i < size; i++) {
            if (pivot < nums[i]) {
                pivot = nums[i];
            } else {
                pivot = nums[i + 1] + 1;
                count++;
            }
        }
        return count;
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        // Write your code here
        List<String> res = new ArrayList<String>();
        List<List<Integer>> valueList = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<Integer>();
        findPathHelper(root, valueList, path);
        transferToStringList(valueList, res);
        return res;
    }
    
    private void findPathHelper(TreeNode root, List<List<Integer>> valueList, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        System.out.println(root.val);
        if (root.left == null && root.right == null) {
            valueList.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        
        findPathHelper(root.left, valueList, path);
        findPathHelper(root.right, valueList, path);
        path.remove(path.size() - 1);
    }
    
    private void transferToStringList(List<List<Integer>> valueList, List<String> res) {
        for (List<Integer> list : valueList) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(Integer.toString(list.get(i)));
                if (i < list.size() - 1) {
                    sb.append("->");
                }
            }
            res.add(sb.toString());
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            System.out.println(stack.size());
            cur = stack.pop();
            pre = cur;
            cur = cur.right;
            if (cur != null && pre.val >= cur.val) {
                return false;
            }
        }
        return true;
    }
    
    public Set<Integer> longestIncreasingSequence(List<Student> list) {
    	if (list == null || list.size() == 0) {
            return null;
        }
        
        
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        
        for (int i = 0; i < list.size(); i++) {
        	Set<Integer> set = new HashSet<Integer>();
        	set.add(i);
            map.put(i, set);
        }
        Collections.sort(list, StuComparator);
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 1; i < list.size(); i++) {
            Set<Integer> temp = new HashSet<Integer>();
            for (int j = 0; j < i; j++) {
                if (list.get(j).gpa > list.get(i).gpa) {
                    if (map.get(j).size() + 1 > map.get(i).size()) {
                    	temp.clear();
                    	temp.addAll(map.get(j));
                    	temp.add(i);
                    	map.put(i, temp);
                    }
                    
                }
                
            }
            if (temp.size() > set.size()) {
                set.clear();
                set.addAll(temp);
            }
        }
        return set;
    }
    
    public Comparator<Student> StuComparator = new Comparator<Student>() {
    	public int compare(Student s1, Student s2) {
    		return s1.sat - s2.sat;
    	}
    };
    public void showElement(Set<Integer> set, List<Student> stu) {
    	for (Integer i : set) {
    		System.out.print(i + "    ");
    		System.out.print(stu.get(i).gpa + "    ");
    	}
    	System.out.print("\n");
    }
    
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> map = new HashMap<Character, Character>();
        
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                char c = stack.peek();
                if (map.get(c) != null && s.charAt(i) == map.get(c)) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] HP = new int[m][n];
        HP[0][0] = (dungeon[0][0] < 0) ? 1 - dungeon[0][0] : 1;
        int tempHP = (dungeon[0][0] < 0) ? 1 : 1 + dungeon[0][0];
        for (int i = 1; i < m; i++) {
            if (dungeon[i][0] + tempHP <= 0) {
                HP[i][0] = 1 - (dungeon[i][0] + tempHP) + HP[i - 1][0];
                tempHP = 1;
            } else {
                HP[i][0] = HP[i - 1][0];
                tempHP += dungeon[i][0];
            }
        }
        tempHP = (dungeon[0][0] < 0) ? 1 : 1 + dungeon[0][0];
        for (int i = 1; i < n; i++) {
            if (dungeon[0][i] + tempHP <= 0) {
                HP[0][i] = 1 - (dungeon[0][i] + tempHP) + HP[0][i - 1];
                tempHP = 1;
            } else {
                HP[0][i] = HP[0][i - 1];
                tempHP += dungeon[0][i - 1];
            }
        }
        if (m >= 2 && n >= 2) {
            tempHP = (dungeon[0][0] < 0) ? 1 - dungeon[0][0] : 1 + dungeon[0][0];
            if (dungeon[0][1] <= 0 && dungeon[1][0] <= 0) {
                tempHP = 1;
            } else if (dungeon[0][1] * dungeon[1][0] > 0){
                tempHP += Math.max(dungeon[0][1], dungeon[1][0]);
            } else if (dungeon[0][1] > 0) {
                tempHP += dungeon[0][1];
            } else {
                tempHP += dungeon[1][0];
            }
            
        }
        
        System.out.println(tempHP);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int min = Math.min(HP[i - 1][j], HP[i][j - 1]);
                if (tempHP + dungeon[i][j] <= 0) {
                    HP[i][j] =  1 - (tempHP + dungeon[i][j]) + min;
                    tempHP = 1;
                } else {
                    HP[i][j] = min;
                    tempHP += dungeon[i][j];
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		System.out.print(HP[i][j]);
        		System.out.print(' ');
        	}
        	System.out.println('\n');
        }
        return HP[m - 1][n - 1];
    }
    
    public String constructAbbr(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        } else {
            return s.substring(0, 1) + Integer.toString(s.length() - 2) + s.substring(s.length() - 1);
        }
    }
    
    public int[] findNextArray(String s) {
    	if (s == null || s.length() == 0) {
    		return null;
    	}
    	int[] next = new int[s.length() + 1];
    	next[0] = 0;
    	int j = 0;
    	while (j < s.length()) {
    		if (s.charAt(j) == s.charAt(0)) {
    			next[j + 1] = next[j] + 1;
    		} else {
    			next[j + 1] = next[next[j]];
    		}
    		j++;
    	}
    	return next;
    }
    
    public List<List<String>> findStringsCombination(List<List<String>> source) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	if (source == null || source.size() == 0) {
    		return res;
    	}
    	List<String> list = new ArrayList<String>();
    	helper(source, res, list, 0);
    	return res;
    }
    
    private void helper(List<List<String>> source, List<List<String>> res, List<String> list, int listIndex) {
    	if (list.size() == source.size()) {
    		res.add(new ArrayList<String>(list));
    		return;
    	}
    	
    	for (int i = 0; i < source.get(listIndex).size(); i++) {
    		list.add(source.get(listIndex).get(i));
    		helper(source, res, list, listIndex + 1);
    		list.remove(list.size() - 1);
    	}
    }
    
    public void rerangeArrayinplace2(int[] a) {
    	int pos = 0;
        int neg = 0;
        int i,j;
        int max = Integer.MIN_VALUE;
        for(i=0; i<a.length; i++){
            if(a[i]<0) neg++;
            else pos++;
            if(a[i]>max) max = a[i];
        }
        max++;
        if(neg==0 || pos == 0) return;//already sorted
        i=0;
        j=1;
        
        while(true){
        	System.out.println(Arrays.toString(a));
            while(i<=neg && a[i]<0) i++;
            while(j<a.length && a[j]>=0) j++;
            if(i>neg || j>=a.length) break;
            a[i]+= max*(i+1);
            
            System.out.print("i= ");
            System.out.print(i);
            System.out.print(" j= ");
            System.out.println(j);
            swap(a,i,j);
            
        }
        System.out.println("");
        i = a.length-1;
        while(i>=neg){
            int div = a[i]/max;
            if(div == 0) i--;
            else{
                a[i]%=max;
                swap(a,i,neg+div-2);// minus 2 since a[i]+= max*(i+1);
            }
            System.out.println(Arrays.toString(a));
        }
    }
    
    public int[] rerangeArray(int[] nums) {
    	if (nums == null || nums.length <= 1) {
    		return nums;
    	}
    	int index = 0;
    	int[] res = new int[nums.length];
    	int resIndex = 0;
    	while (index < nums.length) {
    		if (nums[index] < 0) {
    			res[resIndex++] = nums[index];
    		}
    		index++;
    	}
    	index = 0;
    	while (index < nums.length) {
    		if (nums[index] >= 0) {
    			res[resIndex++] = nums[index];
    		}
    		index++;
    	}
    	return res;
    }
    
    public void rerangeArrayinplace(int[] nums) {
    	if (nums == null || nums.length <= 1) {
    		return;
    	}
    	int positiveIndex = 0;
    	int negativeIndex = 0;
    	while (negativeIndex < nums.length && positiveIndex < nums.length) {
    		if (nums[negativeIndex] >= 0) {
    			negativeIndex++;
    			continue;
    		}
    		for (int i = negativeIndex; i > positiveIndex; i--) {
    			swap(nums, i, i - 1);
    		}
    		negativeIndex += 1;
    		positiveIndex += 1;
    	}
    }
    

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= firstMin) {
                firstMin = nums[i];
            } else if (nums[i] <= secondMin) {
                secondMin = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }
    
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && nums[i] > nums[i - 1] || i % 2 == 0 && nums[i] < nums[i - 1]) {
                continue;
            } else {
                swap(nums, i, i - 1);
            }
        }
    }
    
    public int solution(String E, String L) {
        // write your code in Java SE 8
        int result = 2;
        String[] EArr = E.split(":");
        String[] LArr = L.split(":");
        int Ehour = Integer.parseInt(EArr[0]);
        int Emin = Integer.parseInt(EArr[1]);
        int Lhour = Integer.parseInt(LArr[0]);
        int Lmin = Integer.parseInt(LArr[1]);
        System.out.println(Ehour + " " + Emin);
        System.out.println(Lhour + " " + Lmin);
        if ((Lhour == Ehour && Lmin > Emin) || (Lhour == Ehour + 1 && Lmin <= Emin)) {
            result += 3;
        } else {
            int hours = Lhour - Ehour - 1;
            if (Lmin > Emin) {
                hours++;
            }
            result += (3 + 4 * hours);
        }
        return result;
    }
    
    
}

class MyQueue {
    private Stack<Integer> stkIn = new Stack<Integer>();
    private Stack<Integer> stkOut = new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
        stkIn.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (stkOut.isEmpty()) {
            while (!stkIn.isEmpty()) {
                stkOut.push(stkIn.peek());
                stkIn.pop();
            }
        }
        stkOut.pop();
    }

    // Get the front element.
    public int peek() {
        if (stkOut.isEmpty()) {
            while (!stkIn.isEmpty()) {
                stkOut.push(stkIn.peek());
                stkIn.pop();
            }
        }
        return stkOut.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stkIn.isEmpty() && stkOut.isEmpty();
    }
}

class Student {
	int sat;
	int gpa;
	public Student(int sat, int gpa) {
		this.sat = sat;
		this.gpa = gpa;
	}
}
class DoublyListNode {
	int val;
	DoublyListNode prev;
	DoublyListNode next;
	
	DoublyListNode(int val) {
		this.val = val;
		this.prev = this.next = null;
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}
class Step {
	int x;
	int y;
	int val;
	Step(int x, int y) {
		this.x = x;
		this.y = y;
		this.val = 0;
	}
}

class Process {
	int arriTime;
	int exeTime;
	public Process(int arriTime, int exeTime) {
		this.arriTime = arriTime;
		this.exeTime = exeTime;
	}
}

class MyHashMap {
	public static final int mapDefaultedSize = 32;
	public HashNode[] table;
	public MyHashMap() {
		table = new HashNode[mapDefaultedSize];
	}
	
	public MyHashMap(int capacity) {
		table = new HashNode[capacity];
	}
	
	public void put(Integer key, Integer value) {
		int h = hash(key.hashCode());
		int index = findIndex(h, table.length);
		for (HashNode entry = table[index]; entry != null; entry = entry.next) {
			if (entry.key == key) {
				entry.value = value;
				return;
			}
		}
		addEntry(key, value, index);
	}
	
	public void addEntry(Integer key, Integer value, int index) {
		HashNode node = table[index];
		table[index] = new HashNode(key, value);
		table[index].next = node;
		return;
	}
	
	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	static int findIndex(int h, int size) {
		return h & (size - 1);
	}
}

class HashNode {
	int key;
	int value;
	HashNode next;
	public HashNode(int key, int value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}

class MyBoolean {
	boolean value;
	public MyBoolean(boolean val) {
		this.value = val;
	}
}






