package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardReply;
import com.kh.board.model.vo.Category;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.BoardPagingBar;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		String filePath = BoardDao.class.getResource("/resources/sql/board-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =========  게시글 리스트 전부 가져오기  ========= 
	
	public ArrayList<Board> boardList(Connection conn, BoardPagingBar bp) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("boardList");
		
		//(현재페이지 -1) * 한페이지에 보여줄 게시글수(3) +1  ==> 1 , 4  ,7
		int startRow = (bp.getCurrentPage()-1)* bp.getBoardLimit() +1;
		// 현재페이지 * 한페이지에 보여줄 게시글수(3) ==> 3, 6, 9
		int endRow = bp.getCurrentPage() * bp.getBoardLimit();
		
		
		System.out.println(startRow);
		System.out.println(endRow);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// ========= 게시글 조회수 증가 ========= 
	public int boardCount(Connection conn, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("boardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 작성 페이지에서 option에 넣을 카테고리 종류 가져오기 ========= 
	
	public ArrayList<Category> boardInsert(Connection conn) {
		
		ArrayList<Category> caList = new ArrayList<>();
		ResultSet rset = null;
		Statement stmt = null;
		String sql = prop.getProperty("boardInsert");

		try {
			stmt=conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				caList.add(new Category(
							rset.getString("CATEGORY_NO")
							,rset.getString("CATEGORY_NAME")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return caList;
	}
	
	// =========  게시글 번호 추출해오기  ========= 
	
	public int selectBoardNo(Connection conn) {
		
		int boardNo = 0;
		ResultSet rset = null;
		Statement stmt = null;
		String sql = prop.getProperty("selectBoardNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				boardNo = rset.getInt("BNO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardNo;
	}

	// ========= 게시글 제목 및 내용만 넣기 ========= 
	
	public int contentInsert(Connection conn, Board b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("contentInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBoardNo());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardWriter());
			pstmt.setString(5, b.getCategoryName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// ========= 게시글 첨부파일 넣기 ========= 
	
	public int atInsert(Connection conn, Attachment at) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("atInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getRefNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// ========= 첨부파일 가져오기 ========= 
	
	public Attachment selectAt(Connection conn, int bno) {
		
		Attachment at = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				at = new Attachment( //파일번호,파일본이름,바뀐이름,경로
							rset.getInt("FILE_NO")
							,rset.getString("FILE_NAME")
							,rset.getString("CHANGE_NAME")
							,rset.getString("FILE_PATH")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return at;
	}
	
	
	
	// ========= 게시글 클릭시 상세보기 ========= 
	
	public Board boardDetail(Connection conn, int bno) {
		
		Board b = new Board();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("boardDetail");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				b = new Board(
						rset.getInt("BOARD_NO")
					   ,rset.getString("BOARD_TITLE")
					   ,rset.getString("BOARD_CONTENT")
					   ,rset.getInt("COUNT")
					   ,rset.getInt("LIKES")
					   ,rset.getDate("CREATE_DATE")
					   ,rset.getString("USER_ID")
					   ,rset.getString("CATEGORY_NAME")
					   );
//							b.setCategoryName(rset.getString("CATEGORY_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	// ========= 게시글정보 가져오기  ========= 
	
	public Board selectBoard(Connection conn, int bno) {
		
		Board b = new Board();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("boardDetail");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				b = new Board(
						rset.getInt("BOARD_NO")
					   ,rset.getString("BOARD_TITLE")
					   ,rset.getString("BOARD_CONTENT")
					   ,rset.getDate("CREATE_DATE")
					   ,rset.getString("USER_ID")
					   ,rset.getString("CATEGORY_NAME")
					   );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}
	
	
	// ========= 게시글 수정 ========= 
	
	public int updateContent(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateContent");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getCategoryNo());
			pstmt.setInt(4, b.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// ========= 첨부파일 업데이트 (기존에 첨부파일 있을경우) ========= 
	
	public int updateAt(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAt");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 글삭제  ========= 
	
	public int deleteBoard(Connection conn, int bno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// ========= 게시글 좋아요 취소 ========= 
	
	public int likesCancel(Connection conn, int likeBno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likesCancel");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, likeBno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
		
	// ========= 게시글 좋아요 업데이트  ========= 
	public int likesUpdate(Connection conn, int likeBno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likesUpdate");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, likeBno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
		
	
	// ========= 댓글정보 ========= 
	
		public ArrayList<BoardReply> selectReply(Connection conn, int bno) {
			
			ArrayList<BoardReply> br = new ArrayList<>();
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectReply");
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					br.add(new BoardReply(
							rset.getInt("REPLY_NO")
							,rset.getString("REPLY_CONTENT")
							,rset.getDate("CREATE_DATE")
							,rset.getInt("REPLY_WRITER")
							,rset.getString("USER_ID")
							,rset.getInt("RECOMMEND")
							,rset.getInt("HATE")
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return br;
		}

	// ========= 댓글입력 ========= 
		
	public int insertReply(Connection conn, String replyContent, int bno, int userNo) {
	
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("insertReply");
	try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, replyContent);
		pstmt.setInt(2, userNo);
		pstmt.setInt(3, bno);
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(pstmt);
	}
	return result;
	}

	// ========= 댓글삭제 ========= 
	
	public int replyDelete(Connection conn, int replyNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyDelete");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 따봉 도르마무!!  ========= 
	public int replyCommendCancel(Connection conn, int commendNo) {
		
		System.out.println("따봉 도르마무 dao: "+commendNo);
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyCommendCancel");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, commendNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 추천(따봉) ========= 
	
	public int replyCommend(Connection conn, int commendNo) {
		
		System.out.println("따봉 dao: "+commendNo);
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyCommend");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commendNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	
	// ========= 비추천 도르마무!!  ========= 
	
	public int replyHateCancel(Connection conn, int hateNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyHateCancel");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, hateNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 비추천 (우~~~) ========= 
	
	public int replyHate(Connection conn, int hateNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyHate");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, hateNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// ========= 게시글 총 갯수 불러오기  ========= 
	
	public int listCount(Connection conn) {
		
		int listCount = 0;
		ResultSet rset = null;
		Statement stmt = null;
		String sql = prop.getProperty("listCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return listCount;
	}

	// ========= 검색 (제목으로) ========= 
	
	public ArrayList<Board> searchTitle(Connection conn, String sc, BoardPagingBar bp) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchTitle");
		
		//(현재페이지 -1) * 한페이지에 보여줄 게시글수(3) +1  ==> 1 , 4  ,7
		int startRow = (bp.getCurrentPage()-1)* bp.getBoardLimit() +1;
		// 현재페이지 * 한페이지에 보여줄 게시글수(3) ==> 3, 6, 9
		int endRow = bp.getCurrentPage() * bp.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sc+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	// ========= 검색 (내용으로) ========= 
	
	public ArrayList<Board> searchContent(Connection conn, String sc, BoardPagingBar bp) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchContent");
		
		//(현재페이지 -1) * 한페이지에 보여줄 게시글수(3) +1  ==> 1 , 4  ,7
		int startRow = (bp.getCurrentPage()-1)* bp.getBoardLimit() +1;
		// 현재페이지 * 한페이지에 보여줄 게시글수(3) ==> 3, 6, 9
		int endRow = bp.getCurrentPage() * bp.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sc+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// ========= 검색 (아이디로 검색) ========= 
	
	public ArrayList<Board> searchUserId(Connection conn, String sc, BoardPagingBar bp) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchUserId");
		
		//(현재페이지 -1) * 한페이지에 보여줄 게시글수(3) +1  ==> 1 , 4  ,7
		int startRow = (bp.getCurrentPage()-1)* bp.getBoardLimit() +1;
		// 현재페이지 * 한페이지에 보여줄 게시글수(3) ==> 3, 6, 9
		int endRow = bp.getCurrentPage() * bp.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sc);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	

	// ========= 게시판 카테고리별 정렬 =========  
	public ArrayList<Board> orderByCategory(Connection conn, int categoryNo, BoardPagingBar bp) {
		
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("orderByCategory");
		
		//(현재페이지 -1) * 한페이지에 보여줄 게시글수(3) +1  ==> 1 , 4  ,7
		// 현재페이지 * 한페이지에 보여줄 게시글수(3) ==> 3, 6, 9
		int startRow = (bp.getCurrentPage()-1)* bp.getBoardLimit() +1;
		int endRow = bp.getCurrentPage() * bp.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// ============ 카테고리 게시글 갯수 가져오기 ===========
	public int cListCount(Connection conn, int categoryNo) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("cListCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}

	// ============ 검색할 게시글 갯수 가져오기 ===========
	public int sListCount(Connection conn, int searchOption) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("sListCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, searchOption);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}

	// ============ 메인페이지 첫 로드시 가져올 정보 ===========
	public ArrayList<Board> indexFirstLoad(Connection conn) {
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("indexFirstLoad");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						     rset.getInt("BOARD_NO")
							,rset.getString("BOARD_TITLE")
							,rset.getString("BOARD_CONTENT")
							,rset.getInt("COUNT")
							,rset.getInt("LIKES")
							,rset.getDate("CREATE_DATE")
							,rset.getString("USER_ID")
							,rset.getString("BOARD_WRITER")
							,rset.getString("CATEGORY_NAME")
							,rset.getInt("COUNT_RANK")
							,rset.getString("FILE_PATH")
							,rset.getString("CHANGE_NAME")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// =====배경음악======= 메인페이지 첫 로드시 가져올 정보 ===========
//	public String musicInfo(Connection conn) {
//		
//		String music = "";
//		ResultSet rset = null;
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("musicInfo");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			if(rset.next()) {
//				music =  rset.getString("FILE_NAME");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return music;
//	}

}


















