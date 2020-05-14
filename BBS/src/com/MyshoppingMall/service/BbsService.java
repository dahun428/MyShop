package com.MyshoppingMall.service;

import java.util.List;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.util.BBSCheckFunction;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.bbs.vo.BbsPage;

public class BbsService {

	private BbsDAO bbsDao = new BbsDAO();
	private int size = 10;

	public BbsPage getBbsPage(int pageNum) {
		
		int total = bbsDao.selectCount();
		List<Bbs> content = bbsDao.getAllList((pageNum-1) * size, pageNum * size);

		return new BbsPage(total, pageNum, size, content);
	}
	public BbsPage getBbsPageByTitle(int pageNum, String title) {
			List<Bbs> contents = bbsDao.getListByTitle((pageNum-1) * size, pageNum * size, title);
			int total = bbsDao.selectCount("title", title);

			return new BbsPage(total, pageNum, size, contents);
	}
	public BbsPage getBbsPageByContent(int pageNum, String content) {
			List<Bbs> contents = bbsDao.getListByContent((pageNum-1) * size, pageNum * size, content);
			int total = bbsDao.selectCount("content", content);

			return new BbsPage(total, pageNum, size, contents);
	}
	public BbsPage getBbsPageByWriter(int pageNum, String writer) {
			List<Bbs> contents = bbsDao.getListByWriter((pageNum-1) * size, pageNum * size, writer);
			int total = bbsDao.selectCount("writer", writer);

			return new BbsPage(total, pageNum, size, contents);
	}
	
}
