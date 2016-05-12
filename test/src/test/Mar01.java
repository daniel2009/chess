package test;

import java.util.*;

public class Mar01 {
	abstract class Car {
		public Car(int numDoors) {
			myNumDoors = numDoors;
		}
		
		public abstract int maxNumPassengers();
		
		protected int myNumDoors;
	}
	
	class Sedan extends Car {
		public Sedan(int numDoors) {
			super(numDoors);
			
		}
		
		@Override
		public int maxNumPassengers() {
			return myNumDoors * 1;
		}
	}
	
	class LongObj {
		private int m_length;
		LongObj(int length) {
			m_length = length;
		}
	}
	
	class LongAndWidObj extends LongObj {
		private int m_width;
		public LongAndWidObj(int width, int length) {
			super(length);
			this.m_width = width;
		}
	}
	
	
	class SomeClass {
		void divide(int num, int den){
			if (den == 0) {
				throw new RuntimeException("DivideByZeroException");
			}
			System.out.println("" + (num / den));
		}
		
		void fun() {
			try {
				divide(4, 0);
			} catch (RuntimeException re) {
				re.printStackTrace();
			}
		}
	}
	
}
