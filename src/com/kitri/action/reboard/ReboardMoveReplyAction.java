package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.ReboardServiceImpl;

public class ReboardMoveReplyAction implements Action {
	
	private static Action reboardMoveReplyAction;
	
	static {
		reboardMoveReplyAction = new ReboardMoveReplyAction();
	}
	
	public ReboardMoveReplyAction() {}

	public static Action getReboardMoveReplyAction() {
		return reboardMoveReplyAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		int seq = Integer.parseInt(request.getParameter("seq"));
		ReboardDto reboardDto = ReboardServiceImpl.getReboardService().getArticle(seq);		
		request.setAttribute("article", reboardDto);
		return "/reboard/reply.jsp";
	}

}
