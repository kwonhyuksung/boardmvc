<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/board_common.jsp" %>
<style>
.newlist,.pglist,.articlesubject,.writeBtn,.searchBtn,.myBtn {
	cursor: Pointer;
}
</style>
<script>
$(document).ready(function() {
	
	$(".writeBtn").click(function() {
		$("#act").val("mvwrite");
		$("#pg").val("1");
		$("#key").val("");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".articlesubject").click(function() {		
		$("#act").val("viewarticle");
		$("#seq").val($(this).attr("article-seq"));
		$("#commonForm").attr("action", "${root}/reboard").submit();		
	});
	
	$(".newlist").click(function() {
		$("#act").val("listarticle");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".pglist").click(function() {
		$("#act").val("listarticle");
		$("#pg").val($(this).attr("move-pg"));
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	$(".searchBtn").click(function() {
		$("#act").val("listarticle");
		$("#pg").val("1");
		$("#key").val($("#skey").val());
		$("#word").val($("#sword").val());
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".myBtn").click(function() {
		$("#act").val("listarticle");
		$("#pg").val("1");
		$("#key").val("id");
		$("#word").val("${userInfo.id}");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
});
</script>
<!-- title start -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="${root}/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>
<!-- title end -->

<!-- bbs start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr valign="bottom">
		<td nowrap>
			<img src="${root}/img/board/btn_write_01.gif" class="writeBtn" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기">
		</td>

		<td width="100%" style="padding-left: 6px" valign="bottom">새글 <b><font
			class="text_acc_02">${navigation.newArticleCount}</font></b> / 전체 <font
			class="text_acc_02">${navigation.totalArticleCount}</font></td>
		<td width="300" nowrap>
		<div align="right"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
</table>

<form name="listForm" method="post" style="margin: 0px">
<table width="100%" cellpadding="5" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="2" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr class="bg_board_title" align="center" height="28">
		<td nowrap><b>번호</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td></td>
		<td width="100%"><b>제목</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="120" nowrap><b>글쓴이</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td nowrap><b>조회</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="45" nowrap><b>날짜</b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>

	<!-- 공지기능 적용시 -->


	<!-- 공지기능 적용끝  -->
<c:forEach var="article" items="${list}">	
	<tr>
		<td align="center" class="text_gray">${article.seq}</td>
		<td></td>
		<td nowrap class="onetext" style="padding-right: 5px"></td>
		<!--td>
     
     </td-->
		<td style="word-break: break-all; padding-left: ${article.lev * 10}px">
		<label  class="articlesubject" article-seq="${article.seq}">${article.subject}</label>
		</td>
		<td></td>
		<td style="word-break: break-all;"><a href="javascript:;"
			onClick="showSideView();" class="link_board_04">${article.name}</a></td>
		<td></td>
		<td align="center" class="text_gray">${article.hit}</td>
		<td></td>
		<td align="center" class="text_gray">${article.logtime}</td>
	</tr>

	<tr>
		<td bgcolor="#ededed" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</c:forEach>


	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>
</form>
<!-- bbs end -->

<!-- 하단 페이징 -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="5"></td>
	</tr>
	<tr valign="top">
		<td nowrap>
			<img src="${root}/img/board/btn_write_01.gif" class="writeBtn" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기">
		</td>
		<td width="100%" align="center"><!--PAGE-->${navigation.navigator}</td>
		<td nowrap class="stext"><b>${navigation.pageNo}</b> / ${navigation.totalPageCount}
		pages</td>
	</tr>
</table>
<br>
<!-- 하단 페이징 -->

<!-- 검색 영역-->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="10"></td>
	</tr>
	<tr>
		<td width="50%"></td>
		<td nowrap>
			<select id="skey" class="inp">
				<option value="subject">글제목
				<option value="name">글쓴이
				<option value="seq">글번호
			</select>
			<span id="sear1"> <input type="text" id="sword" size="22"
			class="inp" style="margin-top: -19px;"></span>
			<img src="${root}/img/board/sbtn_s.gif" class="searchBtn" width="32" height="18"
			border="0" align="absmiddle" alt="검색">
			<c:if test="${userInfo != null}">
			<img src="${root}/img/board/sbtn_mytext.gif" class="myBtn" width="82" height="20"
			align="absmiddle" alt="내가 쓴 글 보기">
			</c:if>
			<br>
		</td>
		<td width="50%" align="right"><a href="#"><img
			src="${root}/img/board/sbtn_top.gif" width="24" height="11"
			align="absmiddle" alt="TOP"></a><br>
		</td>
	</tr>
</table>
</body>
</html>

