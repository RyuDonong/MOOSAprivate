package com.kh.course.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.course.model.vo.Attachment;
import com.kh.course.model.vo.Board;
import com.kh.course.model.vo.Category;
import com.kh.course.model.vo.Reply;

public class BoardDao {

	private Properties prop = new Properties();

	public BoardDao() {

		String filePath = BoardDao.class.getResource("/resources/sql/reviewboard-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//게시글 총 개수 
	public int listCount(Connection conn) {
		ResultSet rset = null;
		Statement stmt = null;
		String sql = prop.getProperty("listCount");
		
		int listCount = 0;
		
		
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

		public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
			
			
			ArrayList<Board> list = new ArrayList<>();
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("selectList");
			
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
			int endRow = pi.getCurrentPage() * pi.getBoardLimit();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow); 
				pstmt.setInt(2, endRow); 
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					list.add(new Board(rset.getDate("CREATE_DATE")
							  ,rset.getString("USER_ID")
							  ,rset.getString("CATEGORY_NAME")
							  ,rset.getInt("BOARD_NO")
							  ,rset.getInt("COUNT")
							  ,rset.getString("BOARD_TITLE")));
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
		public ArrayList<Category> selectCategoryList(Connection conn) {
			
			ResultSet rset = null;
			ArrayList<Category> cList = new ArrayList<>();
			Statement stmt = null;
			
			String sql = prop.getProperty("selectCategoryList");
			
			try {
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(sql);
				
				while(rset.next()) {
					cList.add(new Category(rset.getInt("CATEGORY_NO")
										  ,rset.getString("CATEGORY_NAME")));		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
			}
			return cList;
		}
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
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
			}
			
			return boardNo;
		}
		public int insertBoard(Connection conn, Board b) {

			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertBoard");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b.getBoardWriter());
				pstmt.setString(2, b.getCategory());
				pstmt.setInt(3, b.getBoardNo());
				pstmt.setString(4, b.getBoardTitle());
				pstmt.setString(5, b.getBoardContent());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		public int insertAttachment(Connection conn, Attachment at) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, at.getRefno());
				pstmt.setString(2, at.getFileName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		public int increaseCount(Connection conn, int bno) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("increaseCount");
			
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
		public Board selectBoard(Connection conn, int bno) {
			Board b = null; //게시글정보담을 객체변수
			ResultSet rset = null; 
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectBoard");
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					b = new Board(rset.getDate("CREATE_DATE")
							  ,rset.getString("USER_ID")
							  ,rset.getString("CATEGORY_NAME")
							  ,rset.getInt("BOARD_NO")
							  ,rset.getString("BOARD_TITLE")
							  ,rset.getString("BOARD_CONTENT"));
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
		
		public Attachment selectAttachment(Connection conn, int bno) {
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			Attachment at = null; 
			String sql = prop.getProperty("selectAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					at = new Attachment(rset.getInt("FILE_NO")
									   ,rset.getString("FILE_NAME")
									   ,rset.getString("CHANGE_NAME")
									   ,rset.getString("FILE_PATH"));
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
		public int updateBoard(Connection conn, Board b) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateBoard");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b.getBoardTitle());
				pstmt.setString(2, b.getBoardContent());
				pstmt.setString(3, b.getCategory());
				pstmt.setInt(4, b.getBoardNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		public int updateAttachment(Connection conn, Attachment at) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getFileName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileNo());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		public int deleteBoard(Connection conn, int boardNo) {
			
			int result=0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteBoard");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		public int insertReply(Connection conn, Reply r) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertReply");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, r.getReplyContent());
				pstmt.setInt(2, r.getRefBno());
				pstmt.setString(3, r.getReplyWriter());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		public ArrayList<Reply> replyList(Connection conn, int refBno) {
			
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			ArrayList<Reply> list = new ArrayList<>();
			
			String sql = prop.getProperty("replyList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, refBno);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Reply(rset.getInt("REPLY_NO")
									  ,rset.getString("REPLY_CONTENT")
									  ,rset.getString("USER_ID")
									  ,rset.getDate("CREATE_DATE")));
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
		
		//=============== 메인페이지에 불러올 리뷰 게시판 ==========
		public ArrayList<Board> selectReviewList(Connection conn) {
			
			ArrayList<Board> list = new ArrayList<>();
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectReviewList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					list.add(new Board(
								 rset.getString("BOARD_TITLE")
								,rset.getString("BOARD_CONTENT")
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

}
