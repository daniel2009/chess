package test;

import java.util.*;

public class Mar18 {
	public int[][] generateMultiplicationTable(int n) {
		if (n <= 0) {
			return null;
		}
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++) {
			res[i][0] = i + 1; 
			res[0][i] = i + 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				res[i][j] = res[i][0] + res[i][j - 1];
			}
		}
		return res;
	}
	
	public int countOne(int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}
		return count;
	}
}
