package com.MyshoppingMall.frontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.dao.BbsDAO;
import com.MyshoppingMall.bbs.dao.UserDAO;
import com.MyshoppingMall.bbs.util.UserCheckFunction;
import com.MyshoppingMall.command.Bcommand;

public class BBSDeleteCommand implements Bcommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));
		
		UserDAO userDao = UserDAO.getInstance();
		int isExistUser = userDao.loginUser(userId, userPassword);
		
		if(isExistUser != UserCheckFunction.SUCCESS_LOGIN) {
			request.setAttribute("isSuccess", UserCheckFunction.NO_EXIST_ID);
			return;
		}
		
		BbsDAO bbsDao = new BbsDAO();
		bbsDao.getBbsBybbsId(bbsId);
		
		
	}

}
