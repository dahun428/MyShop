package com.MyshoppingMall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.util.BBSCheckFunction;
import com.MyshoppingMall.bbs.vo.Bbs;
import com.MyshoppingMall.bbs.vo.User;

public class BBSWriteCommand implements Bcommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {

		Bbs bbs = new Bbs();
		BbsDAO bbsDao = BbsDAO.getInstance();
		
		bbs.setBbsTitle(request.getParameter("bbsTitle"));
		bbs.setBbsContent(request.getParameter("bbsContent"));
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		User user = new User();
		user.setUserId(userId);
		bbs.setUser(user);
		int isSuccess = bbsDao.addBbs(bbs);
		if(isSuccess == BBSCheckFunction.BBS_WRITE_SUCCESS) {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_WRITE_SUCCESS);
		} else {
			request.setAttribute("isSuccess", BBSCheckFunction.BBS_DATABASE_ERROR);
		}
		
	}

}
