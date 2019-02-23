<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<c:set var="bcode" value="${param.bcode}"></c:set>
<c:set var="pg" value="${param.pg}"></c:set>
<c:set var="key" value="${param.key}"></c:set>
<c:set var="word" value="${param.word}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/css/skin_purple.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<form name="commonForm" id="commonForm" method="get">
	<input type="hidden" name="act" id="act" value="">
	<input type="hidden" name="bcode" id="bcode" value="${bcode}">
	<input type="hidden" name="pg" id="pg" value="${pg}">
	<input type="hidden" name="key" id="key" value="${key}">
	<input type="hidden" name="word" id="word" value="${word}">
	<input type="hidden" name="seq" id="seq" value="">
</form>