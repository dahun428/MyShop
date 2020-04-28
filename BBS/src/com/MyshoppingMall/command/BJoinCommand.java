package com.MyshoppingMall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.dao.UserDAO;
import com.MyshoppingMall.bbs.util.UserCheckFunction;
import com.MyshoppingMall.bbs.vo.User;

public class BJoinCommand implements Bcommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		user.setUserGender(userGender);
		user.setUserEmail(userEmail);
		
		UserDAO userDao = new UserDAO();
		int existUser = userDao.existUser(userId);
		if(existUser == UserCheckFunction.NON_EXIST_USER) {
			userDao.addUser(user);
			request.setAttribute("userExist", UserCheckFunction.NON_EXIST_USER);
		}else {
			request.setAttribute("userExist", UserCheckFunction.EXIST_USER);
		}
		
	}

}
