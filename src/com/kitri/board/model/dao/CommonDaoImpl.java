package com.kitri.board.model.dao;

import java.sql.*;
import java.util.Map;

import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class CommonDaoImpl implements CommonDao {
	
	private static CommonDao commonDao;
	
	static {
		commonDao = new CommonDaoImpl();
	}
	
	private CommonDaoImpl() {}

	public static CommonDao getCommonDao() {
		return commonDao;
	}
	
	@Override
	public int getNextSeq() {		
		int seq = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select board_seq.nextval \n");
			sql.append("from dual");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return seq;
	}

	@Override
	public void updateHit(int seq) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board set \n");
			sql.append("hit = hit + 1 \n");
			sql.append("where seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}		
	}

	@Override
	public int getNewArticleCount(int bcode) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(seq) cnt \n");
			sql.append("from board \n");			
			sql.append("where bcode = ? \n");
			sql.append("and (to_char(sysdate, 'yy-mm-dd') = to_char(logtime, 'yy-mm-dd'))");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, bcode);			
			rs = pstmt.executeQuery();
			rs.next();			
			cnt = rs.getInt(1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int getTotalArticleCount(Map<String, String> map) {		
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(seq) cnt \n");
			sql.append("from board \n");
			sql.append("where bcode = ?");
			String key = map.get("key");
			String word = map.get("word");
			if (!key.isEmpty() && !word.isEmpty()) {
				if ("subject".equals(key))
					sql.append("	and subject like '%'||?||'%'\n");
				else					
					sql.append("	and " + key + " = ?\n");				
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("bcode"));
			if (!key.isEmpty() && !word.isEmpty())
				pstmt.setString(2, word);
			rs = pstmt.executeQuery();
			rs.next();			
			cnt = rs.getInt(1);						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return cnt;
	}

}
