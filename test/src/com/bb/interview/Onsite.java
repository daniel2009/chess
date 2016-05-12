package com.bb.interview;

import java.util.*;



public class Onsite {
	public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        list.add(Integer.toString(root.val));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    list.add(Integer.toString(cur.left.val));
                } else {
                    list.add("null");
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    list.add(Integer.toString(cur.right.val));
                } else {
                    list.add("null");
                }
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).equals("null")) {
                list.remove(list.size() - 1);
            } else {
            	break;
            }
        }
        return list.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nodes_val = data.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes_val[0]));
        queue.offer(root);
        int index = 1;
        while (index < nodes_val.length) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (index < nodes_val.length && !nodes_val[index].equals("null")) {
                    cur.left = new TreeNode(Integer.parseInt(nodes_val[index]));
                    queue.offer(cur.left);
                }
                index++;
                if (index < nodes_val.length && !nodes_val[index].equals("null")) {
                    cur.right = new TreeNode(Integer.parseInt(nodes_val[index]));
                    queue.offer(cur.right);
                }
                index++;
            }
        }
        return root;
    }
    
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        dummy.next = head.next;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
    
    public List<DtTreeNode> findNode(DtTreeNode[] nodes, DtTreeNode n) {
    	if (n == null || nodes == null || nodes.length == 0) {
    		return null;
    	} 
    	Map<DtTreeNode, List<DtTreeNode>> map = new HashMap<DtTreeNode, List<DtTreeNode>>();
    	for (DtTreeNode dtn : nodes) {
    		if (!map.containsKey(dtn.father)) {
    			map.put(dtn.father, new ArrayList<DtTreeNode>());
    		}
    		map.get(dtn.father).add(dtn);
    	}
    	
    	List<DtTreeNode> list = new ArrayList<DtTreeNode>();
    	for (DtTreeNode node : map.keySet()) {
    		if (node != null && n.val == node.val) {
    			helper(map, node, list);
    		}
    	}
    	return list;
    }
    
    private void helper(Map<DtTreeNode, List<DtTreeNode>> map, DtTreeNode cur, List<DtTreeNode> list) {
    	if (map.containsKey(cur)) {
    		list.addAll(map.get(cur));
    		for (DtTreeNode node : map.get(cur)) {
        		helper(map, node, list);
        	}
    	}
    } 
    
    public List<Integer> traverseQpTree(QpTreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
    		return list;
    	}
    	List<Integer> up = traverseQpTree(root.up);
    	List<Integer> down = traverseQpTree(root.down);
    	List<Integer> right = traverseQpTree(root.right);
    	list.addAll(up);
    	list.add(root.val);
    	list.addAll(down);
    	list.addAll(right);
    	return list;
    }
    
    public boolean[] rateLimitWithArray(double[] requests, int rate, int per) {
    	boolean[] Isacquired = new boolean[requests.length];
    	double[] queue = new double[rate];
    	for (int i = 0; i < requests.length; i++) {
    		if (i < queue.length) {
    			queue[i] = requests[i];
    			Isacquired[i] = true;
    		} else if (requests[i] - queue[(i - rate) % rate] <= per){
    			Isacquired[i] = false;
    		} else {
    			
    			queue[(i - rate) % rate] = requests[i];
    			Isacquired[i] = true;
    		}
    	}
    	return Isacquired;
    }
    
    public boolean[] rateLimtiWithQueue(double[] requests, int rate, int per) {
    	boolean[] Isacquired = new boolean[requests.length];
    	Queue<Double> queue = new LinkedList<Double>();
    	for (int i = 0; i < requests.length; i++) {
    		if (i < rate) {
    			queue.offer(requests[i]);
    			Isacquired[i] = true;
    		} else if (requests[i] - queue.peek() <= per) {
    			Isacquired[i] = false;
    		} else {
    			queue.poll();
    			queue.offer(requests[i]);
    			Isacquired[i] = true;
    		}
    	}
    	return Isacquired;
    }
    
    public ListNode addTwoNum(ListNode head1, ListNode head2) {
    	if (head1 == null) {
    		return head2;
    	}
    	if (head2 == null) {
    		return head1;
    	}
    	int len1 = 0;
    	int len2 = 0;
    	ListNode cur1 = head1;
    	ListNode cur2 = head2;
    	while (cur1 != null) {
    		len1++;
    		cur1 = cur1.next;
    	}
    	
    	while (cur2 != null) {
    		len2++;
    		cur2 = cur2.next;
    	}
    	
    	if (len2 > len1) {
    		ListNode temp = null;
    		temp = head1;
    		head1 = head2;
    		head2 = temp;
    		int tempLen = len2;
    		len2 = len1;
    		len1 = tempLen;
    	}
    	
    	if (len1 != len2) {
    		int i = 0;
    		cur1 = head1;
    		while (i < len1 - len2) {
    			cur1 = cur1.next;
    			i++;
    		}
    		ResultType rear = addSameLength(cur1, head2);
    		ResultType front = addCarry(head1, rear.carry, len1 - len2);
    		ListNode cur = front.node;
    		i = 0;
    		while (i < len1 - len2 - 1) {
    			cur = cur.next;
    			i++;
    		}
    		cur.next = rear.node;
    		if (front.carry == 0) {
    			return front.node;
    		} else {
    			ListNode sum = new ListNode(front.carry);
    			sum.next = front.node;
    			return sum;
    		}
    	} else {
    		ResultType rt = addSameLength(head1, head2);
    		if (rt.carry == 0) {
    			return rt.node;
    		} else {
    			ListNode sum = new ListNode(rt.carry);
    			sum.next = rt.node;
    			return sum;
    		}
    	}
    }
    
    private ResultType addCarry(ListNode head1, int carry, int len) {
    	if (len == 0) {
    		return new ResultType(carry, null);
    	}
    	ResultType last = addCarry(head1.next, carry, len - 1);
    	ResultType now = new ResultType(0, null);
    	int sum = head1.val + last.carry;
		now.carry = sum / 10;
		int value = sum % 10;
		ListNode node = new ListNode(value);
		now.node = node;
		now.node.next = last.node;
		return now;
    }
    
    private ResultType addSameLength(ListNode head1, ListNode head2) {
    	if (head1 == null && head2 == null) {
    		return new ResultType(0, null);
    	}
    	ResultType nextNode = addSameLength(head1.next, head2.next);
    	int sum = head1.val + head2.val + nextNode.carry;
    	int carry = sum / 10;
    	int value = sum % 10;
    	ResultType curNode = new ResultType(carry, new ListNode(value));
    	curNode.node.next = nextNode.node;
    	return curNode;
    }
    
    public boolean isPalindrome(ListNode head) {
    	if (head == null || head.next == null) {
    		return true;
    	}
    	ListNode slow = head;
    	ListNode fast = head;
    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	ListNode mid = reverseList(slow.next);
    	
    	while (mid != null) {
    		if (mid.val != head.val) {
    			return false;
    		}
    		mid = mid.next;
    		head = head.next;
    	}
    	return true;
    }
    
    
    private ListNode reverseList(ListNode head) {
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode prev = null;
    	ListNode curr = head;
    	
    	while (curr != null) {
    		ListNode next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	return prev;
    }
    
    public boolean isSameTree(TreeNode root1, TreeNode root2) {
    	if (root1 == null && root2 == null) {
    		return true;
    	} else if (root1 == null || root2 == null) {
    		return false;
    	} else if (root1.val != root2.val) {
    		return false;
    	} else {
    		return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    	}
    }
    
    public boolean isMirrorTree(TreeNode root1, TreeNode root2) {
    	if (root1 == null && root2 == null) {
    		return true;
    	} else if (root1 == null || root2 == null) {
    		return false;
    	} else if (root1.val != root2.val) {
    		return false;
    	} else {
    		return isMirrorTree(root1.left, root2.right) && isMirrorTree(root1.right, root2.left);
    	}
    }
    
    public boolean isSubTree(TreeNode root, TreeNode p) {
    	if (p == null) {
    		return true;
    	} else if (root == null) {
    		return false;
    	} else {
    		return isSameTree(root, p) || isSubTree(root.left, p) || isSubTree(root.right, p);
    	}
    }
    
    public List<Integer> nonrecursiveInorder(TreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
    		return list;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode cur = root;
    	while (cur != null || !stack.isEmpty()) {
    		while (cur != null) {
    			stack.push(cur);
    			cur = cur.left;
    		}
    		cur = stack.pop();
    		list.add(cur.val);
    		cur = cur.right;
    	}
    	return list;
    }
    
    class BSTIterator {
    	Stack<TreeNode> stack = null;
    	TreeNode cur = null;
    	public BSTIterator(TreeNode root) {
    		cur = root;
    		stack = new Stack<TreeNode>();
    		while (cur != null) {
    			stack.push(cur);
    			cur = cur.left;
    		}
    	}
    	
    	public boolean hasNext() {
    		return !stack.isEmpty();
    	}
    	
    	public int next() {
    		cur = stack.pop();
    		int res = cur.val;
    		cur = cur.right;
    		while (cur != null) {
    			stack.push(cur);
    			cur = cur.left;
    		}
    		return res;
    	}
    }
    
    public List<Integer> printLevel(TreeNode root, int level) {
    	List<Integer> list = new ArrayList<Integer>();
    	if (root == null || level < 0) {
    		return list;
    	}
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	int curLevel = 0;
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			TreeNode cur = queue.poll();
    			if (cur.left != null) {
    				queue.offer(cur.left);
    			}
    			if (cur.right != null) {
    				queue.offer(cur.right);
    			}
    			if (curLevel == level) {
    				list.add(cur.val);
    			}
    		}
    		curLevel++;
    	}
    	return list;
    }
    
    public TreeNode upsideDown(TreeNode root) {
    	if (root == null || root.left == null && root.right == null) {
    		return root;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode newRoot = null;
    	TreeNode cur = root;
    	while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
    	newRoot = stack.peek();
    	while (true) {
    		cur = stack.pop();
    		if (!stack.isEmpty()) {
    			cur.left = stack.peek().right;
        		cur.right = stack.peek();
    		} else {
    			cur.left = null;
    			cur.right = null;
    			break;
    		}
    	}
    	return newRoot;
    }
    
    public TreeNode convertArrayToBST(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return null;
    	}
    	return convertHelper(nums, 0, nums.length - 1);
    }
    
    private TreeNode convertHelper(int[] nums, int start, int end) {
    	if (start > end) {
    		return null;
    	}
    	int mid = start + (end - start) / 2;
    	TreeNode root = new TreeNode(mid);
    	root.left = convertHelper(nums, start, mid - 1);
    	root.right = convertHelper(nums, mid + 1, end);
    	return root;
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
    		cur = stack.pop();
    		if (pre != null && pre.val >= cur.val) {
    			return false;
    		}
    		pre = cur;
    		cur = cur.right;
    	}
    	return true;
    }
    
    public void flatten2DLinkedList(ListNode_2D head) {
    	if (head == null) {
    		return;
    	}
    	while (head != null) {
    		if (head.down != null) {
    			ListNode_2D cur = head.down;
    			while (cur.next != null) {
    				cur = cur.next;
    			}
    			cur.next = head.next;
    			head.next = head.down;
    			head.down = null;
    		}
    		head = head.next;
    	}
    }
    
    public RListNode cloneList(RListNode head) {
    	if (head == null) {
    		return null;
    	}
    	Map<RListNode, RListNode> map = new HashMap<RListNode, RListNode>();
    	RListNode dummy = new RListNode(0);
    	RListNode pre = dummy;
    	RListNode newNode = null;
    	while (head != null) {
    		if (map.containsKey(head)) {
    			newNode = map.get(head);
    		} else {
    			newNode = new RListNode(head.val);
    			map.put(head, newNode);
    		}
    		pre.next = newNode;
    		if (head.random != null) {
    			if (!map.containsKey(head.random)) {
    				map.put(head.random, new RListNode(head.random.val));
        		} 
    			newNode.random = map.get(head.random);
    		}
    		
    		pre = newNode;
    		head = head.next;
    	}
    	return dummy.next;
    }
    
    public RListNode cloneList2(RListNode head) {
    	if (head == null) {
    		return null;
    	}
    	copyNext(head);
    	copyRandom(head);
    	return splitList(head);
    }
    
    private void copyNext(RListNode head) {
    	while (head != null) {
    		RListNode newNode = new RListNode(head.val);
    		newNode.next = head.next;
    		head.next = newNode;
    		head = head.next.next;
    	}
    }
    
    private void copyRandom(RListNode head) {
    	while (head != null) {
    		if (head.random != null) {
    			head.next.random = head.random.next;
    		}
    		head = head.next.next;
    	}
    }
    
    private RListNode splitList(RListNode head) {
    	RListNode newHead = head.next;
    	while (head != null) {
    		RListNode temp = head.next;
    		head.next = temp.next;
    		if (head.next != null) {
    			temp.next = head.next.next;
    		}
    		head = head.next;
    	}
    	return newHead;
    }
    
    public int uniquePath(int x, int y, int m, int n) {
    	if (x >= m || y >= n) {
    		return 0;
    	}
    	System.out.println(uniquePathHelper(0, 0, x, y));
    	System.out.println(uniquePathHelper(x, y, m - 1, n - 1));
    	return uniquePathHelper(0, 0, x, y) * uniquePathHelper(x, y, m - 1, n - 1);
    }
    
    private int uniquePathHelper(int startX, int startY, int endX, int endY) {
    	int[][] count = new int[endX - startX + 1][endY - startY + 1];
    	int m = endX - startX + 1;
    	int n = endY - startY + 1;
    	for (int i = 0; i < m; i++) {
    		count[i][0] = 1;
    	}
    	
    	for (int i = 0; i < n; i++) {
    		count[0][i] = 1;
    	}
    	
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    			count[i][j] = count[i - 1][j] + count[i][j - 1];
    		}
    	}
    	return count[m - 1][n - 1];
    }
    
    public List<List<Step>> uniquePath2(int x, int y, int m, int n) {
    	List<List<Step>> temp = new ArrayList<List<Step>>();
    	List<List<Step>> res = new ArrayList<List<Step>>();
    	List<Step> path = new ArrayList<Step>();
    	if (x >= m || y >= n) {
    		return res;
    	}
    	int[] dx = {1, 0};
    	int[] dy = {0, 1};
    	
    	uniquePath2Helper(0, 0, x, y, temp, path, dx, dy);
		
		for (List<Step> list : temp) {
			list.remove(list.size() - 1);
			uniquePath2Helper(x, y, m - 1, n - 1, res, list, dx, dy);
		}
    	
    	return res;
    }
    
    private void uniquePath2Helper(int curX, int curY, int endX, int endY, List<List<Step>> res, List<Step> path, int[] dx, int[] dy) {
    	if (curX > endX || curY > endY) {
    		return;
    	}
    	
    	if (curX == endX && curY == endY) {
    		path.add(new Step(curX, curY));
    		res.add(new ArrayList<Step>(path));
    		path.remove(path.size() - 1);
    		return;
    	}
    	
    	for (int i = 0; i < dx.length; i++) {
    		path.add(new Step(curX, curY));
    		uniquePath2Helper(curX + dx[i], curY + dy[i], endX, endY, res, path, dx, dy);
    		path.remove(path.size() - 1);
    	}
    	
    } 
    
    class BrowserCache {
    	class Node {
    		String url;
    		Node next;
    		Node prev;
    		public Node(String s) {
    			this.url = s;
    			this.next = null;
    			this.prev = null;
    		}
    	}
    	Node head = new Node("");
    	Node tail = new Node("");
    	int capacity;
    	Map<String, Node> cache = null;
    	public BrowserCache(int capacity) {
    		this.capacity = capacity;
    		this.cache = new HashMap<String, Node>();
    		head.next = tail;
    		tail.prev = head;
    	}
    	
    	public void set(String url) {
    		if (cache.size() == this.capacity) {
    			cache.remove(head.next.url);
    			head.next = head.next.next;
    			head.next.prev = head;
    		}
    		Node insert = new Node(url);
    		cache.put(url, insert);
    		move_to_tail(insert);
    	}
    	
    	private void move_to_tail(Node insert) {
    		insert.next = tail;
    		insert.prev = tail.prev;
    		tail.prev.next = insert;
    		tail.prev = insert;
    	}
    }
    
    public class LRUCache {
        private class Node
        {
            int key;
            int value;
            Node prev;
            Node next;
            public Node(int key, int value)
            {
                this.key = key;
                this.value = value;
                prev = null;
                next = null;
            }
        }
        
        private int capacity;
        private HashMap<Integer, Node> cache = new HashMap<Integer, Node>();
        private Node head = new Node(-1, -1);
        private Node tail = new Node(-1, -1);
        public void move_to_tail(Node current)
        {
            current.prev = tail.prev;
            tail.prev = current;
            current.prev.next = current;
            current.next = tail;
        }
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            tail.prev = head;
            head.next = tail;
        }
        
        public int get(int key) {
            if (!cache.containsKey(key)) {return -1;}
            Node current = cache.get(key);
            current.prev.next = current.next;
            current.next.prev = current.prev;
            move_to_tail(current);
            return cache.get(key).value;
        }
        
        public void set(int key, int value) {
            if (cache.containsKey(key)) 
            {
            	Node current = cache.get(key);
            	current.prev.next = current.next;
            	current.next.prev = current.prev;
                cache.get(key).value = value;
                return;
            }
            
            if (cache.size() == this.capacity)
            {
                cache.remove(head.next.key);
                head.next = head.next.next;
                head.next.prev = head;
                
            }
            Node insert = new Node(key, value);
            cache.put(key, insert);
            move_to_tail(insert);
        }
    }
    
    public void moveZero(char[] nums) {
    	if (nums == null || nums.length == 0) {
    		return;
    	}
    	int slow = 0;
    	int fast = 0;
    	while (fast < nums.length) {
    		if (nums[slow] != ' ') {
    			slow++;
    			fast++;
    		} else {
    			while (fast < nums.length) {
    				if (nums[fast] != ' ') {
    					break;
    				}
    				fast++;
    			}
    			if (fast < nums.length) {
    				swap(nums, fast, slow);
    			}
    		}
    	}
    }
    
    private void swap(char[] nums, int i, int j) {}
    
    
    /**
     * "abbccc" => "a1b2c3"
     * @param s
     * @return
     */
    public String compressString(String s) {
    	if (s == null || s.length() == 0) {
    		return s;
    	}
    	StringBuilder sb = new StringBuilder();
    	int i = 0; 
    	int j = 0;
    	while (i < s.length()) {
    		int count = 0;
    		while (j < s.length() && s.charAt(i) == s.charAt(j)) {
    			count++;
    			j++;
    		}
    		sb.append(s.charAt(i));
    		sb.append(Integer.toString(count));
    		i = j;
    	}
    	return sb.toString();
    }
    
    /**
     * 在先递增后递减的array中找target
     * @param array
     * @return
     */
    public int findElementInPeekArray(int[] array, int target) {
    	if (array == null || array.length == 0) {
    		return -1;
    	}
    	int mid = findPeekElement(array);
    	int left = binarySearch(array, 0, mid, target, 1);
    	int right = binarySearch(array, mid + 1, array.length - 1, target, -1);
    	return (left == -1) ? right : left;
    }
    
    private int binarySearch(int[] array, int start, int end, int target, int factor) {
    	while (start < end - 1) {
    		int mid = start + (end - start) / 2;
    		if (target == array[mid]) {
    			return mid;
    		} else if (target * factor < array[mid] * factor) {
    			end = mid;
    		} else {
    			start = mid;
    		}
    	}
    	if (array[start] == target) {
    		return start;
    	} else if (array[end] == target) {
    		return end;
    	} else {
    		return -1;
    	}
    }
    
    private int findPeekElement(int[] array) {
    	int start = 0;
    	int end = array.length - 1;
    	while (start < end - 1) {
    		int mid = start + (end - start) / 2;
    		if (array[mid] > array[mid + 1] && array[mid] > array[mid - 1]) {
    			return mid;
    		} else if (array[mid] < array[mid + 1]) {
    			start = mid;
    		} else if (array[mid] < array[mid - 1]) {
    			end = mid;
    		}
    	}
    	return (array[start] > array[end]) ? start : end;
    }
    
    
    public int[] maxProfit(int[] prices) {
    	if (prices == null || prices.length == 0) {
    		return null;
    	}
    	int[] res = new int[2];
    	int minPrice = Integer.MAX_VALUE;
    	int maxProfit = Integer.MIN_VALUE;
    	for (int i = 0; i < prices.length; i++) {
    		if (prices[i] < minPrice) {
    			minPrice = prices[i];
    		}
    		if (maxProfit < prices[i] - minPrice) {
    			maxProfit = prices[i] - minPrice;
    			res[1] = prices[i];
    			res[0] = minPrice;
    		}
    	}
    	return res;
    }
    
    public int[] findMostLeast_And_SecondMostLeast(int[] nums) {
    	if (nums == null || nums.length <= 1) {
    		return nums;
    	}
    	int[] res = new int[2];
    	int first = 0;
    	int second = 1;
    	if (nums[0] >= nums[1]) {
    		first = 1;
    		second = 0;
    	}
    	
    	for (int i = 2; i < nums.length; i++) {
    		if (nums[i] < nums[first]) {
    			second = first;
    			first = i;
    		} else if (nums[i] < nums[second]) {
    			second = i;
    		}
    	}
    	res[0] = first;
    	res[1] = second;
    	return res;
    } 
    
    
    public int findKthElement(int[] nums, int k) {
    	if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
    		return 0;
    	}
    	return findKthElementHelper(nums, 0, nums.length - 1, k - 1);
    }
    
    private int findKthElementHelper(int[] nums, int start, int end, int k) {
    	
    	int pivot = end;
    	int left = start;
    	int right = end - 1;
    	while (left <= right) {
    		while (left <= right && nums[left] <= nums[pivot]) {
    			left++;
    		}
    		while (right >= left && nums[right] >= nums[pivot]) {
    			right--;
    		}
    		if (left < right) {
    			swap(nums, left, right);
    		}
    	}
    	swap(nums, left, pivot);
    	if (left == k) {
    		return nums[left];
    	} else if (left < k) {
    		return findKthElementHelper(nums, left + 1, end, k);
    	} else {
    		return findKthElementHelper(nums, start, left - 1, k);
    	}
    }
    
    private void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
    
    public String longestPalindromSubstring(String s) {
    	String LPSubstring = "";
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
    	boolean[][] isPalindrome = getPalindrome(s);
    	for (int i = 0; i < s.length(); i++) {
    		for (int j = i; j < s.length(); j++) {
    			if (isPalindrome[i][j] && j - i + 1 > LPSubstring.length()) {
    				LPSubstring = s.substring(i, j + 1);
    			}
    		}
    	}
    	return LPSubstring;
    }
    
    private boolean[][] getPalindrome(String s) {
    	boolean[][] isPalindrome = new boolean[s.length()][s.length()];
    	for (int i = 0; i < s.length(); i++) {
    		isPalindrome[i][i] = true;
    	}
    	for (int i = 0; i < s.length() - 1; i++) {
    		isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
    	}
    	
    	for (int length = 2; length < s.length(); length++) {
    		for (int start = 0; start + length < s.length(); start++) {
    			isPalindrome[start][start + length] = (isPalindrome[start + 1][start + length - 1] 
    					&& s.charAt(start) == s.charAt(start + length));
    		}
    	}
    	return isPalindrome;
    } 
    
    static class Direction {
    	public static final int[] dx = {1, 0, 0, -1};
    	public static final int[] dy = {0, 1, -1, 0};
    	public static final int DOWN = 0;
    	public static final int RIGHT = 1;
    	public static final int LEFT = 2;
    	public static final int UP = 3;
    	
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
    	
    	public static int[] move(int[] cursor, int direction) {
    		int[] nextCursor = new int[2];
    		nextCursor[0] = cursor[0] + dx[direction];
    		nextCursor[1] = cursor[1] + dy[direction];
    		return nextCursor;
    	}
    }
    
    public List<Integer> spiral(int[][] matrix) {
    	List<Integer> list = new ArrayList<Integer>();
    	if (matrix == null || matrix.length == 0) {
    		return list;
    	}
    	int m = matrix.length;
    	if (matrix[0] == null || matrix[0].length == 0) {
    		return list;
    	}
    	int n = matrix[0].length;
    	int direction = Direction.RIGHT;
    	int[] cursor = {0, 0};
    	for (int i = 0; i < n * m; i++) {
    		list.add(matrix[cursor[0]][cursor[1]]);
    		matrix[cursor[0]][cursor[1]] = -1;
    		int[] nextCursor = Direction.move(cursor, direction);
    		if (nextCursor[0] < 0 || nextCursor[0] >= m || nextCursor[1] < 0 || nextCursor[1] >= n || matrix[nextCursor[0]][nextCursor[1]] == -1) {
    			direction = Direction.turnRight(direction);
    			nextCursor = Direction.move(cursor, direction);
    		}
    		cursor = nextCursor;
    	}
    	return list;
    }
    
    public boolean isSorted(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return true;
    	}
    	
    	int counter = 0;
    	int dup = 0;
    	int n = nums.length;
    	for (int i = 0; i < n - 1; i++) {
    		if (nums[i] == nums[i + 1]) {
    			dup++;
    		} else if (nums[i] < nums[i + 1]) {
    			counter++;
    		} else {
    			counter--;
    		}
    	}
    	
    	return (Math.abs(counter) == n - 1 - dup);
    }
}

