package com.kitri.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.BoardConstance;
import com.kitri.util.Validator;

@WebServlet("/memo")
public class MemoController extends HttpServlet {
private static final long serialVersionUID = 1L;       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");					
		System.out.println("act ======================= " + act);
		
		// json 객체를 보내기 위한 준비
		response.setContentType("application/x-json; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		String list = "";		
		if ("writememo".equals(act)) {			
			list = BoardActionFactory.getMemoWriteAction().execute(request, response);			
		} else if ("listmemo".equals(act)) {
			list = BoardActionFactory.getMemoListAction().execute(request, response);
		} else if ("modifymemo".equals(act)) {
			list = BoardActionFactory.getMemoModifyAction().execute(request, response);
		} else if ("deletememo".equals(act)) {
			list = BoardActionFactory.getMemoDeleteAction().execute(request, response);
		}
		
//		System.out.println(list);
		
		// 어차피 모든 동작의 마무리는 목록 출력
		out.println(list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding(BoardConstance.CHARSET);
		
		doGet(request, response);
	}

}
