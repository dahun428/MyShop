package com.MyshoppingMall.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.command.BBSCommand;
import com.MyshoppingMall.command.BBSWriteCommand;
import com.MyshoppingMall.command.BBSViewCommand;
import com.MyshoppingMall.command.BJoinCommand;
import com.MyshoppingMall.command.Bcommand;
import com.MyshoppingMall.command.BLoginCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		Bcommand command = null;
		
		String url = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = url.substring(conPath.length());
		
		//로그인
		if(com.equals("/login.do")) {
			command = new BLoginCommand();
			command.execute(request, response);
			viewPage = "CheckPage/loginCheck.jsp";
		//회원가입	
		} else if (com.equals("/join.do")){
			command = new BJoinCommand();
			command.execute(request, response);
			viewPage = "CheckPage/joinCheck.jsp";
		//로그아웃
		} else if (com.equals("/logout.do")) {
			viewPage = "CheckPage/logoutCheck.jsp";
		//홈으로 돌아가기
		} else if (com.equals("/index.do")) {
			viewPage = "index.jsp";
		//게시판 메인으로 돌아가기	
		} else if (com.equals("/BBSmainPage.do")) {
			command = new BBSCommand();
			command.execute(request, response);
			viewPage = "BBSmainPage.jsp";
		//게시판 검색
		} else if (com.equals("/BBSsearch.do")) {
			command = new BBSCommand();
			command.execute(request, response);
			viewPage = "BBSmainPage.do";
		//게시판 글쓰기
		} else if (com.equals("/BBSwrite.do")) {
			command = new BBSWriteCommand();
			command.execute(request, response);
			viewPage = "CheckPage/bbsWriteCheck.jsp";
		//게시판 조회
		} else if(com.equals("/BBSviewPage.do")) {
			command = new BBSViewCommand();
			command.execute(request, response);
			viewPage = "BBSviewPage.jsp";
		//게시판 수정	
		} else if(com.equals("/BBSupdatePage.do")) {
			command = new BBSViewCommand();
			command.execute(request, response);
			viewPage = "BBSupdatePage.jsp";
		} else if(com.equals("/BBSupdate.do")) {
			command = new BBSUpdateCommand();
			command.execute(request, response);
			viewPage = "BBSviewPage.do";
		} else if(com.equals("/BBSdelet.do")) {
			command = new BBSDeleteCommand();
			command.execute(request, response);
			viewPage = "BBSmainPage.do";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}

}