class Step {
	int x;
	int y;
	public Step(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void print() {
		System.out.print("(");
		System.out.print(this.x);
		System.out.print(" ");
		System.out.print(this.y);
		System.out.print(")    ");
	}
}

class ResultType {
	int carry;
	ListNode node;
	public ResultType(int carry, ListNode node) {
		this.carry = carry;
		this.node = node;
	}
}

class ListNode {
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}

class RListNode {
	int val;
	RListNode next;
	RListNode random;
	public RListNode(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}

class QpTreeNode {
	int val;
	QpTreeNode up;
	QpTreeNode right;
	QpTreeNode down;
	public QpTreeNode(int val) {
		this.val = val;
		this.up = null;
		this.right = null;
		this.down = null;
	}
}

class ListNode_2D {
	int val;
	ListNode_2D next;
	ListNode_2D down;
	public ListNode_2D(int val) {
		this.val = val;
	}
}

class DtTreeNode {
	int val;
	DtTreeNode father;
	public DtTreeNode(int val) {
		this.val = val;
		this.father = null;
	}
}

class ReadWriteLock {
	private int readers = 0;
	private int writers = 0;
	private int writerRequests = 0;
	
	public synchronized void lockRead() throws InterruptedException {
		while (writers > 0 || writerRequests > 0) {
			wait();
		}
		readers++;
	}
	
	public synchronized void unlockRead() throws InterruptedException {
		readers--;
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		writerRequests++;
		while (readers > 0 || writers > 0) {
			wait();
		}
		writerRequests--;
		writers++;
	}
	
	public synchronized void unlockWrite() throws InterruptedException {
		writers--;
		notifyAll();
	}
}
