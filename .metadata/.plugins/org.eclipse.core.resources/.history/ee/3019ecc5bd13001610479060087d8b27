package test;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import java.lang.*;
import java.lang.reflect.Field;
public class Test {
    public static final String FAILURE = "failure";
    
    private final Character m = 'a';
    public String toString() {
    	return "" + m;
    }
    
    public static void main(String[] args) {
        May06 m = new May06();
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(m.minSubArrayLen(15, nums));
    }
    	
    	/* Enter your code here. Read input from STDIN. Print output to STDOUT */
        /*
        BufferedReader br = null;
        InputStreamReader isr = null;
        List<Integer> input = new ArrayList<Integer>();
        
        try {
        	
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String line = "";
            while (((line = br.readLine()) != null) && (line.length() != 0)) {
                try {
                    input.add(Integer.parseInt(line));
                } catch (NumberFormatException nfe) {
                    System.out.println("nfe");
                }
            }
         
        	input.add(5);
        	input.add(6);
        	input.add(0);
        	input.add(4);
        	input.add(2);
        	input.add(4);
        	input.add(1);
        	input.add(0);
        	input.add(0);
        	input.add(0);
            List<Integer> dungeoneering = findPath(input);
           
            String output = outputProcess(dungeoneering);
            if (output == null || output.length() == 0) {
                System.out.println(FAILURE);
            } else {
                System.out.println(output);
            }
            
        } catch (Exception e) {
            System.out.println(FAILURE);
        } finally {
            close(br, isr);
        }
        
        
    }
    
    public static List<Integer> findPath(List<Integer> input) {
    	if (input == null) {
    		return null;
    	}
        List<Integer> result = new ArrayList<Integer>();
        if (input.size() == 1) {
        	result.add(0);
        	return result;
        }
        int lastReach = 0;
        int currentReach = 0;
        int step = 0;
        int i = 0;
        while (i < input.size()) {
            if (i > currentReach) {
                break;
            }
            if (i > lastReach) {
                lastReach = currentReach;
                result.add(step);
            }
            int tempReach = i + input.get(i);
            if (tempReach > currentReach) {
                step = i;
                currentReach = tempReach;
            }
            i++;
        }
        System.out.println("i=" + i);
        System.out.println("cr =" + currentReach);
        System.out.println("step =" + step);
        System.out.println("lr=" + lastReach);
        if ((i > lastReach) && (result.get(result.size() - 1) != step)) {
            result.add(step);
        }
        
        if (lastReach >= input.size() - 1 && currentReach > input.size() - 1) {
            return result;
        } else {
            return null;
        }
    }
    
    public static String outputProcess(List<Integer> output) {
        if (output == null || output.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : output) {
            sb.append(Integer.toString(i));
            sb.append(", ");
        }
        sb.append("out");
        return sb.toString();
    }
    
    public static void close(BufferedReader br, InputStreamReader isr) {
        try {
            if (br != null) {
               br.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            if (isr != null) {
               isr.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }   
    }
    */
    	
    
} 
	 

		
