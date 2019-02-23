package com.kitri.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.board.model.AlbumDto;
import com.kitri.member.model.MemberDto;
import com.kitri.util.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/uploadcontrol")
public class PictureUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String realPath;
	private int maxPostSize;
    
	@Override
	// 한 번만 실행.
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		// /upload의 실제 경로를 얻어오고 싶어. 서버의 레알 경로
		realPath = context.getRealPath("/upload");
		// 5M setup
		maxPostSize = 5 * 1024 * 1024;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오늘 날짜를 가진 문자열 생성
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(new Date());
		String saveDirectory = realPath + File.separator + today;
		File folder = new File(saveDirectory);
		if (!folder.exists()) {
			folder.mkdirs();
		}		
		
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, BoardConstance.CHARSET, new DateFileRenamePolicy());
//		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, BoardConstance.CHARSET, new DefaultFileRenamePolicy());
		
		// 바로 위에서 이미지가 저장되었는데, 원본 사이즈의 이미지가 웹상에 모두 뿌려지면 웹이 너무 느리다.
		// thumnail 이미지를 만들어서 저장하는 것이 좋다. 
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String path = "/index.jsp";
		if (memberDto != null) {
			AlbumDto albumDto = new AlbumDto();
			albumDto.setId(memberDto.getId());
			albumDto.setName(memberDto.getName());
			albumDto.setEmail(memberDto.getEmailid() + "@" + memberDto.getEmaildomain());
			albumDto.setSubject(multi.getParameter("subject"));
			albumDto.setContent(multi.getParameter("content"));
			albumDto.setBcode(Integer.parseInt(multi.getParameter("bcode")));
			
//			System.out.println(multi.getParameter("subject"));
			
			albumDto.setSaveFolder(today);
			albumDto.setOrignPicture(multi.getOriginalFileName("picture"));
			albumDto.setSavePicture(multi.getFilesystemName("picture"));
			
			System.out.println(realPath);
			System.out.println("원본이미지이름 : " + albumDto.getOrignPicture() + "저장이미지이름 : " + albumDto.getSavePicture());
			
//			int seq = ReboardServiceImpl.getReboardService().writeArticle(albumDto);
//			request.setAttribute("seq", seq);
			path = "/album/writeok.jsp";
		}		
		PageMove.forward(request, response, path);

	}

}
