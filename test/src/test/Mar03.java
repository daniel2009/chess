package test;

import java.util.*;

public class Mar03 {
	class Result {
		int tempMove;
		int minMove;
		
		public Result() {
			this.tempMove = 0;
			this.minMove = 0;
		}
	}
	
	// the entry without any car has the -1 carId
	public int findMinMove(int[] cars, int[] result) {
		if (cars == null || cars.length == 0) {
			return 0;
		}
		Set<Integer> unfinishedCar = new HashSet<Integer>();
		Map<Integer, Integer> curMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		
		init(unfinishedCar, curMap, resultMap, cars, result);
		
		Result r = new Result();
		
		helper(unfinishedCar, cars, r, curMap, resultMap);
		return r.minMove;
	}
	
	private void helper(Set<Integer> unfinishedCar, int[] cars, Result r, Map<Integer, Integer> curMap, Map<Integer, Integer> resultMap) {
		for (Integer carId : unfinishedCar) {
			int wantPos = resultMap.get(carId);
			int curPos = curMap.get(carId);
			int wantPosCar = cars[wantPos];
			
			if (wantPosCar == -1) {
				
				swap(curPos, wantPos, cars);
				curMap.put(carId, wantPos);
				r.tempMove += 1;
				unfinishedCar.remove(carId);
				helper(unfinishedCar, cars, r, curMap, resultMap);
				r.tempMove -= 1;
				unfinishedCar.add(carId);
				curMap.put(carId, curPos);
				swap(wantPos, curPos, cars);
				
			} else {
				
				swap(curPos, wantPos, cars);
				curMap.put(carId, wantPos);
				r.tempMove += 2;
				unfinishedCar.remove(carId);
				helper(unfinishedCar, cars, r, curMap, resultMap);
				r.tempMove -= 2;
				unfinishedCar.add(carId);
				curMap.put(carId, curPos);
				swap(wantPos, curPos, cars);
				
			}
		}
		r.minMove = Math.min(r.minMove, r.tempMove);
	}
	
	private void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	
	
	// key is carId, value is pos/Index
	private void init(Set<Integer> unfinishedCar, Map<Integer, Integer> curMap, 
			Map<Integer, Integer> resultMap, int[] cars, int[] result) {
		for (int i = 0; i < cars.length; i++) {
			curMap.put(cars[i], i);
		}
		
		for (int i = 0; i < result.length; i++) {
			resultMap.put(result[i], i);
		}
		
		for (int i = 0; i < cars.length; i++) {
			int carId = cars[i];
			if (resultMap.get(carId).intValue() != curMap.get(carId).intValue()) {
				unfinishedCar.add(carId);
			}
		}
		
	}
	
}
