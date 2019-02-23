package com.kitri.board.model.service;

import java.util.List;

import com.kitri.board.model.ReboardDto;

public interface ReboardService {
	
	int writeArticle(ReboardDto reboardDto);
	List<ReboardDto> listArticle(int bcode, int pg, String key, String word);
	ReboardDto viewArticle(int seq);
	int replyArticle(ReboardDto reboardDto);
	ReboardDto getArticle(int seq);
	int modifyArticle(ReboardDto reboardDto);
	int deleteArticle(int seq);
	
}
