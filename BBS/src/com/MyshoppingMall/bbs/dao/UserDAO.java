package com.MyshoppingMall.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

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
			
			String encryptionPw = DigestUtils.sha256Hex(userPassword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("user_password").equals(encryptionPw)) {
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
			String encryptionPw = DigestUtils.sha256Hex(user.getUserPassword());
			pstmt.setString(2, encryptionPw);
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
	/**
	 * 회원탈퇴시 사용할 메소드, userId 와 userPassword를 입력받아 해당 회원을 DB에서 삭제한다.	
	 * @param userId
	 * @param userPassword
	 */
	public void DeleteUser(String userId, String userPassword) {
		
		String query ="DELETE FROM BBS_USER WHERE USER_ID = ? AND USER_PASSWORD =  ? ";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			String encryptionPw = DigestUtils.sha256Hex(userPassword);
			pstmt.setString(2, encryptionPw);
			pstmt.executeQuery();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 업데이트 할 user 객체를 전달 받고, userPasswor 변수를 전달받아, id와 password가 일치하는 아이디의 패스워드, email을 수정한다.  
	 * @param user
	 * @param userPassword
	 */
	public void updateUser(User user, String userPassword) {
		
		String query = "UPDATE BBS_USER"
					+ " SET user_password = ?, user_email = ? "
					+ " WHERE USER_ID = ? AND USER_PASSWORD = ?";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			String encryptionPw = DigestUtils.sha256Hex(user.getUserPassword());
			pstmt.setString(1, encryptionPw);
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserId());
			pstmt.setString(4, userPassword);
			
			
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

