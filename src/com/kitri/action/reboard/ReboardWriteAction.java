package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardWriteAction implements Action {

	private static Action reboardWriteAction;
	
	static {
		reboardWriteAction = new ReboardWriteAction();
	}
	
	private ReboardWriteAction() {}

	public static Action getReboardWriteAction() {
		return reboardWriteAction;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			ReboardDto reboardDto = new ReboardDto();
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmailid() + "@" + memberDto.getEmaildomain());
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setBcode(Integer.parseInt(request.getParameter("bcode")));
			
			int seq = ReboardServiceImpl.getReboardService().writeArticle(reboardDto);
			request.setAttribute("seq", seq);
			return "/reboard/writeok.jsp";
		}		
		return "/index.jsp";
	}

}
