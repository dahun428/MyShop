package com.MyshoppingMall.bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.MyshoppingMall.bbs.util.LoginCheckFunction;

public class UserDAO {

	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	public UserDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password="zxcv1234";
			conn = DriverManager.getConnection(url, user, password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public int login(String userId, String userPassword) {
		
		try {
			String query = "SELECT user_password FROM BBS_USER WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("user_password").equals(userPassword)) {
					return LoginCheckFunction.SUCCESS_LOGIN;
				} else {
					return LoginCheckFunction.NO_EQUALS_PASSWORD;
				}
			}
			rs.close();
			pstmt.close();
			conn.close();

			return LoginCheckFunction.NO_EXIST_ID;

		}catch(Exception e) {
			e.printStackTrace();
		}
		return LoginCheckFunction.DATABASE_ERROR;
	}

}

