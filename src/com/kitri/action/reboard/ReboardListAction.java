package com.kitri.action.reboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.*;
import com.kitri.util.PageNavigation;
import com.kitri.util.Validator;

public class ReboardListAction implements Action {
	
	private static Action reboardListAction;
	
	static {
		reboardListAction = new ReboardListAction();
	}
	
	private ReboardListAction() {}

	public static Action getReboardListAction() {
		return reboardListAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bcode = Validator.notNumberToZero(request.getParameter("bcode"));
		int pg = Validator.notNumberToOne(request.getParameter("pg"));		
		String key = Validator.nullToBlank(request.getParameter("key"));
		String word = Validator.nullToBlank(request.getParameter("word"));
		
		List<ReboardDto> list = ReboardServiceImpl.getReboardService().listArticle(bcode, pg, key, word);
		request.setAttribute("list", list);
		
		PageNavigation pageNavigation = CommonServiceImpl.getCommonService().getPageNavigation(bcode, pg, key, word);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.makeNavigator();
		request.setAttribute("navigation", pageNavigation);
		
		return "/reboard/list.jsp";

	}

}
