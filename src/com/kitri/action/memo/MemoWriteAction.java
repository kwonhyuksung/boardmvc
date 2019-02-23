package com.kitri.action.memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kitri.action.Action;
import com.kitri.board.model.MemoDto;
import com.kitri.board.model.service.MemoServiceImpl;
import com.kitri.member.model.MemberDto;

public class MemoWriteAction implements Action {

	private static Action memoWriteAction;
	
	static {
		memoWriteAction = new MemoWriteAction();
	}
	
	private MemoWriteAction() {}
	
	public static Action getMemoWriteAction() {
		return memoWriteAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if (memberDto != null) {
			MemoDto memoDto = new MemoDto();
			int seq = Integer.parseInt(request.getParameter("seq"));
			memoDto.setSeq(seq);
			memoDto.setMcontent(request.getParameter("mcontent"));
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			
			MemoServiceImpl.getMemoService().writeMemo(memoDto);
			
			List<MemoDto> list = MemoServiceImpl.getMemoService().listMemo(seq);
//			System.out.println("메모 갯수 : " + list.size());
			// json 파일을 만들자
			JSONObject mlist = new JSONObject();
			JSONArray jarray = new JSONArray();
			for (MemoDto mDto : list) {
				JSONObject memo = new JSONObject();
				memo.put("mseq", mDto.getMseq());
				memo.put("seq", mDto.getSeq());
				memo.put("id", mDto.getId());
				memo.put("name", mDto.getName());
				memo.put("mcontent", mDto.getMcontent());
				memo.put("mtime", mDto.getMtime());
				
				jarray.put(memo);
			}
			
			mlist.put("memolist", jarray);
			return mlist.toString();
//			System.out.println(mlist.toString());
//			[{"mseq":2,"name":"안효인","id":"java2","mcontent":"안녕하세요 메모글입니다.\n1등???","mtime":"2019-02-18 16:46:09","seq":370}
		}
		return null;
	}

}

/* json 형식의 대략
 * { "memolist" : [
 * 					{"mseq" : 20, "seq" : 370, "id" : "kwon", "name" : "권혁성", "mcontent" : "내용", "mtime" : "2019-02-16.."} * 					
 * 					{"mseq" : 20, "seq" : 370, "id" : "kwon", "name" : "권혁성", "mcontent" : "내용", "mtime" : "2019-02-16.."} * 					
 * 					{"mseq" : 20, "seq" : 370, "id" : "kwon", "name" : "권혁성", "mcontent" : "내용", "mtime" : "2019-02-16.."} * 					
 * 				]
 * }	  
 */