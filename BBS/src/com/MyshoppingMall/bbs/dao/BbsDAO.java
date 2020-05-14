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
	
	public int updateBbs(Bbs bbs) {
		
		String query = "UPDATE BBS_BBS " + 
				"    SET BBS_TITLE = ? , " + 
				"        BBS_CONTENT = ?, " + 
				"		 bbs_available = ? "+
				"    WHERE BBS_ID = ? ";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getBbsContent());
			pstmt.setInt(3, bbs.getBbsAvailable());
			pstmt.setInt(4, bbs.getBbsId());
			pstmt.executeQuery();
			
			return BBSCheckFunction.BBS_WRITE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return BBSCheckFunction.BBS_WRITE_FAIL;
	}
	
	
	public Bbs getBbsBybbsId(int bbsId) {
		String query = "SELECT ROWNUM NO, BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE "
				+ "	FROM BBS_BBS WHERE BBS_ID = ? AND BBS_AVAILABLE = 1 ";
		
		Bbs bbs = null;
		try {

			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bbsId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bbs = resultSetForm(rs);
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
		return bbs;
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
	public List<Bbs> getListByTitle(int startRow, int size, String title){
		List<Bbs> lists = new ArrayList<Bbs>();
		String query = "SELECT NO, BBS_ID, BBS_TITLE, USER_ID, bbs_date, BBS_CONTENT, BBS_AVAILABLE FROM ( " + 
				"    SELECT ROWNUM ROWN, B.* FROM ( " + 
				"        SELECT ROWNUM AS NO, A.* FROM ( " + 
				"            SELECT BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE " + 
				"            FROM BBS_BBS " + 
				"            WHERE bbs_available = 1 " + 
				"            AND bbs_title LIKE '%'|| ? ||'%' " + 
				"            ORDER BY BBS_ID asc) A " + 
				"        ORDER BY NO desc) B " + 
				"    WHERE ROWNUM <= ? ) " + 
				"WHERE ROWN > ? ";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setInt(2, size);
			pstmt.setInt(3, startRow);
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
	public List<Bbs> getListByContent(int startRow, int size, String content){
		List<Bbs> lists = new ArrayList<Bbs>();
		String query = "SELECT NO, BBS_ID, BBS_TITLE, USER_ID, bbs_date, BBS_CONTENT, BBS_AVAILABLE FROM ( " + 
				"    SELECT ROWNUM ROWN, B.* FROM ( " + 
				"        SELECT ROWNUM AS NO, A.* FROM ( " + 
				"            SELECT BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE " + 
				"            FROM BBS_BBS " + 
				"            WHERE bbs_available = 1 " + 
				"            AND bbs_content LIKE '%'|| ? ||'%' " + 
				"            ORDER BY BBS_ID asc) A " + 
				"        ORDER BY NO desc) B " + 
				"    WHERE ROWNUM <= ? ) " + 
				"WHERE ROWN > ? ";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, size);
			pstmt.setInt(3, startRow);
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
	public List<Bbs> getListByWriter(int startRow, int size, String writer){
		List<Bbs> lists = new ArrayList<Bbs>();
		String query = "SELECT NO, BBS_ID, BBS_TITLE, USER_ID, bbs_date, BBS_CONTENT, BBS_AVAILABLE FROM ( " + 
				"    SELECT ROWNUM ROWN, B.* FROM ( " + 
				"        SELECT ROWNUM AS NO, A.* FROM ( " + 
				"            SELECT BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE " + 
				"            FROM BBS_BBS " + 
				"            WHERE bbs_available = 1 " + 
				"            AND USER_ID = ? " + 
				"            ORDER BY BBS_ID asc) A " + 
				"        ORDER BY NO desc) B " + 
				"    WHERE ROWNUM <= ? ) " + 
				"WHERE ROWN > ? ";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setInt(2, size);
			pstmt.setInt(3, startRow);
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
	public List<Bbs> getAllList(int startRow, int size){


		List<Bbs> lists = new ArrayList<Bbs>();
		String query = "SELECT NO, BBS_ID, BBS_TITLE, USER_ID, bbs_date, BBS_CONTENT, BBS_AVAILABLE FROM ( " + 
				"    SELECT ROWNUM ROWN, B.* FROM ( " + 
				"        SELECT ROWNUM AS NO, A.* FROM ( " + 
				"            SELECT BBS_ID, BBS_TITLE, USER_ID, to_char(BBS_DATE, 'yyyy/mm/dd')as bbs_date, BBS_CONTENT, BBS_AVAILABLE " + 
				"            FROM BBS_BBS \r\n" + 
				"            WHERE bbs_available = 1 " + 
				"            ORDER BY BBS_ID asc) A " + 
				"        ORDER BY NO desc) B " + 
				"    WHERE ROWNUM <= ?) " + 
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
	public int selectCount() {

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
	public int selectCount(String attachment, String search) {

		String query = "select count(bbs_id) from bbs_bbs ";
		StringBuffer bf = new StringBuffer(query); 

		if(attachment.equals("title")) {
			bf.append("WHERE BBS_TITLE LIKE '%'|| ? ||'%' ");
		} else if (attachment.equals("content")) {
			bf.append("WHERE BBS_CONTENT LIKE '%'|| ? ||'%' ");
		} else if (attachment.equals("writer")) {	
			bf.append("WHERE USER_ID = ? ");
		}
		
		try {

			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(bf.toString());
			pstmt.setString(1, search);
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
