package com.kh.course.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.course.model.dao.BoardDao;
import com.kh.course.model.vo.Attachment;
import com.kh.course.model.vo.Board;
import com.kh.course.model.vo.Category;
import com.kh.course.model.vo.Reply;

public class BoardService {

	public int listcount() {
		Connection conn = JDBCTemplate.getConnection();

		int listCount = new BoardDao().listCount(conn);

		JDBCTemplate.close(conn);

		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Board> list = new BoardDao().selectList(conn, pi);

		JDBCTemplate.close(conn);

		return list;
	}

	public ArrayList<Category> selectCategoryList() {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Category> cList = new BoardDao().selectCategoryList(conn);

		JDBCTemplate.close(conn);

		return cList;
	}

	public int insertBoard(Board b, Attachment at) {
		Connection conn = JDBCTemplate.getConnection();

		int boardNo = new BoardDao().selectBoardNo(conn);

		if (boardNo != 0) {

			b.setBoardNo(boardNo);
			int result = new BoardDao().insertBoard(conn, b);

			int result2 = 1;
			if (result > 0 && at != null) {

				at.setRefno(boardNo);
				result2 = new BoardDao().insertAttachment(conn, at);
			}

			if (result * result2 > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

			JDBCTemplate.close(conn);

			return result * result2;
		} else {
			JDBCTemplate.close(conn);
			return boardNo;
		}

	}

	public int increaseCount(int bno) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new BoardDao().increaseCount(conn, bno);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public Board selectBoard(int bno) {
		Connection conn = JDBCTemplate.getConnection();

		Board b = new BoardDao().selectBoard(conn, bno);

		JDBCTemplate.close(conn);

		return b;
	}

	public Attachment selectAttachment(int bno) {
		Connection conn = JDBCTemplate.getConnection();

		Attachment at = new BoardDao().selectAttachment(conn, bno);

		JDBCTemplate.close(conn);

		return at;
	}

	public int updateBoard(Board b, Attachment at) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new BoardDao().updateBoard(conn, b);

		int result2 = 1;

		if (at != null) {

			if (at.getFileNo() != 0) {
				result2 = new BoardDao().updateAttachment(conn, at);
			} else {
				result2 = new BoardDao().insertAttachment(conn, at);
			}
		}

		if (result * result2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result * result2;
	}

	public int deleteBoard(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteBoard(conn,boardNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int insertReply(Reply r) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertReply(conn,r);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Reply> replyList(int refBno) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Reply> list = new BoardDao().replyList(conn, refBno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	
	//=============== 메인페이지에 불러올 리뷰 게시판 ==========
	public ArrayList<Board> selectReviewList() {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Board> list = new BoardDao().selectReviewList(conn);

		JDBCTemplate.close(conn);

		return list;
	}
}
