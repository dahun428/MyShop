package com.MyshoppingMall.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.MyshoppingMall.bbs.util.ConnUtil;
import com.MyshoppingMall.bbs.util.JoinCheckFunction;
import com.MyshoppingMall.bbs.util.LoginCheckFunction;
import com.MyshoppingMall.bbs.vo.User;

public class UserDAO {


	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}

	/**
	 * userId와 userPassword를 데이터베이스에 입력받아, 두개의 값이 일치하는지 확인하는 메소드
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	public int loginUser(String userId, String userPassword) {

		try {
			String query = "SELECT user_password FROM BBS_USER WHERE USER_ID = ?";
			
			conn = ConnUtil.getConnection();
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
			return LoginCheckFunction.NO_EXIST_ID;

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null )conn.close();	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return LoginCheckFunction.DATABASE_ERROR;
	}
	/**
	 * String userId를 입력받아, id,name,gender,email를 리턴 받는 메소드
	 * @param userId
	 * @return
	 */
	public User getUser(String userId) {
		User user = null;

		try {
			String query = "SELECT USER_ID, USER_NAME, USER_GENDER, USER_EMAIL FROM BBS_USER WHERE USER_ID = ?";
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserGender(rs.getString("user_gender"));
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));

			}
			return user;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null )conn.close();	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}
	/**
	 * userId,password,name,gender,email을 순서대로 입력받아 데이터베이스에 등록하는 메소드
	 * @param user
	 * @return
	 */
	public int addUser(User user) {

		try {
			String query ="INSERT INTO BBS_USER VALUES (?,?,?,?,?)"; 
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.executeQuery();
			
			return JoinCheckFunction.JOIN_SUCCESS;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return JoinCheckFunction.JOIN_FAIL;
	}
	/**
	 * userId가 존재하는지 확인하는 메소드
	 * @param userId
	 * @return
	 */
	public int existUser(String userId) {
		
		String query ="SELECT user_id FROM BBS_USER WHERE USER_ID = ? ";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("user_id").equalsIgnoreCase(userId)) {
					return JoinCheckFunction.EXIST_USER;
				}else {
					return JoinCheckFunction.NON_EXIST_USER;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return JoinCheckFunction.NON_EXIST_USER;
	}
	public void DeleteUser(String userId, String userPassword) {
		
		String query ="DELETE FROM BBS_USER WHERE USER_ID = ? AND USER_PASSWORD =  ? ";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);
			pstmt.executeQuery();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	

}

