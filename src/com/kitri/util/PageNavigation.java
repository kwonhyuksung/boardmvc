package com.kitri.util;

public class PageNavigation {

	private String root;
	private boolean nowFirst;
	private boolean nowEnd;
	private int newArticleCount;
	private int totalArticleCount;
	private int totalPageCount;
	private int pageNo;
	private String navigator;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public boolean isNowFirst() {
		return nowFirst;
	}

	public void setNowFirst(boolean nowFirst) {
		this.nowFirst = nowFirst;
	}

	public boolean isNowEnd() {
		return nowEnd;
	}

	public void setNowEnd(boolean nowEnd) {
		this.nowEnd = nowEnd;
	}

	public int getNewArticleCount() {
		return newArticleCount;
	}

	public void setNewArticleCount(int newArticleCount) {
		this.newArticleCount = newArticleCount;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getNavigator() {
		return navigator;
	}

	public void makeNavigator() {
		StringBuffer tmpNavigator = new StringBuffer();

		int naviSize = BoardConstance.NAVIGATION_SIZE;
		int preLastPage = (pageNo - 1) / naviSize * naviSize;
		
		tmpNavigator.append("<table cellpadding='0' cellspacing='0' border='0'>\n");
		tmpNavigator.append(" <tr>\n");
		if (this.isNowFirst()) {
			tmpNavigator.append("  <td>\n");
			tmpNavigator.append("   <span class='newlist'><img src='" + root + "/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>최신목록</span>\n");
			tmpNavigator.append("   <font color='#999999'><img src='" + root + "/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>이전\n");
			tmpNavigator.append("   </font>\n");
		} else {
			tmpNavigator.append("  <td>\n");
			tmpNavigator.append("   <span class='newlist'><img src='" + root + "/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>최신목록</span>\n");
			tmpNavigator.append("   <span class='pglist' move-pg='"+ preLastPage +"'><img src='" + root + "/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>이전</span>\n");
		}
		tmpNavigator.append("  \n</td>\n");
		tmpNavigator.append("  <td style='padding: 0 5 0 5'>\n");
		tmpNavigator.append("   <table cellpadding='0' cellspacing='5' border='0'>\n");
		tmpNavigator.append("    <tr>\n");
		tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
		tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>");
		tmpNavigator.append("     </td>\n");
		
		int startPage = preLastPage + 1;
		int endPage = preLastPage + naviSize;
		if(endPage > totalPageCount)
			endPage = totalPageCount;
		
		for (int i = startPage; i <= endPage; i++) {
			if (pageNo == i) {
				tmpNavigator.append("     <td style='padding:0 7 0 7;' nowrap><font class='text_acc_02'><b>" + i + "</b></font></td>\n");
				tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
				tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>\n");
			} else {
				tmpNavigator.append("     <td style='padding:0 7 0 7;' nowrap><span class='pglist' move-pg='" + i + "'>" + i + "</span></td>\n");
				tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
				tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>\n");
			}
		}
		tmpNavigator.append("     </td>\n");
		tmpNavigator.append("    </tr>\n");
		tmpNavigator.append("   </table>\n");
		tmpNavigator.append("  </td>\n");
		tmpNavigator.append("  <td>\n");
		
		if (this.isNowEnd()) {
			tmpNavigator.append(" <font color='#999999'>");
			tmpNavigator.append(" 다음<img src='" + root + "/img/board/icon_next01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'></font>\n");
			tmpNavigator.append(" <span class='pglist' move-pg='" + totalPageCount + "'>끝목록<img src='" + root + "/img/board/icon_next02_dim.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'></span>\n");
		} else {
			tmpNavigator.append(" <span class='pglist' move-pg='" + (preLastPage + naviSize + 1) + "'>다음<img src='" + root + "/img/board/icon_next01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'></span>\n");
			tmpNavigator.append(" <span class='pglist' move-pg='" + totalPageCount + "'>끝목록<img src='" + root + "/img/board/icon_next02_dim.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'></span>\n");
		}
		
//		System.out.println("totalPageCount: " + totalPageCount);

		tmpNavigator.append("  </td>\n");
		tmpNavigator.append(" </tr>\n");
		tmpNavigator.append("</table>\n");

		this.navigator = tmpNavigator.toString();
	}

}

//<<최신목록 <이전 1 2 3 4 5 6 7 8 9 10 다음> 끝목록>>


























