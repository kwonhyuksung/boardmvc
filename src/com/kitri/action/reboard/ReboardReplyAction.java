package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.model.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardReplyAction implements Action {

	private static Action reboardReplyAction;
	
	static {
		reboardReplyAction = new ReboardReplyAction();
	}
	
	private ReboardReplyAction() {}

	public static Action getReboardReplyAction() {
		return reboardReplyAction;
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
			// 엄밀히 말해 이것은 원글 정보. 이것을 인식해야 한다.
			reboardDto.setRef(Integer.parseInt(request.getParameter("ref")));
			reboardDto.setLev(Integer.parseInt(request.getParameter("lev")));
			reboardDto.setStep(Integer.parseInt(request.getParameter("step")));
			reboardDto.setPseq(Integer.parseInt(request.getParameter("pseq")));
						
			int seq = ReboardServiceImpl.getReboardService().replyArticle(reboardDto);
			request.setAttribute("seq", seq);
			return "/reboard/writeok.jsp";
		}		
		return "/index.jsp";

	}

}
