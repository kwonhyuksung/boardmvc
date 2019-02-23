<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto,com.kitri.util.PageMove" %>
<%
String root = request.getContextPath();

MemberDto memberDto = new MemberDto();
memberDto.setId("kwon");
memberDto.setName("권혁성");
memberDto.setEmailid("billrich");
memberDto.setEmaildomain("naver.com");

session.setAttribute("userInfo", memberDto);

PageMove.redirect(request, response, "/adminboard?act=boardmenu");
%>    
