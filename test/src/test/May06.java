package test;

public class May06 {
	public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int minLen = nums.length + 1;
        int sum = 0;
        while (start < nums.length || end < nums.length) {
            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start];
                start++;
            } else {
                if (end < nums.length) {
                    sum += nums[end];
                    end++;
                } else {
                    break;
                }
            }
        }
        return (minLen == nums.length + 1) ? 0 : minLen;
    }
}
