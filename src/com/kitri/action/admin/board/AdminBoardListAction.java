package com.kitri.action.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.model.service.AdminBoardServiceImpl;

// 이 클래스는 단 한 개의 인스턴스만 가져야 하므로, 싱글톤 패턴으로 만듬
public class AdminBoardListAction implements Action {

	private static Action adminBoardListAction;

	static {
		adminBoardListAction = new AdminBoardListAction();
	}

	private AdminBoardListAction() {
	}

	public static Action getAdminBoardListAction() {
		return adminBoardListAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<BoardListDto> list = AdminBoardServiceImpl.getAdminBoardService().getBoardMenu();
		// 게시판 목록은 한 번만 얻어와서 계속 사용하는 것이므로 application영역에 저장.
		ServletContext application = request.getServletContext();
		application.setAttribute("boardmenu", list);
		return "/admin/board/boardmenu.jsp";
	}

}
