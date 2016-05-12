package test.test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import test.Solution;

public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testFindGCD() {
		int[] num1 = {1, 2, 3, 4, 5};
		int[] num2 = {2, 4, 6, 8, 10};
		int[] num3 = {4, 8, 10, 20};
		assertEquals(1, s.findGCD(num1)); 
		assertEquals(2, s.findGCD(num2));
		assertEquals(2, s.findGCD(num3));
		
		
	}

	@Test
	private void testFindLCM() {
		int[] num1 = {1, 2, 3, 4, 5};
		int[] num2 = {2, 4, 6, 8, 10};
		int[] num3 = {4, 8, 10, 20};
		assertEquals(60, s.findLCM(num1)); 
		assertEquals(120, s.findLCM(num2));
		assertEquals(40, s.findLCM(num3));
	}

}
