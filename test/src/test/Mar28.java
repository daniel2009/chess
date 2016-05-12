package test;

import java.util.*;

public class Mar28 {
	public class TreeNode {
		int value;
		List<TreeNode> nextList;
	}
	
	public class ResultType {
		int max;
	}
	
	public int maxWidthTreeWithoutQueue(TreeNode root) {
		if (root == null) {
			return -1;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		ResultType rt = new ResultType();
		maxWidthTreeHelper(root, 0, map, rt);
		return rt.max;
	}
	
	private void maxWidthTreeHelper(TreeNode node, int depth, 
			Map<Integer, Integer> map, ResultType rt) {
		for (TreeNode next : node.nextList) {
			if (next == null) {
				continue;
			} else {
				if (map.containsKey(depth)) {
					map.put(depth, map.get(depth) + 1);
				} else {
					map.put(depth, 1);
				}
				rt.max = Math.max(rt.max, map.get(depth));
				maxWidthTreeHelper(next, depth + 1, map, rt);
			}
		}
	}
	
}
