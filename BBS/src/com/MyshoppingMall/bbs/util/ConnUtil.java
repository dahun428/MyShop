package com.MyshoppingMall.bbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {

	static {
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password ="zxcv1234";
		
		Connection connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	
	}
	

}
