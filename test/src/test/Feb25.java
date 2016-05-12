package test;

public class Feb25 {
	public int findFirstCommon(int[] A, int[] B, int[] C) {
		if (A == null || A.length == 0 || B == null || B.length == 0 || C == null || C.length == 0) {
			return 0;
		}
		
		int indexA = 0;
		int indexB = 0;
		int indexC = 0;
		while (indexA < A.length && indexB < B.length && indexC < C.length) {
			
			if (A[indexA] < B[indexB]) {
				indexA++;
			} else if (A[indexA] == B[indexB]) {
				if (A[indexA] < C[indexC]) {
					indexA++;
					indexB++;
				} else if (A[indexA] == C[indexC]) {
					return A[indexA];
				} else {
					indexC++;
				}
			} else {
				indexB++;
			}
		}
		return 0;
	} 
}
