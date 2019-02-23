package com.kitri.board.model.service;

import java.util.HashMap;
import java.util.Map;

import com.kitri.board.model.dao.CommonDaoImpl;
import com.kitri.util.BoardConstance;
import com.kitri.util.PageNavigation;

public class CommonServiceImpl implements CommonService {
	
	private static CommonService commonService;
	
	static {
		commonService = new CommonServiceImpl();
	}
	
	private CommonServiceImpl() {}

	public static CommonService getCommonService() {
		return commonService;
	}

	@Override
	public PageNavigation getPageNavigation(int bcode, int pg, String key, String word) {
		int listSize = BoardConstance.ARTICLE_LIST_SIZE;
		int naviSize = BoardConstance.NAVIGATION_SIZE;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode + "");
		map.put("key", key);
		map.put("word", word);
		
		PageNavigation pageNavigation = new PageNavigation();		
		int newArticleCount = CommonDaoImpl.getCommonDao().getNewArticleCount(bcode);
		int totalArticleCount = CommonDaoImpl.getCommonDao().getTotalArticleCount(map);
		int totalPageCount = (totalArticleCount - 1) / listSize + 1;
		
		pageNavigation.setNewArticleCount(newArticleCount);
		pageNavigation.setTotalArticleCount(totalArticleCount);
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setNowFirst(pg <= naviSize);
		pageNavigation.setNowEnd((totalPageCount - 1) / naviSize * naviSize < pg);
		pageNavigation.setPageNo(pg);
		return pageNavigation;
	}

}
