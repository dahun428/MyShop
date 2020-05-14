package com.MyshoppingMall.frontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.util.BBSCheckFunction;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.command.Bcommand;

public class BBSUpdateCommand implements Bcommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));
		
		BbsDAO bbsDao = new BbsDAO();
		Bbs bbs = bbsDao.getBbsBybbsId(bbsId);
		if(bbs == null) {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_DATABASE_ERROR);
		}
		
		
		bbs.setBbsTitle(bbsTitle);
		bbs.setBbsContent(bbsContent);
		int isSuccess = bbsDao.updateBbs(bbs);
		
		if(isSuccess == BBSCheckFunction.BBS_WRITE_SUCCESS) {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_WRITE_SUCCESS);
			request.setAttribute("bbs", bbs);
		} else {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_WRITE_FAIL);
		}
	}

}
