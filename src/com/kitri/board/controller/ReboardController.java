package com.kitri.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.*;

@WebServlet("/reboard")
public class ReboardController extends HttpServlet {
private static final long serialVersionUID = 1L;       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		System.out.println("act ======================= " + act);
		
		int bcode = Validator.notNumberToZero(request.getParameter("bcode"));
		int pg = Validator.notNumberToOne(request.getParameter("pg"));		
		String key = Validator.nullToBlank(request.getParameter("key"));
		String word = Validator.nullToBlank(request.getParameter("word"));
		
		// word는 한글일 가능성이 크다. 인코더 처리
		String queryString = "?bcode=" + bcode + "&pg=" + pg + "&key=" + key + "&word=" + URLEncoder.encode(word, BoardConstance.CHARSET);
		
		String path = "/index.jsp";
		if("mvwrite".equals(act)) {
			path = "/reboard/write.jsp" + queryString;
			PageMove.redirect(request, response, path);			
		} else if ("writearticle".equals(act)) {
			path = BoardActionFactory.getReboardWriteAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("viewarticle".equals(act)) {			
			path = BoardActionFactory.getReboardViewAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("listarticle".equals(act)) {			
			path = BoardActionFactory.getReboardListAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("mvmodify".equals(act)) {			
			path = BoardActionFactory.getReboardMoveModifyAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("modifyarticle".equals(act)) {
			path = BoardActionFactory.getReboardModifyAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("deletearticle".equals(act)) {
			path = BoardActionFactory.getReboardDeleteAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("mvreply".equals(act)) {
			path = BoardActionFactory.getReboardMoveReplyAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);			
		} else if ("replyarticle".equals(act)) {
			path = BoardActionFactory.getReboardReplyAction().execute(request, response);
			path += queryString;
			PageMove.forward(request, response, path);
		} else if ("".equals(act)) {
			
		} else if ("".equals(act)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding(BoardConstance.CHARSET);
		
		doGet(request, response);
	}

}
