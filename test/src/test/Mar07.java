package test;

import java.util.*;

public class Mar07 {
	class Employee {
		private String name;
		private String title;
		private Employee manager;
		
		public Employee(String name, String title, Employee manager) {
			this.name = name;
			this.title = title;
			this.manager = manager;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Employee getManager() {
			return manager;
		}

		public void setManager(Employee manager) {
			this.manager = manager;
		}
	}
	
	private Employee director;
	private Map<Employee, List<Employee>> list;
	
	public Mar07() {
		director = new Employee("Kaneki", "director", null);
		list = new HashMap<Employee, List<Employee>>();
		list.put(director, null);
	}
	
	public void add(Employee e) {
		for (Employee employee : list.keySet()) {
			if (e.manager.equals(employee.name)) {
				list.get(employee).add(e);
			}
		}
	}
	
	public void printAll() {
		Employee e = director;
		printHelper(e);
	}
	
	private void printHelper(Employee e) {
		System.out.println(e.title + " " + e.name + " " + e.manager);
		for (Employee employee : list.get(e)) {
			printHelper(employee);
		}
	}
	
}
