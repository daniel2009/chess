package test;

import java.util.*;

public class Feb26 {
	public boolean consecutiveK(int[] nums, int k) {
		
		if (nums == null || nums.length % k != 0) {
			return false;
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		
		while (!map.keySet().isEmpty()) {
			int minKey = findMinKey(map);
			for (int cur = minKey; cur < minKey + k; cur++) {
				if (!map.containsKey(cur)) {
					return false;
				}
				map.put(cur, map.get(cur) - 1);
				if (map.get(cur) == 0) {
					map.remove(cur);
				}
			}
		}
		return true;
	}
	
	private int findMinKey(Map<Integer, Integer> map) {
		int minKey = Integer.MAX_VALUE;
		for (Integer i : map.keySet()) {
			minKey = Math.min(i, minKey);
		}
		return minKey;
	}
}
