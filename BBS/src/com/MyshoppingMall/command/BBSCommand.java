package com.MyshoppingMall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.vo.BbsPage;
import com.MyshoppingMall.service.ListBbsService;

public class BBSCommand implements Bcommand {

	private ListBbsService service = new ListBbsService();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		String pageNoVal = request.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		BbsPage bbsList = service.getBbsPage(pageNo);
		request.setAttribute("bbsList", bbsList);
		
		
	}
	public static void main(String[] args) {
		ListBbsService service = new ListBbsService();
		BbsPage bbsList = service.getBbsPage(1);
		System.out.println(bbsList);
	}
	

}
