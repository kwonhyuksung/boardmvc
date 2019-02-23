package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.action.admin.board.AdminBoardListAction;
import com.kitri.action.admin.board.AdminCategoryMakeAction;

// adminboard에 action을 만드는 공장
public class AdminBoardActionFactory {
	
	private static Action adminBoardListAction;
	private static Action adminBoardMakeAction;
	private static Action adminCategoryMakeAction;	
	
	static {
		adminBoardListAction = AdminBoardListAction.getAdminBoardListAction();
		adminBoardMakeAction = AdminCategoryMakeAction.getAdminCategoryMakeAction();
		adminCategoryMakeAction = AdminCategoryMakeAction.getAdminCategoryMakeAction();
	}

	public static Action getAdminBoardListAction() {
		return adminBoardListAction;
	}

	public static Action getAdminBoardMakeAction() {
		return adminBoardMakeAction;
	}

	public static Action getAdminCategorymakeAction() {
		return adminCategoryMakeAction;
	}
	
	
	
}
