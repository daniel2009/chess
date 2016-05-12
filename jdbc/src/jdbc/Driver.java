package jdbc;

import java.sql.*;


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/demo";
		String username = "root";
		String password = "123";
		try {
			Connection myConn = DriverManager.getConnection(url, username, password);
			Statement myStmt = myConn.createStatement();
			/*
			String sql = "insert into employee"
						+ "(id, last_name, first_name)"
						+ "values(4, 'Brown', 'David')";
			myStmt.executeUpdate(sql);
			System.out.println("Insertion complete");
			*/
			String query = "select * from employee";
			ResultSet myRs = myStmt.executeQuery(query);
			while (myRs.next()) {
				System.out.println(myRs.getString("id") + ',' 
						+ myRs.getString("last_name") + ',' +myRs.getString("first_name"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
