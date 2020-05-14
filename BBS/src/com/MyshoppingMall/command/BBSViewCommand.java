package com.MyshoppingMall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.util.BBSCheckFunction;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.service.BbsService;

public class BBSViewCommand implements Bcommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));
		BbsDAO bbsDao = new BbsDAO();
		Bbs bbs = bbsDao.getBbsBybbsId(bbsId);
		
		if(bbs == null) {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_NO_ARTICLE);
		} else {
			request.setAttribute("bbs", bbs);
		}
		
		
	}

}
