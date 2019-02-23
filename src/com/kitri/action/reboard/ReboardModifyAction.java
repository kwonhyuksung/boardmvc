package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardModifyAction implements Action {
	
	private static Action reboardModifyAction;
	
	static {
		reboardModifyAction = new ReboardModifyAction();
	}
	
	private ReboardModifyAction() {}

	public static Action getReboardModifyAction() {
		return reboardModifyAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			ReboardDto reboardDto = new ReboardDto();
			reboardDto.setSeq(seq);
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));			
			
			ReboardServiceImpl.getReboardService().modifyArticle(reboardDto);
			request.setAttribute("seq", seq);
			return "/reboard/writeok.jsp";
		}		
		return "/index.jsp";

	}

}
