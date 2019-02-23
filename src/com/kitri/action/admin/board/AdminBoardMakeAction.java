package com.kitri.action.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class AdminBoardMakeAction implements Action {

	private static Action adminBoardMakeAction;
	
	static {
		adminBoardMakeAction = new AdminBoardMakeAction();
	}
	
	private AdminBoardMakeAction() {}
	
	public static Action getAdminBoardMakeAction() {
		return adminBoardMakeAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return null;

	}

}
