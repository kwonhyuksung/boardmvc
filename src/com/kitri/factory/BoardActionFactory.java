package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.action.memo.*;
import com.kitri.action.reboard.*;

public class BoardActionFactory {
	
	private static Action reboardWriteAction;
	private static Action reboardListAction;
	private static Action reboardViewAction;
	private static Action reboardReplyAction;
	private static Action reboardModifyAction;
	private static Action reboardDeleteAction;
	private static Action reboardMoveModifyAction;
	private static Action reboardMoveReplyAction;
	
	private static Action memoWriteAction;
	private static Action memoDeleteAction;
	private static Action memoListAction;
	private static Action memoModifyAction;
	
	static {
		reboardWriteAction = ReboardWriteAction.getReboardWriteAction();
		reboardListAction = ReboardListAction.getReboardListAction();
		reboardViewAction = ReboardViewAction.getReboardViewAction();
		reboardReplyAction = ReboardReplyAction.getReboardReplyAction();
		reboardModifyAction = ReboardModifyAction.getReboardModifyAction();
		reboardDeleteAction = ReboardDeleteAction.getReboardDeleteAction();
		reboardMoveModifyAction = ReboardMoveModifyAction.getReboardMoveModifyAction();
		reboardMoveReplyAction = ReboardMoveReplyAction.getReboardMoveReplyAction();				
		
		memoWriteAction = MemoWriteAction.getMemoWriteAction();				
		memoDeleteAction = MemoDeleteAction.getMemoDeleteAction();				
		memoListAction = MemoListAction.getMemoListAction();				
		memoModifyAction = MemoModifyAction.getMemoModifyAction();				
	}

	public static Action getReboardWriteAction() {
		return reboardWriteAction;
	}

	public static Action getReboardListAction() {
		return reboardListAction;
	}

	public static Action getReboardViewAction() {
		return reboardViewAction;
	}

	public static Action getReboardReplyAction() {
		return reboardReplyAction;
	}

	public static Action getReboardModifyAction() {
		return reboardModifyAction;
	}

	public static Action getReboardDeleteAction() {
		return reboardDeleteAction;
	}

	public static Action getReboardMoveModifyAction() {
		return reboardMoveModifyAction;
	}

	public static Action getReboardMoveReplyAction() {
		return reboardMoveReplyAction;
	}

	public static Action getMemoWriteAction() {
		return memoWriteAction;
	}

	public static Action getMemoDeleteAction() {
		return memoDeleteAction;
	}

	public static Action getMemoListAction() {
		return memoListAction;
	}

	public static Action getMemoModifyAction() {
		return memoModifyAction;
	}
	
	
		
}
