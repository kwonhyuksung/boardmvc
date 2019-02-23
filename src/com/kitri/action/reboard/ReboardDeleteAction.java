package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardDeleteAction implements Action {
	
	private static Action reboardDeleteAction;
	
	static {
		reboardDeleteAction = new ReboardDeleteAction();
	}
	
	private ReboardDeleteAction() {}
	
	public static Action getReboardDeleteAction() {
		return reboardDeleteAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			ReboardServiceImpl.getReboardService().deleteArticle(seq);			
			return "/reboard/writeok.jsp";
		}		
		return "/index.jsp";

	}

}
