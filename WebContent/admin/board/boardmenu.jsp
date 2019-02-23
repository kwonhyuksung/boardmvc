<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
.menu_list {
	width: 250px;
}

.menu_header {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	font-weight: bold;
	background-color: #99ffff;
}

.menu_body {
	display: none;
	text-align: left;
}

.menu_body a {
	display: block;
	background-color: #99cc00;
	padding: 10px;
	text-decoration: none;
}

.menu_body a:hover {
	background-color: #ffffff;
	text-decoration: underline;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu p.menu_header").click(function(){
		$(this).next("div.menu_body").slideDown(100).siblings("div.menu_body").slideUp("fast");
	});
});
</script>
</head>
<body>
<div class="menu_list" id="menu">
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<c:set var="preCcode" value="0"></c:set>
<c:if test="${boardmenu != null}">
<c:forEach varStatus="i" var="boardListDto" items="${boardmenu}">
	<c:if test="${preCcode != boardListDto.ccode}">
		<p class="menu_header">${boardListDto.cname}</p>
		<div class="menu_body">
		<c:set var="preCcode" value="${boardListDto.ccode}">
		</c:set>
	</c:if>
		<a href="${root}/${boardListDto.control}?act=listarticle&bcode=${boardListDto.bcode}&pg=1&key=&word=">
		${boardListDto.bname}
		</a>
	<c:if test="${i.index < boardmenu.size() - 1}">
		<c:if test="${preCcode != boardmenu.get(i.index + 1).ccode}">
		</div>
		</c:if>
	</c:if>	
</c:forEach>
</c:if>
</div>
</body>
</html>

<!-- private int age;
public void setAge2(int age){
}
public int getAge2(){

property라는 것은 age가 아닌 set, get을 빼고 첫글자를 소문자로 한 age2가 된다. 
} -->
<%--
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.kitri.admin.board.model.BoardListDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
.menu_list {
	width: 250px;
}

.menu_header {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	font-weight: bold;
	background-color: #99ffff;
}

.menu_body {
	display: none;
	text-align: left;
}

.menu_body a {
	display: block;
	background-color: #99cc00;
	padding: 10px;
	text-decoration: none;
}

.menu_body a:hover {
	background-color: #ffffff;
	text-decoration: underline;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu p.menu_header").click(function(){
		$(this).next("div.menu_body").slideDown(100).siblings("div.menu_body").slideUp("fast");
	});
});
</script>
</head>
<body>
<div class="menu_list" id="menu">
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<%
String root = request.getContextPath();

List<BoardListDto> boardmenu = (List<BoardListDto>) application.getAttribute("boardmenu");
if (boardmenu != null) {
	//for (BoardListDto boardListDto : boardmenu) {
	int len = boardmenu.size();
	int ccode = -1 ;
	for (int i = 0; i < len; i++) {		
		BoardListDto boardListDto = boardmenu.get(i);
		if (ccode != boardListDto.getCcode()){
%>
<p class="menu_header"><%=boardListDto.getCname()%></p>
<div class="menu_body">
<%		
			ccode = boardListDto.getCcode();	
		}
%>
<a href="">
<%=boardListDto.getBname()%>
</a>
<%
		if (i < len - 1) {
			if (ccode != boardmenu.get(i + 1).getCcode()) {
%>
</div>
<%
			}
		}
	}
}
%>
</div>
</body>
</html>

  private int age;
public void setAge2(int age){
}
public int getAge2(){

property라는 것은 age가 아닌 set, get을 빼고 첫글자를 소문자로 한 age2가 된다. 
} --> --%>