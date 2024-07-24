package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	static Connection con=null;
	
	public static Connection getConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task_manager","root","2003");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return con;
	}

}
