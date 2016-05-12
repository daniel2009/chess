package test;

import java.util.*;
/*
public class Selection {
	static final int areaNum = 6;
	static final double[] weight = {1.0, 0.8, 0.75, 0.5, 0.3, 0.2};
	static final int[] value = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10}; 
	static final int[] id = {1, 2, 3, 4, 5, 6, 7 ,8, 9, 10};
	static final int adNum = 10;
	private Map<Integer, Integer> valueMap;
	private Map<Integer, Boolean> isSelected;
	
	public Selection() {
		isSelected = new HashMap<Integer, Boolean>();
		valueMap = new HashMap<Integer, Integer>();
		for (int i = 1; i <= adNum; i++) {
			valueMap.put(id[i - 1], value[i - 1]);
		}
		
	}
	
	public List<Content> selecting(int[][][] input, int moment) {
		List<Content> selectedResult = new ArrayList<Content>(); 
		List<List<Content>> candidatesList = findCandidateContents(input, moment);
		
		double mostValue = Integer.MIN_VALUE;
		List<Content> tempResult = new ArrayList<Content>();
		selectingHelper(selectedResult, tempResult, 0.0, mostValue, candidatesList, 0);
		return selectedResult;
	}
	
	public void selectingHelper(List<Content> selectedResult, List<Content> tempResult, double tempValue, double mostValue, List<List<Content>> candidatesList, int numOfSelected) {
		if (numOfSelected == areaNum) {
			if (tempValue > mostValue) {
				mostValue = tempValue;
				selectedResult.clear();
				selectedResult.addAll(tempResult);
			}
			return;
		}
		
		for (Content c : candidatesList.get(numOfSelected)) {
			if (isSelected.get(c.id)) {
				continue;
			} else {
				tempResult.add(c);
				isSelected.put(c.id, true);
				tempValue += valueMap.get(c.id) * weight[numOfSelected];
				selectingHelper(selectedResult, tempResult, tempValue, mostValue, candidatesList, numOfSelected + 1);
				tempValue -= valueMap.get(c.id) * weight[numOfSelected];
				isSelected.put(c.id, false);
				tempResult.remove(tempResult.size() - 1);
			}
		}
	}
	
	public List<List<Content>> findCandidateContents(int[][][] input, int moment) {
		List<List<Content>> candidatesList = new ArrayList<List<Content>>();
		for (int[][] schedule : input) {
			List<Content> candidatesPerArea = new ArrayList<Content>();
			for (int[] content : schedule) {
				int id = content[0];
				int startTime = content[1];
				int endTime = content[2];
				int value = valueMap.get(id);
				if (isMomentInTimeSlot(moment, startTime, endTime)) {
					Content c = new Content(id, startTime, endTime, value);
					candidatesPerArea.add(c);
				}
			}
			candidatesList.add(candidatesPerArea);
		}
		return candidatesList;
	}
	
	public boolean isMomentInTimeSlot(int moment, int startTime, int endTime) {
		if (startTime <= moment && moment < endTime) {
			return true;
		} else {
			return false;
		}
	}
	
}
*/

