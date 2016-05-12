package pers.kaneki.amazongroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Optimization {
	private static final int maximumOfContent = 3;
	private static Map<Integer, Double> areaWeight;
	private static Map<Integer, Integer> contentValue;
	public Optimization(Map<Integer, Double> areaWeight, Map<Integer, Integer> contentValue) {
		Optimization.areaWeight = areaWeight;
		Optimization.contentValue = contentValue;
	}
	
	/**
	 * 
	 * @param input schedules of all area (key: location/areaId, value: schedule, which is presented 
	 * as a hashmap(key: moment, value: a set of candidate contents at this moment))
	 * @param newContent a content to be inserted
	 * @return a list that contains location/areaId and startTime/insertion_Pos
	 */
	public List<Integer> optimize(Map<Integer, Map<Integer, Set<Integer>>> input, 
			Content newContent) {
		
		List<Integer> areaId_And_Moment = new ArrayList<Integer>();
		int insertionLocation = 0;
		int insertionPos = 0;
		double maxWeightDiff = 0.0;

		for (Integer areaId : input.keySet()) {
			Map<Integer, Set<Integer>> schedule = input.get(areaId);
			ResultType tempResult = findMaxWeightDiffPos(areaId, schedule, newContent, maxWeightDiff);
			if (tempResult.maxWeightDiff > maxWeightDiff) {
				maxWeightDiff = tempResult.maxWeightDiff;
				insertionLocation = tempResult.areaId;
				insertionPos = tempResult.moment;
			}
		}
		
		areaId_And_Moment.add(insertionLocation);
		areaId_And_Moment.add(insertionPos);
		return areaId_And_Moment;
	}
	
	/**
	 * 
	 * @param schedule schedule of one area
	 * @param newConent content to be inserted
	 * @param maxWeightDiff maximum sum of weight difference before insertion
	 * @return an object contains insertion position and maximum sum of weight difference after insertion
	 */
	private ResultType findMaxWeightDiffPos(int areaId, Map<Integer, Set<Integer>> schedule, Content newContent, double maxWeightDiff) {
		ResultType res = new ResultType(areaId, 0, maxWeightDiff);
		int scheduleEndTime = findMaxInSet(schedule.keySet());
		int startTime = findMinInSet(schedule.keySet());
		
		for (int currentTime = startTime; currentTime <= scheduleEndTime - newContent.duration + 1; currentTime++) {
			if (isAvailable(schedule, newContent, currentTime)) {
				int endTime = currentTime;
				double curDiff = 0;
				while (endTime - currentTime < newContent.duration) {
					if (!isAvailable(schedule, newContent, endTime)) {
						break;
					}
					int maxValue = findMaxValue(schedule, endTime);
					curDiff += getWeightDiff(maxValue, areaId, newContent.id);
					endTime++;
				}
				if (endTime == currentTime + newContent.duration) {
					if (curDiff > res.maxWeightDiff) {
						res.maxWeightDiff = curDiff;
						res.moment = currentTime;
					}
				} 
			} 
		}
		
		return res;
	}
	
	/**
	 * 
	 * @param maxValue maximum content value before insertion
	 * @param areaId
	 * @param contentId
	 * @return difference of product of weight of this area and maximum content value change
	 */
	private double getWeightDiff(int maxValue, int areaId, int contentId) {
		double weight = areaWeight.get(areaId);
		int curValue = contentValue.get(contentId);
		
		if (curValue > maxValue) {
			return (curValue - maxValue) * weight;
		} else {
			return 0;
		}
	}
	
	
	/**
	 * 
	 * @param schedule: schedule of one area
	 * @param c: content to be inserted
	 * @param moment: current time in this schedule 
	 * @return a boolean which equals to true if at this moment, this content can be inserted validly, otherwise return false
	 */
	private boolean isAvailable(Map<Integer, Set<Integer>> schedule, Content c, int moment) {
		Set<Integer> scheduledContentsSet = schedule.get(moment);
		
		if (scheduledContentsSet.size() < maximumOfContent && !scheduledContentsSet.contains(c.id)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param schedule: schedule of one area
	 * @param moment: current time in this schedule
	 * @return
	 */
	private int findMaxValue(Map<Integer, Set<Integer>> schedule, int moment) {
		Set<Integer> scheduledContentsIdSet = schedule.get(moment);
		int size = scheduledContentsIdSet.size();

		if (size == 0) {
			return 0;
		} else {
			int maxValue = Integer.MIN_VALUE;
			for (Integer id : scheduledContentsIdSet) {
				int cValue = contentValue.get(id);
				if (cValue >= maxValue) {
					maxValue = cValue;
				}
			}
			return maxValue;
		}
	}
	
	private int findMinInSet(Set<Integer> set) {
		int min = Integer.MAX_VALUE;
		for (Integer i : set) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}
	
	private int findMaxInSet(Set<Integer> set) {
		int max = Integer.MIN_VALUE;
		for (Integer i : set) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}
	
}

class ResultType {
	int areaId;
	int moment;
	double maxWeightDiff;
	public ResultType(int areaId, int moment, double maxWeightDiff) {
		this.areaId = areaId;
		this.moment = moment;
		this.maxWeightDiff = maxWeightDiff;
	}
}


