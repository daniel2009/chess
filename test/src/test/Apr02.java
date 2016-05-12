package test;

import java.util.*;

public class Apr02 {
	public int findLastValidIndex(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}
		Map<Character, Character> map = new HashMap<Character, Character>();
		for (char c = 'A'; c <= 'Z'; c++) {
			map.put(c, (char) (c - 'A' + 'a'));
		}
		Stack<Character> stack = new Stack<Character>();
		int index = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (stack.isEmpty()) {
				if (isLower(c)) {
					return index;
				} else if (isUpper(c)) {
					stack.push(c); 
				} else {
					continue;
				}
			} else {
				if (isUpper(c)) {
					stack.push(c);
				} else if (isLower(c)) {
					if (map.get(stack.peek()) == c) {
						index = i;
						stack.pop();
					} else {
						return index;
					}
				} else {
					continue;
				}
			}
		}
		return s.length();
	}
	
	private boolean isUpper(char c) {
		return c >= 'A' && c <= 'Z';
	}
	
	private boolean isLower(char c) {
		return c >= 'a' && c <= 'z';
	}
}
