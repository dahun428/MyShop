package com.MyshoppingMall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.util.ConnUtil;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.bbs.vo.BbsPage;

public class ListBbsService {

	private BbsDAO bbsDao = new BbsDAO();
	private int size = 10;
	
	public BbsPage getBbsPage(int pageNum) {
		try (Connection conn = ConnUtil.getConnection()){
			int total = bbsDao.selectCount(conn);
			List<Bbs> content = bbsDao.getList((pageNum-1) * size, pageNum * size);
			
			return new BbsPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		ListBbsService service = new ListBbsService();
		
		System.out.println(service.getBbsPage(1));
		
	}
}
