package test;

import java.util.*;

public class Apr26 {
	public void test(int[] arr) {
		int n = arr.length;
        List<Integer> result = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE;
        for(int arr_i=0; arr_i < n; arr_i++){
            min = Math.min(min, arr[arr_i]);
        }
        int num_remaining = n;
        while (num_remaining > 0) {
            int newMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (arr[i] == 0) {
                    num_remaining--;
                    continue;
                } 
                arr[i] -= min;
                if (arr[i] != 0) {
                	newMin = Math.min(newMin, arr[i]);
                }
                
            }
            min = newMin;
            if (num_remaining == 0) {
            	break;
            }
            result.add(num_remaining);
            num_remaining = n;
        }
        System.out.println(result.toString());
    }
}
