package com.bb.interview;

import java.util.*;

public class PhoneInterview {
	void printStars(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				System.out.print(' ');
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print('*');
				
			}
			for (int j = 0; j < n - 1 - i; j++) {
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
	
	int[] addTwoArrays(int[] A, int[] B) {
		List<Integer> resList = new ArrayList<Integer>();
		int indexA = A.length - 1;
		int indexB = B.length - 1;
		int carry = 0;
		while (indexA >= 0 && indexB >= 0) {
			int tempRes = A[indexA] + B[indexB] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexA--;
			indexB--;
		}
		
		while (indexA >= 0) {
			int tempRes = A[indexA] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexA--;
		}
		
		while (indexB >= 0) {
			int tempRes = B[indexB] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexB--;
		}
		
		if (carry != 0) {
			resList.add(carry);
		}
		
		int[] res = new int[resList.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = resList.get(resList.size() - i - 1);
		}
		return res;
	}
	
	int[] multiplyTwoArrays(int[] A, int[] B) {
		List<Integer> resList = new ArrayList<Integer>();
		int indexA = A.length - 1;
		int indexB = B.length - 1;
		int carry = 0;
		while (indexA >= 0 && indexB >= 0) {
			int tempRes = A[indexA] + B[indexB] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexA--;
			indexB--;
		}
		
		while (indexA >= 0) {
			int tempRes = A[indexA] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexA--;
		}
		
		while (indexB >= 0) {
			int tempRes = B[indexB] + carry;
			int digit = tempRes % 10;
			carry = tempRes / 10;
			resList.add(digit);
			indexB--;
		}
		
		if (carry != 0) {
			resList.add(carry);
		}
		
		int[] res = new int[resList.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = resList.get(resList.size() - i - 1);
		}
		return res;
	}
	
	int findDepth(TreeNode root, TreeNode target) {
		if (target == null || root == null) {
			return -1;
		}
		if (root.val == target.val) {
			return 0;
		}
		if (findDepth(root.left, target) == -1 && findDepth(root.right, target) == -1) {
			return -1;
		} else if (findDepth(root.left, target) == -1) {
			return findDepth(root.right, target) + 1;
		} else {
			return findDepth(root.left, target) + 1;
		}
	}
	
	boolean checkLuckyNumber(int n) {
		List<Integer> nums = new ArrayList<Integer>();
		if (n == 1) {
			return true;
		}
		if (n % 2 == 0 || n < 0) {
			return false;
		}
		for (int i = 1; i <= n; i += 2) {
			nums.add(i);
		}
		
		int survivingIndex = 1;
		while (survivingIndex < nums.size()) {
			int base = nums.get(survivingIndex);
			int offset = 0;
			for (int i = 1; base * i - 1 - offset < nums.size(); i++) {
				
				if (nums.get(base * i - 1 - offset) == n) {
					return false;
				}
				nums.remove(base * i - 1 - offset);
				offset++;
			}
			survivingIndex++; 
		}
		return true;
	}
    
	public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode parent = null;
        TreeNode newRoot = null;
        while (cur.left != null) {
            stack.push(cur);
            cur = cur.left;
        }
        newRoot = cur;
        while (!stack.isEmpty()) {
            parent = stack.pop();
            cur.right = parent;
            cur.left = parent.right;
            cur = parent;
        }
        return newRoot;
    }
    
	
	public int divide(int dividend, int divisor) {
		if (divisor == 0) {
            return (dividend >= 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        boolean isNegative = (((dividend ^ divisor) >>> 31) == 1) ? true : false;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int digit = 0;
        while (b <= a) {
            b <<= 1;
            digit++;
            System.out.println(b);
            System.out.println(digit);
        }
        while (digit >= 0) {
            if (a >= b) {
                a -= b;
                res += (1 << digit);
            }
            b >>= 1;
            digit--;
        }
        return isNegative? -res : res;
    }
	
	public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length; i++) {
            while (i < s.length && Character.isLetter(s[i])) {
                i++;
            }
            end = i - 1;
            reverseWord(s, start, end);
            while (i < s.length && !Character.isLetter(s[i])) {
                i++;
            }
            start = i;
        }
        end = s.length - 1;
        while (!Character.isLetter(s[end])) {
        	end--;
        }
        reverseWord(s, 0, end);
        for (int i = 0; i < s.length; i++) {
        	System.out.println(s[i]);
        }
    }
    
    private void reverseWord(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    } 
    
