package com.kitri.board.model.service;

import java.util.List;



import com.kitri.board.model.MemoDto;
import com.kitri.board.model.dao.MemoDao;
import com.kitri.board.model.dao.MemoDaoImpl;

public class MemoServiceImpl implements MemoService {
	
	private static MemoService memoService;
	
	static {
		memoService = new MemoServiceImpl();
	}
	
	private MemoServiceImpl() {}

	public static MemoService getMemoService() {
		return memoService;
	}

	@Override
	public void writeMemo(MemoDto memoDto) {
		MemoDaoImpl.getMemoDao().writeMemo(memoDto);

	}

	@Override
	public List<MemoDto> listMemo(int seq) {
		
		return MemoDaoImpl.getMemoDao().listMemo(seq);
	}

	@Override
	public void modifyMemo(MemoDto memoDto) {
		MemoDaoImpl.getMemoDao().modifyMemo(memoDto);

	}

	@Override
	public void deleteMemo(int mseq) {
		MemoDaoImpl.getMemoDao().deleteMemo(mseq);

	}

}
