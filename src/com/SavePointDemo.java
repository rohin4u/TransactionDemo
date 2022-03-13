package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "password");
		Statement st = con.createStatement();
		
		con.setAutoCommit(false);
		
		st.executeUpdate("insert into accounts values ('malini',60000)");
		st.executeUpdate("insert into accounts values ('Suresh',20000)");
		
		Savepoint sp = con.setSavepoint();
		
		int balance = 80000;
		
		st.executeUpdate("insert into accounts values ('Jyoti',"+balance+")");
		
		if(balance<90000) {
			System.out.println("oops wrong entry");
			con.rollback(sp);
			System.out.println("rollback has been performed");
		}
		

//		con.releaseSavepoint(sp);
		
		
		con.commit();
		con.close();
	}

}