    public int maxProfit(int[] prices) {
    	if (prices == null || prices.length == 0) {
    		return 0;
    	}
    	int price = 0;
    	int tempPrice = Integer.MAX_VALUE;
    	int maxProfit = Integer.MIN_VALUE;
    	for (int i = 0; i < prices.length; i++) {
    		if (prices[i] < tempPrice) {
    			tempPrice = prices[i];
    		}
    		if (maxProfit < prices[i] - tempPrice) {
    			price = tempPrice;
    			maxProfit = prices[i] - tempPrice;
    		}
    	}
    	return price;
    }
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        long origin = (long)x;
        return x == reverse(x);    
    }
    
    public long reverse(int x) {
        long rst = 0;
        while(x != 0) {
            rst = rst * 10 + x % 10;
            x = x / 10;
        }
        return rst;
    }
    
    public int numberOfIsland(int[][] grid) {
    	if (grid == null || grid.length == 0) {
    		return 0;
    	}
    	int num = 0;
    	int[] dx = {0, 1, 0, -1};
    	int[] dy = {1, 0, -1, 0};
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 0) {
    				continue;
    			} else {
    				findIsland(grid, i, j, dx, dy);
    				num++;
    			}
    		}
    	}
    	return num;
    }
    
    private void findIsland(int[][] grid, int posX, int posY, int[] dx, int[] dy) {
    	if (grid[posX][posY] == 0) {
    		return;
    	}
    	grid[posX][posY] = 0;
    	for (int i = 0; i < 4; i++) {
    		int nextPosX = posX + dx[i];
    		int nextPosY = posY + dy[i];
    		if (nextPosX >= 0 && nextPosY >= 0 && nextPosX < grid.length && nextPosY < grid[nextPosX].length && grid[nextPosX][nextPosY] == 1) {
    			findIsland(grid, nextPosX, nextPosY, dx, dy);
    			grid[nextPosX][nextPosY] = 0;
    		}
    	}
    }
    
    public int longestConsecutiveSequence(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	if (nums.length == 1) {
    		return 1;
    	}
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i = 0; i < nums.length; i++) {
    		map.put(nums[i], 0);
    	}
    	int maxLen = 1;
    	for (int index = 0; index < nums.length; index++) {
    		if (map.get(nums[index]) == 1) {
    			continue;
    		}
    		int currentLength = 1;
    		int increment = nums[index] + 1;
    		int decrement = nums[index] - 1;
    		while (map.containsKey(increment)) {
    			map.put(increment, 1);
    			increment++;
    			currentLength++;
    		}
    		while (map.containsKey(decrement)) {
    			map.put(decrement, 1);
    			decrement--;
    			currentLength++;
    		}
    		maxLen = Math.max(maxLen, currentLength);
    	}
    	return maxLen;
    }
    
    private int stupidlongest(int[] nums) {
    	Arrays.sort(nums);
    	int length = 1;
    	int maxLen = 0;
    	for (int i = 0; i < nums.length - 1; i++) {
    		if (nums[i] == nums[i + 1] - 1) {
    			length++;
    		} else {
    			length = 1;
    		}
    		maxLen = Math.max(length, maxLen);
    	}
    	return maxLen;
    }
    
    public void printMatrixSpiral(int[][] matrix) {
    	if (matrix == null || matrix.length == 0) {
    		return;
    	}
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int count = 0;
    	int startX = 0;
    	int startY = 0;
    	while (count < m * n) {
    		int horizontalLimit = n;
    		int verticalLimit = m;
    		System.out.println(matrix[startX][startY]);
    		count++;
    		for (int i = 0; i < 4; i++) {
    			if (i == 0) {
    				for (int j = startY + 1; j < horizontalLimit; j++) {
    					System.out.println(matrix[startX][j]);
    					count++;
    					if (count == m * n) {
    						break;
    					}
    				}
    			} else if (i == 1) {
    				for (int j = startX + 1; j < verticalLimit; j++) {
    					System.out.println(matrix[j][horizontalLimit - 1]);
    					count++;
    					if (count == m * n) {
    						break;
    					}
    				}
    			} else if (i == 2) {
    				for (int j = horizontalLimit - 2; j >= 0; j--) {
    					System.out.println(matrix[verticalLimit - 1][j]);
    					count++;
    					if (count == m * n) {
    						break;
    					}
    				}
    			} else {
    				for (int j = verticalLimit - 2; j > 0; j--) {
    					System.out.println(matrix[j][startY]);
    					count++;
    					if (count == m * n) {
    						break;
    					}
    				}
    			}
    		}
    		verticalLimit--;
    		horizontalLimit--;
    		startX++;
    		startY++;
    	}
    }
}



class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}
