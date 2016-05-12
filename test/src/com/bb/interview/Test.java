package com.bb.interview;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
	private int number;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Onsite os = new Onsite();
		
		/*
		DtTreeNode[] array = new DtTreeNode[7];
		for (int i = 0; i < 7; i++) {
			array[i] = new DtTreeNode(i + 1);
		}
		
		array[3].father = array[1];
		array[4].father = array[1];
		array[5].father = array[2];
		array[6].father = array[2];
		array[1].father = array[0];
		array[2].father = array[0];
		DtTreeNode p = new DtTreeNode(1);
		List<DtTreeNode> res = os.findNode(array, p);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).val);
		}
		*/
		int j = 0;
		for (int i = 0; i < 100; i++) {
			j = j++;
			System.out.println(j);
		}
		
		
		System.out.println(j);
	}

	
}
