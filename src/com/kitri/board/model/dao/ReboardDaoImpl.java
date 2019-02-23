package com.kitri.board.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.board.model.ReboardDto;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class ReboardDaoImpl implements ReboardDao {
	
	private static ReboardDao reboardDao;
	
	static {
		reboardDao = new ReboardDaoImpl();
	}
	
	private ReboardDaoImpl() {}

	public static ReboardDao getReboardDao() {
		return reboardDao;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into board (seq, id, name, email, subject, content, hit, logtime, bcode) \n");
			sql.append("	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n");
			sql.append("	into reboard (rseq, seq, ref, lev, step, pseq, reply) \n");
			sql.append("	values (reboard_rseq.nextval, ?, ?, 0, 0, 0, 0) \n");
			sql.append("select * from dual");
			pstmt = conn.prepareStatement(sql.toString());
//			System.out.println(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx,  reboardDto.getSeq());
			pstmt.setString(++idx,  reboardDto.getId());
			pstmt.setString(++idx,  reboardDto.getName());
			pstmt.setString(++idx,  reboardDto.getEmail());
			pstmt.setString(++idx,  reboardDto.getSubject());
			pstmt.setString(++idx,  reboardDto.getContent());			
			pstmt.setInt(++idx,  reboardDto.getBcode());
			pstmt.setInt(++idx,  reboardDto.getSeq());
			pstmt.setInt(++idx,  reboardDto.getRef());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> map) {		
		List<ReboardDto> list = new ArrayList<ReboardDto>();		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();	
			
			sql.append("select b.*\n");
			sql.append("from (select rownum rn, a.*\n");
			sql.append("	from (select b.seq, b.id, b.name, b.email, b.subject, b.content, b.bcode, b.hit,\n");
			sql.append("			r.seq rseq, r.ref, r.lev, r.step, r.pseq, r.reply,\n");
			sql.append("			case when to_char(logtime, 'yymmdd') = to_char(sysdate, 'yymmdd')\n");
			sql.append("				then to_char(logtime, 'hh24:mi:ss')\n");
			sql.append("				else to_char(logtime, 'yy.mm.dd')\n");
			sql.append("			end logtime\n");
			sql.append("	from board b, reboard r\n");
			sql.append("	where b.seq = r.seq\n");
			sql.append("	and b.bcode = ?\n");
			String key = map.get("key");
			String word = map.get("word");
			if (!key.isEmpty() && !word.isEmpty()) {
				if ("subject".equals(key))
					sql.append("	and b.subject like '%'||?||'%'\n");
				else					
					sql.append("	and b." + key + " = ?\n");				
			}
			sql.append("	order by ref desc, step) a\n");
			sql.append("	where rownum <= ?) b\n");
			sql.append("where b.rn > ?\n");		
			
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if (!key.isEmpty() && !word.isEmpty())
				pstmt.setString(++idx, word);
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
						
//			System.out.println(sql.toString());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReboardDto reboardDto = new ReboardDto();
				reboardDto.setSeq(rs.getInt("seq"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setName(rs.getString("name"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setRseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
				
				list.add(reboardDto);
			}
			
//		System.out.println(reboardDto.getContent());
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		ReboardDto reboardDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select b.seq, b.id, b.name, b.email, b.subject, b.content, b.hit, b.logtime, b.bcode, \n");
			sql.append("	r.rseq, r.ref, r.lev, r.step, r.pseq, r.reply \n");
			sql.append("from board b, reboard r \n");
			sql.append("where b.seq = r.seq \n");
			sql.append("and b.seq = ?");
			pstmt = conn.prepareStatement(sql.toString());			
			pstmt.setInt(1, seq);
			
//			System.out.println(seq);
//			System.out.println(sql.toString());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reboardDto = new ReboardDto();
				reboardDto.setSeq(rs.getInt("seq"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setName(rs.getString("name"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setRseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
			}
			
//		System.out.println(reboardDto.getContent());
			
		} catch (SQLException e) {
			reboardDto = null;
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return reboardDto;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {		
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			StringBuffer update_step = new StringBuffer();
			update_step.append("update reboard set \n");
			update_step.append("step = step + 1 \n");
			update_step.append("where step > ? \n");
			update_step.append("and ref = ?");
			
			pstmt = conn.prepareStatement(update_step.toString());			
			pstmt.setInt(1,  reboardDto.getStep());
			pstmt.setInt(2,  reboardDto.getRef());
			pstmt.executeUpdate();
			pstmt.close();			
			
			StringBuffer inert_reply = new StringBuffer();
			inert_reply.append("insert all \n");
			inert_reply.append("	into board (seq, id, name, email, subject, content, hit, logtime, bcode) \n");
			inert_reply.append("	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n");
			inert_reply.append("	into reboard (rseq, seq, ref, lev, step, pseq, reply) \n");
			inert_reply.append("	values (reboard_rseq.nextval, ?, ?, ?, ?, ?, 0) \n");
			inert_reply.append("select * from dual");
			
			pstmt = conn.prepareStatement(inert_reply.toString());			
			int idx = 0;
			pstmt.setInt(++idx,  reboardDto.getSeq());
			pstmt.setString(++idx,  reboardDto.getId());
			pstmt.setString(++idx,  reboardDto.getName());
			pstmt.setString(++idx,  reboardDto.getEmail());
			pstmt.setString(++idx,  reboardDto.getSubject());
			pstmt.setString(++idx,  reboardDto.getContent());			
			pstmt.setInt(++idx,  reboardDto.getBcode());
			pstmt.setInt(++idx,  reboardDto.getSeq());			
			pstmt.setInt(++idx,  reboardDto.getRef());			
			pstmt.setInt(++idx,  reboardDto.getLev() + 1);			
			pstmt.setInt(++idx,  reboardDto.getStep() + 1);
			pstmt.setInt(++idx,  reboardDto.getPseq());
			
//			System.out.println(inert_reply);
//			System.out.println(reboardDto.getPseq());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			StringBuffer update_reply = new StringBuffer();
			update_reply.append("update reboard set \n");
			update_reply.append("reply = reply + 1 \n");
			update_reply.append("where seq = ?");
			
			pstmt = conn.prepareStatement(update_reply.toString());			
			pstmt.setInt(1,  reboardDto.getPseq());			
			pstmt.executeUpdate();			
			
			conn.commit();
			
			cnt = reboardDto.getSeq();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public ReboardDto getArticle(int seq) {		
		return null;
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {		
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board set \n");
			sql.append("subject = ?, \n");
			sql.append("content = ? \n");
			sql.append("where seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
//			System.out.println(sql.toString());	
//			System.out.println(reboardDto.getSubject());
			
			pstmt.setString(1,  reboardDto.getSubject());
			pstmt.setString(2,  reboardDto.getContent());			
			pstmt.setInt(3,  reboardDto.getSeq());
			pstmt.executeUpdate();
			
			cnt = reboardDto.getSeq();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public int deleteArticle(int seq) {		
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {			
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete \n");
			sql.append("from reboard \n");			
			sql.append("where seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
//			System.out.println(sql.toString());						
			pstmt.setInt(1, seq);
			cnt = pstmt.executeUpdate();
			pstmt.close();
			sql.setLength(0);
			
			sql.append("delete \n");
			sql.append("from board \n");			
			sql.append("where seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
//			System.out.println(sql.toString());						
			pstmt.setInt(1, seq);
			cnt = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

}





