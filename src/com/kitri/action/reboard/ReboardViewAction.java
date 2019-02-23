package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.dao.CommonDaoImpl;
import com.kitri.board.model.service.ReboardServiceImpl;

public class ReboardViewAction implements Action {

	private static Action reboardViewAction;
	
	static {
		reboardViewAction = new ReboardViewAction();
	}
	
	private ReboardViewAction() {}

	public static Action getReboardViewAction() {
		return reboardViewAction;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		ReboardDto reboardDto = ReboardServiceImpl.getReboardService().viewArticle(seq);		
		request.setAttribute("article", reboardDto);
		return "/reboard/view.jsp";
	}

}
