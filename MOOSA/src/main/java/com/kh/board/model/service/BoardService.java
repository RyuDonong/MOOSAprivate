package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardReply;
import com.kh.board.model.vo.Category;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.BoardPagingBar;

public class BoardService {

	//리스트 전부 가져오기
	public ArrayList<Board> boardList(BoardPagingBar bp) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList <Board> list = new BoardDao().boardList(conn,bp);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//조회수증가
	public int boardCount(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().boardCount(conn,bno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	//작성 페이지에서 option에 넣을 카테고리 종류 가져오기
	public ArrayList<Category> boardInsert() {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Category> caList = new BoardDao().boardInsert(conn);
		
		JDBCTemplate.close(conn);

		return caList;
	}
	
	//글작성
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		//게시글 넘버를 먼저 추출해와야 함.(가져온 정보를 넣어줄 게시판이 어떤건지 알아야하고, 추출이 돼야 시퀀스 넘버가 잘 지정됐다는 얘기)
		int boardNo = new BoardDao().selectBoardNo(conn);
		
		if(boardNo!=0) { //0이 아니면 추출성공
			
			b.setBoardNo(boardNo); 
			
			//게시글 내용
			int result1 = new BoardDao().contentInsert(conn,b);
			
			//게시글 첨부파일
			int result2 = 1; // 가져온 정보에 첨부파일이 없을 경우도 있으니 1로 설정함
			
			if(result1>0 && at!=null) {
				at.setRefNo(boardNo); //참조게시글 번호 지정해주기
				result2 = new BoardDao().atInsert(conn,at);
				
			}
			
			if(result1*result2>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			return result1*result2;
			
		}else {
			JDBCTemplate.close(conn);
			return boardNo;
		}
	}

	//첨부파일 가져오기
	public Attachment selectAt(int bno) {
		Connection conn = JDBCTemplate.getConnection();
		
		Attachment at = new BoardDao().selectAt(conn,bno);
		
		JDBCTemplate.close(conn);
		
		return at;
	}
	
	
	//게시글 클릭시 상세보기
	public Board boardDetail(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Board b = new BoardDao().boardDetail(conn,bno);
		
		JDBCTemplate.close(conn);
		
		return b;
	}

	
	//글 정보 가져오기	
	public Board selectBoard(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Board b = new BoardDao().selectBoard(conn,bno);
		JDBCTemplate.close(conn);
		return b;
	}

	//게시글, 첨부파일 수정
	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 수정
		int result1 = new BoardDao().updateContent(conn,b);
		
		//첨부파일 수정
		int result2 = 1;
		
		if(at!=null) {//가져온 첨부파일이 있다면
			
			if(at.getFileNo()!=0){
				result2 = new BoardDao().updateAt(conn,at);//기존 첨부파일이 있는경우 update
				
			}else {
				result2 = new BoardDao().atInsert(conn,at);	//새로운 첨부파일 insert
			}
		}
		
				
		if(result1*result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result1*result2;
		
		
	}
	
	//글 삭제 (기존 첨부파일은 삭제로)
	public int deleteBoard(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//글정보 삭제
		int result = new BoardDao().deleteBoard(conn,bno);
		
		//게시판 기능 완성 후 시간 남을 때 첨부파일 삭제
		//int result2 = 1;
		//result2 = new BoardDao().deleteAt(conn,bno);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//============= 게시판 좋아요 취소 ==========
	
		public int likesCancel(int likeBno) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new BoardDao().likesCancel(conn,likeBno);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.commit(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

	//============= 게시판 좋아요 업데이트 ==========
	
	public int likesUpdate(int likeBno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().likesUpdate(conn,likeBno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//댓글정보
	public ArrayList<BoardReply> selectReply(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<BoardReply> br = new BoardDao().selectReply(conn,bno);
		
		JDBCTemplate.close(conn);
		return br;
	}
	
	//댓글입력
	public int insertReply(String replyContent, int bno, int userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().insertReply(conn,replyContent,bno,userNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//댓글삭제
	public int replyDelete(int replyNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().replyDelete(conn,replyNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//따봉 도르마무!!
	public int replyCommendCancel(int commendNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().replyCommendCancel(conn,commendNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//따봉!!
	public int replyCommend(int commendNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().replyCommend(conn,commendNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//비추천 도르마무
	public int replyHateCancel(int hateNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().replyHateCancel(conn,hateNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//비추천 (우~~~)
	public int replyHate(int hateNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().replyHate(conn,hateNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//게시글 총 갯수 불러오기
	public int listCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().listCount(conn);
		
		JDBCTemplate.close(conn);
		return listCount;
	}

	// 검색 (제목으로)
	public ArrayList<Board> searchTitle(String sc, BoardPagingBar bp) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> searchedList = new BoardDao().searchTitle(conn,sc,bp);
		
		JDBCTemplate.close(conn);
		return searchedList;
	}

	//검색 (내용으로)
	public ArrayList<Board> searchContent(String sc, BoardPagingBar bp) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> searchedList = new BoardDao().searchContent(conn,sc,bp);
		
		JDBCTemplate.close(conn);
		return searchedList;
	}

	//검색 (아이디로)
	public ArrayList<Board> searchUserId(String sc, BoardPagingBar bp) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> searchedList = new BoardDao().searchUserId(conn,sc,bp);
		
		JDBCTemplate.close(conn);
		return searchedList;
	}

	// ============ 게시글 카테고리별 정렬 ===========
	public ArrayList<Board> orderByCategory(int categoryNo, BoardPagingBar bp) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> bList = new BoardDao().orderByCategory(conn,categoryNo,bp);
		
		JDBCTemplate.close(conn);
		return bList;
	}

	// ============ 카테고리 게시글 갯수 가져오기 ===========
	public int cListCount(int categoryNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().cListCount(conn,categoryNo);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	// ============ 검색할 게시글 갯수 가져오기 ===========
	public int sListCount(int searchOption) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().sListCount(conn,searchOption);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	// =========== 메인페이지 첫 로드시 가져올 정보 =============
	public ArrayList<Board> indexFirstLoad() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = new BoardDao().indexFirstLoad(conn);
		JDBCTemplate.close(conn);
		return list;
	}

//	// =========== 메인페이지 첫 로드시 가져올 음악정보 =============
//	public String musicInfo() {
//		Connection conn = JDBCTemplate.getConnection();
//		String music = new BoardDao().musicInfo(conn);
//		JDBCTemplate.close(conn);
//		return music;
//	}

	

	
	
	



		
}
