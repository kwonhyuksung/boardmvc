<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/board_common.jsp" %>
<c:if test="${article != null}">
<script>
$(document).ready(function() {
	
	$(".writeBtn").click(function() {
		$("#act").val("mvwrite");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".newlist").click(function() {
		$("#act").val("listarticle");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".prelist").click(function() {
		$("#act").val("listarticle");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".modifyBtn").click(function() {
		$("#act").val("mvmodify");
		$("#seq").val("${article.seq}");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".deleteBtn").click(function() {
		$("#act").val("deletearticle");
		$("#seq").val("${article.seq}");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	$(".replyBtn").click(function() {
		$("#act").val("mvreply");
		$("#seq").val("${article.seq}");
		$("#commonForm").attr("action", "${root}/reboard").submit();
	});
	
	// 버튼의 submit은 항상 주의깊게 봐야함
	// submit제어를 하지 않은 버튼이 있으면 새로고침을 유발할 수 있음
	// input button과 button을 구분해서 사용
	$("#memoBtn").click(function() {		
		var seq = "${article.seq}";				
		var mcontent = $("#mcontent").val();
		$("#mcontent").val("");
		var param = {"act" : "writememo", "seq" : seq, "mcontent" : mcontent}; // 이름과 값을 정해서 param에 넣음 json형식 
		$.ajax({
			url : '${root}/memo',
			type : 'POST',
			data : param,
			dataType : 'json',			
			success : function(response) {				
				makeList(response);				
			},
			error : function() {
				
			}
			
		});
	});
	
	// 동적으로 만든 이벤트는 이 로직으로 처리불가능
	/* $(".viewModifyBtn").click(function() {
		// 좋은 디버깅 방법
		$(this).parents("tr").attr("bgcolor", "orange");
	} */
		
	$.ajax({
		url : '${root}/memo',
		type : 'GET',
		data : {"act" : "listmemo", "seq" : "${article.seq}"},
		dataType : 'json',			
		success : function(response) {				
			makeList(response);
		},
		error : function() {
			
		}
		
	});
	
});
	
$(document).on("click", ".viewModifyBtn", function() {
	// 좋은 디버깅 방법
	//$(this).parents("tr").attr("bgcolor", "orange");	
	
	$(this).parents("tr").attr("style", "display: none;");
	$(this).parents("tr").next().attr("style", "display: ;");	
});

$(document).on("click", ".mModifyCancelBtn", function() {
	$(this).parents("tr").attr("style", "display: none;");
	$(this).parents("tr").prev().attr("style", "display: ;");	
});

$(document).on("click", ".mModifyBtn", function() {
	var seq = "${article.seq}";	
	var mseq = $(this).parents("tr").attr("mseq-data") ;
	var mcontent = $("#modifycontent" + mseq).val();
	$("#modifycontent" + mseq).val("");
	var param = {"act" : "modifymemo", "mseq" : mseq, "seq" : seq, "mcontent" : mcontent}; // 이름과 값을 정해서 param에 넣음 json형식 
	$.ajax({
		url : '${root}/memo',
		type : 'POST',
		data : param,
		dataType : 'json',			
		success : function(response) {				
			makeList(response);
		},
		error : function() {
			
		}
		
	});
});

$(document).on("click", ".mDeleteBtn", function() {
	if (confirm("정말로 삭제?")) {
		var seq = "${article.seq}";	
		var mseq = $(this).parents("tr").attr("mseq-data") ;	
		var param = {"act" : "deletememo", "mseq" : mseq, "seq" : seq}; // 이름과 값을 정해서 param에 넣음 json형식 
		$.ajax({
			url : '${root}/memo',
			type : 'GET',
			data : param,
			dataType : 'json',			
			success : function(response) {				
				makeList(response);
			},
			error : function() {
				
			}
		});		
	}
});

function makeList(memos) {
	$("#memolist").children("tr").remove();
	var memocnt = memos.memolist.length;
	var viewlist = "";	
	for (var i = 0; i < memocnt; i++) {
		var memo = memos.memolist[i];
		
		viewlist += "<tr mseq-data=\"" + memo.mseq + "\" height=\"40\">";
		viewlist += "	<td width=\"100\" style=\"padding-left: 14px\">" + memo.name + "</td>";
		viewlist += "	<td align=\"left\">" + memo.mcontent + "</td>";
		viewlist += "	<td width=\"100\">" + memo.mtime + "</td>";
		viewlist += "	<td width=\"100\">";
		if ('${userInfo.id}' == memo.id) {
			viewlist += "		<input type=\"button\" class=\"viewModifyBtn\" value=\"수정\">";
			viewlist += "		<input type=\"button\" class=\"mDeleteBtn\" value=\"삭제\">";
		}
		viewlist += "	</td>";
		viewlist += "</tr>";
		viewlist += "<tr mseq-data=\"" + memo.mseq + "\" style='display: none;'>";
		viewlist += "	<td colspan=\"3\" height=\"30\" style=\"padding: 10px\">";
		viewlist += "		<textarea id=\"modifycontent" + memo.mseq +  "\" rows=\"4\" cols=\"160\">" + memo.mcontent + "</textarea>";
		viewlist += "	</td>";
		viewlist += "	<td>";
		viewlist += "		<input type=\"button\" class=\"mModifyBtn\" value=\"수정\">";
		viewlist += "		<input type=\"button\" class=\"mModifyCancelBtn\" value=\"취소\">";
		viewlist += "	</td>";
		viewlist += "</tr>";
		viewlist += "<tr>";
		viewlist += "	<td class=\"bg_board_title_02\" colspan=\"4\" height=\"1\" style=\"overflow: hidden; padding: 0px\"></td>";
		viewlist += "</tr>";
	}
		
	$("#memolist").append(viewlist);
}
</script>
<!-- title -->
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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<form name="bbsForm" id="bbsbbs" method="post"><input
		type="hidden" name="" value="">
	<tr>
		<td valign="bottom" nowrap>
		
			<img src="${root}/img/board/btn_write_01.gif" class="writeBtn" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"> 
			<img src="${root}/img/board/btn_reply.gif" class="replyBtn" width="40" height="22"
			border="0" align="absmiddle" alt="답글">
			<c:if test="${userInfo.id == article.id}">
			<img src="${root}/img/board/btn_modify.gif" class="modifyBtn"
			border="0" align="absmiddle" alt="수정"> 
			<img src="${root}/img/board/btn_delete.gif" class="deleteBtn"
			border="0" align="absmiddle" alt="삭제">
			</c:if>
			
		</td>
		<td valign="bottom" width="100%" style="padding-left: 4px"></td>
		<td align="right" nowrap valign="bottom">
			<label class="newlist">최신목록</label> <font color="#c5c5c5">|</font>
			<label class="prelist">목록</label> <font color="#c5c5c5">|</font>

		<a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="5" cellspacing="0" width="100%">
	<tr>
		<td class="bg_board_title_02" colspan="2" height="2"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="28">
		<td class="bg_board_title" colspan="2" style="padding-left: 14px">
		<b><font class="text"> ${article.subject} </font></b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="26">
		<td width="100%" style="padding-left: 14px"><font class="stext">번호
		:</font> <font class="text_commentnum">${article.seq}</font> &nbsp; <font
			class="stext">글쓴이 :</font> <a href="javascript:;"
			onClick="showSideView();" class="link_board_02">${article.name}</a><br>
		</td>
		<td style="padding-right: 14px" nowrap class="stext">조회 : <font
			class="text_commentnum">${article.hit}</font> &nbsp; 스크랩 : <font
			class="text_commentnum">0</font> &nbsp; 날짜 : <font
			class="text_commentnum">${article.logtime}</font></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="15" cellspacing="0" width="100%">
	<tr valign="top">
		<td bgcolor="#ffffff" width="100%" class="text"
			style="padding-bottom: 8px; line-height: 1.3" id="clix_content">



		<P>${article.content}</P>



		</td>
		<td nowrap valign="top" align="right" style="padding-left: 0px">

		</td>
	</tr>
</table>

<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<!-- 하단 페이징 -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
	<tr valign="top">
		<td nowrap>
		<img src="${root}/img/board/btn_write_01.gif" class="writeBtn" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"> 
			<img src="${root}/img/board/btn_reply.gif" class="replyBtn" width="40" height="22"
			border="0" align="absmiddle" alt="답글">
		</td>
		<td style="padding-left: 4px" width="100%"><a href=""
			target="new"><img src="${root}/img/board/btn_print.gif"
			width="30" height="18" border="0" align="absmiddle" alt="인쇄"></a></td>

		<td align="right" nowrap>
		<label class="newlist">최신목록</label> <font color="#c5c5c5">|</font>
		<label class="prelist">목록</label> <font color="#c5c5c5">|</font>
		<font color="#c5c5c5">|</font> <a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="4" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="3" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr>
		<td colspan="4" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td colspan="3" height="30" style="padding: 10px">
		<textarea id="mcontent" rows="4" cols="160"></textarea>
		</td>
		<td>
		<c:if test="${userInfo != null}">
		<input type="button" id="memoBtn" value="작성">
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="4" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="4" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tbody id="memolist">
	</tbody>
</table>

</c:if>
<c:if test="${article == null}">
<script>
alert("삭제된 글이거나 부적절한 접근 입니다.");
location.href = "${root}/index.jsp"; // 나중에 list로 보내자.
</script>
</c:if>
<br>
</body>
</html>