package com.kitri.board.model.dao;

import java.util.Map;

public interface CommonDao {
	
	int getNextSeq();
	public void updateHit(int seq);
	
	int getNewArticleCount(int bcode);
	int getTotalArticleCount(Map<String, String>map);
	
}
