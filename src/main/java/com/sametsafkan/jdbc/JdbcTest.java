package com.sametsafkan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
	private static final String user = "user";
	private static final String password = "password";
	
	public static void main(String[] args) {
		try(Connection conn = DriverManager.getConnection(jdbcUrl, user, password)){
			System.out.println("Connection Successfull...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
