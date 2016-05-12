package test;

import java.util.*;

public class May01 {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        int fast = 0;
        int slow = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLen = Integer.MIN_VALUE;
        int len = s.length();
        while (fast < len) {
            char cur = s.charAt(fast);
            if (map.size() < k) {
                if (map.containsKey(cur)) {
                    map.put(cur, map.get(cur) + 1);
                } else {
                    map.put(cur, 1);
                }
                maxLen = Math.max(maxLen, fast - slow + 1);
                fast++;
            } else {
                if (map.containsKey(cur)) {
                	map.put(cur, map.get(cur) + 1);
                    maxLen = Math.max(maxLen, fast - slow + 1);
                    fast++;
                } else {
                    char last = s.charAt(slow);
                    if (!map.containsKey(last)) {
                    	System.out.println(last);
                    }
                    if (map.get(last) == 1) {
                        map.remove(last);
                    } else {
                        map.put(last, map.get(last) - 1);
                    }
                    slow++;
                }
            }
        }
        return maxLen;
	}
}
