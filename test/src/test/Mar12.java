package test;

import java.text.DecimalFormat;
import java.util.*;

public class Mar12 {
	
	Set<Character> operators;
	
	public Mar12() {
		operators = new HashSet<Character>();
		operators.add('-');
		operators.add('+');
		operators.add('*');
		operators.add('/');
	}
	public String toJadenCase(String phrase) {
		// TODO put your code below this comment
		if (phrase == null || phrase.length() == 0) {
      return phrase;
    }
    String[] sArr = phrase.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String s : sArr) {
      sb.append(Character.toUpperCase(s.charAt(0)));
      sb.append(s.substring(1) + ' ');
    }
		return sb.toString().trim();
	}
	
	
	
	private List<List<String>> generateExpressions(double[] nums) {
		List<List<String>> res = new ArrayList<List<String>>();
		if (nums == null || nums.length <= 3) {
			return res;
		} 
		List<String> list = new ArrayList<String>();
		generateHelper(nums, list, 0, res, 0, 0);
		return res;
	}
	
	private void generateHelper(double[] nums, List<String> list, 
			int pos, List<List<String>> res, int countNum, int countOperator) {
		if (countNum == 4 && countOperator == 3) {
			res.add(new ArrayList<String>(list));
			return;
		}
		
		list.add(Double.toString(nums[pos]));
		if (pos < nums.length - 1) {
			for (Character c : this.operators) {
				list.add(c.toString());
				generateHelper(nums, list, pos + 1, res, countNum + 1, countOperator + 1);
				list.remove(list.size() - 1);
			}
		} else {
			generateHelper(nums, list, pos + 1, res, countNum + 1, countOperator);
		}
		list.remove(list.size() - 1);
	}
	
	private Map<String, Map<Double, String>> findResult(List<List<String>> input) {
		Map<String, Map<Double, String>> output = new HashMap<String, Map<Double, String>>();
		if (input == null || input.size() == 0 || input.get(0).size() == 0) {
			return output;
		}
		for (List<String> list : input) {
			StringBuilder sb = new StringBuilder();
			for (String s : list) {
				sb.append(s);
			}
			output.put(sb.toString(), findResultForSingleExpressionHelper(sb.toString()));
		}
		return output;
	}
	
	
	
	private Map<Double, String> findResultForSingleExpressionHelper(String expression) {
		Map<Double, String> map = new HashMap<Double, String>();
		
		for (int i = 0; i < expression.length(); i++) {
			if (operators.contains(expression.charAt(i))) {
				Map<Double, String> left = findResultForSingleExpressionHelper(expression.substring(0, i));
				Map<Double, String> right = findResultForSingleExpressionHelper(expression.substring(i + 1));
				for (Double j : left.keySet()) {
					for (Double k : right.keySet()) {
						StringBuilder sb = new StringBuilder();
						double key = 0;
						sb.append("(");
						sb.append(left.get(j));
						sb.append(expression.charAt(i));
						sb.append(right.get(k));
						sb.append(")");
						if (expression.charAt(i) == '+') {
							key = j.doubleValue() + k.doubleValue();
						} else if (expression.charAt(i) == '-') {
							key = j.doubleValue() - k.doubleValue();
						} else if (expression.charAt(i) == '*') {
							key = j.doubleValue() * k.doubleValue();
						} else {
							key = j.doubleValue() / k.doubleValue();
						}
						
						map.put(key, sb.toString());
					}
				}
			}
		}
		if (map.size() == 0) {
			map.put(Double.parseDouble(expression), expression);
		}
		return map;
	}
	
	public String isValid(double[] nums, double target) {
		List<List<String>> expressions = generateExpressions(nums);
		Map<String, Map<Double, String>> results = findResult(expressions);
		
		for (String s : results.keySet()) {
			System.out.println(results.get(s).keySet());
			System.out.println(results.get(s).values());
			if (results.get(s).keySet().contains(target)) {
				return results.get(s).get(target);
			}
		}
		return "not found";
	}
	
	public double myRound(double d) {
		return Math.round(d * 100) / 100;
	}
}