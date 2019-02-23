package com.kitri.action.memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kitri.action.Action;
import com.kitri.board.model.MemoDto;
import com.kitri.board.model.service.MemoServiceImpl;

public class MemoDeleteAction implements Action {

	private static Action memoDeleteAction;
	
	static {
		memoDeleteAction = new MemoDeleteAction();
	}
	
	private MemoDeleteAction() {}
	
	public static Action getMemoDeleteAction() {
		return memoDeleteAction;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mseq = Integer.parseInt(request.getParameter("mseq"));		
		
		MemoServiceImpl.getMemoService().deleteMemo(mseq);
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		List<MemoDto> list = MemoServiceImpl.getMemoService().listMemo(seq);
//		System.out.println("메모 갯수 : " + list.size());
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

	}

}
