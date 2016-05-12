package test;

import java.util.Random;

public class Mar02 {
	public int[] produceRandomArray(int[] nums) throws Exception {
		if (nums == null || nums.length == 0) {
			throw new Exception();
		}
		int len = nums.length - 1;
		int[] res = new int[len + 1];
		for (int i = 0; i < nums.length; i++) {
			if (len != 0) {
				Random r = new Random();
				int j = r.nextInt(len);
				res[i] = nums[j];
				swap(nums, j, len);
				len--;
			} else {
				res[i] = nums[0];
			}
			
		}
		return res;
	}
	
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
