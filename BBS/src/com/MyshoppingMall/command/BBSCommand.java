package com.MyshoppingMall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MyshoppingMall.bbs.vo.BbsPage;
import com.MyshoppingMall.service.BbsService;

public class BBSCommand implements Bcommand {

	private BbsService service = new BbsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		String searchFilter = request.getParameter("searchFilter");
		String searchValue = request.getParameter("searchValue");
		String pageNoVal = request.getParameter("pageNo");
		
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		if (searchValue == null) {

			BbsPage bbsList = service.getBbsPage(pageNo);
			request.setAttribute("bbsList", bbsList);	
			return;
		}

		BbsPage bbsListAddSearch = null;
		if(searchFilter.equals("bbsSearchTitle")) {

			bbsListAddSearch = service.getBbsPageByTitle(pageNo, searchValue);
			bbsListAddSearch.setSearchValue(searchValue);
			bbsListAddSearch.setSearchFilter(searchFilter);
			request.setAttribute("bbsList", bbsListAddSearch);

		} else if (searchFilter.equals("bbsSearchContent")) {

			bbsListAddSearch = service.getBbsPageByContent(pageNo, searchValue);
			bbsListAddSearch.setSearchValue(searchValue);
			bbsListAddSearch.setSearchFilter(searchFilter);
			request.setAttribute("bbsList", bbsListAddSearch);
			
		} else if (searchFilter.equals("bbsSearchWriter")) {
			
			bbsListAddSearch = service.getBbsPageByWriter(pageNo, searchValue);
			bbsListAddSearch.setSearchValue(searchValue);
			bbsListAddSearch.setSearchFilter(searchFilter);
			request.setAttribute("bbsList", bbsListAddSearch);
		}

	}

}
