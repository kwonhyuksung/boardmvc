package com.kitri.action.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class AdminCategoryMakeAction implements Action {
	
	private static Action adminCategoryMakeAction;
	
	static {
		adminCategoryMakeAction = new AdminCategoryMakeAction();	
	}
	
	private AdminCategoryMakeAction() {}
	
	public static Action getAdminCategoryMakeAction() {
		return adminCategoryMakeAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return null;

	}

}
