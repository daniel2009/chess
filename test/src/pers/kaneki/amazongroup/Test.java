package pers.kaneki.amazongroup;

import java.util.*;

import test.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		/*
		String path = "/Users/kaneki/Desktop/950.dat";
		List<List<Integer>> contentsInput = readDataFromFile(path);
		Map<Integer, Map<Integer, Set<Integer>>> input = transferToSchedule(contentsInput);
		
		for (Integer areaId : input.keySet()) {
			System.out.println(areaId);
			for (Integer moment : input.get(areaId).keySet()) {
				System.out.print(moment);
				System.out.print(':');
				for (Integer contentId : input.get(areaId).get(moment)) {
					System.out.print(contentId);
					System.out.print(' ');
				}
				System.out.print('\n');
			}
		}
		System.out.println('\n');
		
		
		final double[] weight = {1.0, 0.8, 0.75};
		final int[] value = {10, 20, 30, 40, 50, 60, 70, 80}; 
		Content newContent = new Content(5, 5);
		
		Map<Integer, Double> areaWeight = transferDoubleArrayToMap(weight);
		Map<Integer, Integer> contentValue = transferArrayToMap(value);
		
		Optimization opt = new Optimization(areaWeight, contentValue);
		List<Integer> res = opt.optimize(input, newContent);
		
		System.out.println(res.get(0));
		System.out.println(res.get(1));
		*/
		
		int[] tickets = {4, 3, 5, 6, 1};
		Solution solver = new Solution();
        
	}
	
	private static List<List<Integer>> readDataFromFile(String path) {
		BufferedReader br = null;
		List<List<Integer>> contentsInput = new ArrayList<List<Integer>>();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strs =sCurrentLine.split(",");
				List<Integer> item = new ArrayList<Integer>();
				for(String str : strs) {
					item.add(Integer.parseInt(str));
				}
				contentsInput.add(item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contentsInput;
	}
	
	private static Map<Integer, Map<Integer, Set<Integer>>> transferToSchedule(List<List<Integer>> contentsInput) {
		Map<Integer, Map<Integer, Set<Integer>>> input = new HashMap<Integer, Map<Integer, Set<Integer>>>();
		for (List<Integer> content : contentsInput) {
			int areaId = content.get(0);
			if (!input.containsKey(areaId)) {
				input.put(areaId, new HashMap<Integer, Set<Integer>>());
			}
			for (int startTime = content.get(2); startTime < content.get(3); startTime++) {
				if (!input.get(areaId).containsKey(startTime)) {
					input.get(areaId).put(startTime, new HashSet<Integer>());
				}
				input.get(areaId).get(startTime).add(content.get(1));
			}
			
			int endTime = Integer.MIN_VALUE;
			int startTime = Integer.MAX_VALUE;
			for (Integer moment : input.get(areaId).keySet()) {
				if (moment > endTime) {
					endTime = moment;
				}
				if (moment < startTime) {
					startTime = moment;
				}
			}
			
			for (int curTime = startTime; curTime < endTime; curTime++) {
				if (input.get(areaId).get(curTime) == null) {
					input.get(areaId).put(curTime, new HashSet<Integer>());
				}
			}
		}
		
		return input;
	}
	
	private static Map<Integer, Integer> transferArrayToMap(int[] array) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			map.put(i + 1, array[i]);
		}
		return map;
	}
	
	private static Map<Integer, Double> transferDoubleArrayToMap(double[] array) {
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for (int i = 0; i < array.length; i++) {
			map.put(i + 1, array[i]);
		}
		return map;
	}
}

