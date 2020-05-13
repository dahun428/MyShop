package com.MyshoppingMall.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.MyshoppingMall.bbs.util.BBSCheckFunction;
import com.MyshoppingMall.bbs.util.ConnUtil;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.bbs.vo.User;

public class BbsDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static BbsDAO instance = new BbsDAO();
	public static BbsDAO getInstance() {
		return instance;
	}

	public List<Bbs> getBbs(){

		List<Bbs> lists = new ArrayList<Bbs>();

		try {

			String query = "select ROWNUM RWN,  A.BBS_ID, A.BBS_TITLE, A.USER_ID, A.bbs_date, A.BBS_CONTENT, A.BBS_AVAILABLE from "
					+ "(select  BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd') as bbs_date, BBS_CONTENT, BBS_AVAILABLE "
					+ "from bbs_bbs order by bbs_id asc) A "
					+ "order by ROWNUM desc ";
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Bbs bbs = resultSetForm(rs);
				lists.add(bbs);
			}


		}catch(Exception e) {

			e.printStackTrace();

		}finally {

			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();

			}catch(Exception e) {

				e.printStackTrace();
			}
		}

		return lists;
	}
			
	public int addBbs(Bbs bbs) {
		
		String query = "INSERT INTO BBS_BBS (BBS_ID, BBS_TITLE, USER_ID, BBS_DATE, BBS_CONTENT, BBS_AVAILABLE)" + 
						"VALUES (BBS_BBS_SEQ.NEXTVAL, ?,?, SYSDATE,?,1) ";
		try {

			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getUser().getUserId());
			pstmt.setString(3, bbs.getBbsContent());
			pstmt.executeQuery();
			
			return BBSCheckFunction.BBS_WRITE_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return BBSCheckFunction.BBS_DATABASE_ERROR;
	}

	public List<Bbs> getList(int startRow, int size){
		
		
		List<Bbs> lists = new ArrayList<Bbs>();
		String query = "SELECT NO, BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE " + 
				"FROM ( " + 
				"    SELECT ROWNUM ROWN, B.* FROM ( " + 
				"        SELECT * FROM ( " + 
				"            SELECT ROWNUM AS NO, A.* " + 
				"            FROM BBS_BBS A \r\n" + 
				"            WHERE bbs_available= 1 " + 
				"            ORDER BY bbs_id desc ) " + 
				"        ORDER BY NO DESC ) B " + 
				"    WHERE ROWNUM <= ? " + 
				") " + 
				"WHERE ROWN > ? ";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, size);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Bbs bbs = resultSetForm(rs);
				lists.add(bbs);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return lists;
	}
	public int selectCount(Connection conn) {
		
		String query = "select count(bbs_id) from bbs_bbs ";
	
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
			return BBSCheckFunction.BBS_NO_ARTICLE;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return BBSCheckFunction.BBS_DATABASE_ERROR;
	}
	private Bbs resultSetForm(ResultSet rs) throws SQLException {
		
		Bbs bbs = new Bbs();
		bbs.setBbsNo(rs.getInt("NO"));
		bbs.setBbsId(rs.getInt("BBS_ID"));
		bbs.setBbsTitle(rs.getString("BBS_TITLE"));

		User user = new User();
		user.setUserId(rs.getString("USER_ID"));
		bbs.setUser(user);

		bbs.setBbsDate(rs.getString("bbs_date"));
		bbs.setBbsContent(rs.getString("BBS_CONTENT"));
		bbs.setBbsAvailable(rs.getInt("BBS_AVAILABLE"));
		
		
		return bbs;
	}
	
}
