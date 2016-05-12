package com.godaddy.oa;

import java.util.*;

public class OA {
	int[] mergeTwoArraysNotInPlace(int[] A, int[] B) {
		int[] res = new int[A.length + B.length];
		Arrays.sort(A);
		Arrays.sort(B);
		int indexA = 0;
		int indexB = 0;
		while (indexA < A.length && indexB < B.length) {
			if (A[indexA] < B[indexB]) {
				res[indexA + indexB] = A[indexA];
				indexA++;
			} else {
				res[indexA + indexB] = B[indexB];
				indexB++;
			}
		}
		while (indexA < A.length) {
			res[indexA + indexB] = A[indexA];
			indexA++;
		}
		while (indexB < B.length) {
			res[indexA + indexB] = B[indexB];
			indexB++;
		}
		return res;
	}
	
	List<Integer> findCommonElement(int[] A, int[] B) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer i : A) {
			if (!map.containsKey(i)) {
				map.put(i, 1);
			}
		}
		for (Integer i : B) {
			if (map.containsKey(i)) {
				res.add(i);
			}
		}
		return res;
	}
	
	List<Integer> findCommonElementsWithMatch(int[] A, int[] B) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (Integer i : A) {
			if (!map.containsKey(i)) {
				map.put(i, 1);
			} else {
				map.put(i, map.get(i) + 1);
			}
		}
		Map<Integer, Integer> copyMap = new HashMap<Integer, Integer>(map);
		
		for (Integer i : B) {
			if (map.containsKey(i)) {
				if (map.get(i) != 0) {
					map.put(i, map.get(i) - 1);
				}
			}
		}
		for (Integer i : map.keySet()) {
			int countAfter = map.get(i);
			int countBefore = copyMap.get(i);
			for (int j = 0; j < countBefore - countAfter; j++) {
				res.add(i);
			}
		}
		return res;
	}
	
	int[] zigzagArrayNotInPlace(int[] A) {
		Arrays.sort(A);
		int[] res = new int[A.length];
		int flag = 1;
		int head = 0;
		int tail = A.length - 1;
		int i = 0;
		while (head <= tail) {
			if (flag == 1) {
				res[i] = A[tail--];
				flag = 0;
			} else {
				res[i] = A[head++];
				flag = 1;
			}
			i++;
		}
		return res;
	}
	
	String compressString(String s) {
		StringBuilder sb = new StringBuilder();
		if (s == null || s.length() == 0) {
			return s;
		}
		char[] sCharArr = s.toCharArray();
		Arrays.sort(sCharArr);
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character c : sCharArr) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		
		for (Character c : map.keySet()) {
			if (map.get(c) != 1) {
				sb.append(c);
				sb.append(map.get(c).toString());
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	String closestNum(String s, int len) {
		String[] sNums = s.split(" ");
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			nums.add(Integer.parseInt(sNums[i]));
		}
		Collections.sort(nums);
		int min = Integer.MAX_VALUE;
		List<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < len - 1; i++) {
			min = Math.min(min, nums.get(i + 1) - nums.get(i));
		}
		
		for (int i = 0; i < len - 1; i++) {
			if (min == nums.get(i + 1) - nums.get(i)) {
				indices.add(i);
				indices.add(i + 1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Integer i : indices) {
			sb.append(nums.get(i).toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	
    private class ResultType {
        int number_building;
        int distance;
        public ResultType(int number, int dis) {
            number_building = number;
            distance = dis;
        }
    }
    
    private class Step {
        int Xpos;
        int Ypos;
        public Step(int x, int y) {
            Xpos = x;
            Ypos = y;
        }
    }
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int count = countBuilding(grid);
        int minDistance = Integer.MAX_VALUE;
        int maxNumberOfBuilding = 0;
        ResultType rt = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    rt = helper(grid, i, j);
                    maxNumberOfBuilding = Math.max(maxNumberOfBuilding, rt.number_building);
                    minDistance = Math.min(minDistance, rt.distance);
                }
            }
        }
        System.out.println(maxNumberOfBuilding);
        return (rt != null && maxNumberOfBuilding == count) ? minDistance : -1;
    }
    
    private ResultType helper(int[][] grid, int Xpos, int Ypos) {
    	int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Step> queue = new LinkedList<Step>();
        Step startpoint = new Step(Xpos, Ypos);
        int number_building = 0;
        int distance = 0;
        queue.offer(startpoint);
        int pathLength = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Step cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    Step next = new Step(cur.Xpos + dx[j], cur.Ypos + dy[j]);
                    if (isValidPos(next, grid, visited)) {
                        if (grid[next.Xpos][next.Ypos] == 1) {
                            distance += pathLength;
                            number_building++;
                            visited[next.Xpos][next.Ypos] = true;
                        } else {
                            queue.offer(next);
                        }
                    }
                }
                visited[cur.Xpos][cur.Ypos] = true;
            }
            pathLength++;
        }
        System.out.println(number_building);
        return new ResultType(number_building, distance);
    }
    
    private boolean isValidPos(Step cur, int[][] grid, boolean[][] visited) {
        if (cur.Xpos < 0 || cur.Ypos < 0 || cur.Xpos >= grid.length || 
        		cur.Ypos >= grid[cur.Xpos].length || grid[cur.Xpos][cur.Ypos] == 2 || visited[cur.Xpos][cur.Ypos]) {
            return false;
        } else {
            return true;
        }
    }
    
    private int countBuilding(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
            	if (grid[i][j] == 1) {
            		count++;
            	}
            }
        }
        return count;
    }
    
    private class Length {
        int mlen;
        public Length(int len) {
            mlen = len;
        }
    }
    
  
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int maxLen = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int len = dfs(matrix, i, j, dx, dy, cache);
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
    
    private int dfs(int[][] matrix, int curX, int curY, int[] dx, int[] dy, int[][] cache) {
        if (cache[curX][curY] != 0) {
            return cache[curX][curY];
        }
        int maxLen = 1;
        for (int i = 0; i < dx.length; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            
            if (nextX < 0 || nextY < 0 || nextX >= matrix.length || nextY >= matrix[nextX].length || matrix[nextX][nextY] <= matrix[curX][curY]) {
                continue;
            }
            int len = 1 + dfs(matrix, nextX, nextY, dx, dy, cache);
            maxLen = Math.max(maxLen, len);
        }
        cache[curX][curY] = maxLen;
        return maxLen;
    }
    
    public int singleNumber(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	Set<Integer> s = new HashSet<Integer>();
    	for (int i : nums) {
    		if (!s.add(i)) {
    			System.out.println(i);
    			System.out.println(s.add(i));
    			s.remove(i);
    		}
    		System.out.println("size" + Integer.toString(s.size()));
    	}
    	return s.iterator().next();
    }
}
