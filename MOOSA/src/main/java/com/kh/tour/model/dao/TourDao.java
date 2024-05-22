package com.kh.tour.model.dao;

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
import com.kh.tour.model.vo.TourBoard;
import com.kh.tour.model.vo.TourPhoto;

public class TourDao {
	

	private Properties prop = new Properties();
	
public TourDao() {
		
		String filePath = TourDao.class.getResource("/resources/sql/tour-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	public int selectBoardNo(Connection conn) {
		// select 구문으로 시퀀스 발행시키기
		int boardNo = 0;
		ResultSet rset = null;
		Statement stmt = null;

		String sql = prop.getProperty("selectBoardNo");

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				boardNo = rset.getInt("BNO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}

		return boardNo;
	}

	// 사진게시글 작성
	public int insertThumbnail(Connection conn, TourBoard b) {
		// DML - insert
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertThumbnail");

		try {
			pstmt = conn.prepareStatement(sql);;
			pstmt.setInt(1,b.getBoardNo());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardWriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 파일 목록처리 (등록메소드)
	public int insertTourPhotoList(Connection conn, ArrayList<TourPhoto> tpList, int boardNo) {
		// 파일 목록에 대해서 모두 등록처리하기
		int result = 1; // insert문을 여러번 사용(하나라도 0이면 0이되게 처리)

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAttachmentList");

		try {
			// 전부 넣어야하니 향상된 for문 이용하기
			for (TourPhoto tp : tpList) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				pstmt.setString(2, tp.getOriginName());
				pstmt.setString(3, tp.getChangeName());
				pstmt.setString(4, tp.getFilePath());

				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			result = 0; // 처음부터 문제가 생겼다면 0으로 바꿀수있도록 처리
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	//사진게시글 목록 조회메소드
		public ArrayList<TourBoard> selectThumbnailList(Connection conn) {
			//select 
			ResultSet rset = null;
			ArrayList<TourBoard> list = new ArrayList<>();
			Statement stmt = null;
			
			String sql = prop.getProperty("selectThumbnailList");
					
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);
				
				while(rset.next()) {
					list.add(new TourBoard(rset.getInt("TOURBOARD_NO")
							          ,rset.getString("TOURBOARD_TITLE")
							          ,rset.getInt("TOUR_COUNT")
							          ,rset.getString("TOURBOARD_CONTENT")
									  ,rset.getString("THUMBNAIL")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
			}
			return list;
		}
		
		//조회수 증가메소드
		public int increaseCount(Connection conn, int bno) {
			
			//dml
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("increaseCount");
			
			try {
				//미완성 sql문 전달하며 pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				//위치홀더 채워서 sql구문 완성시키기
				pstmt.setInt(1, bno);
				
				//완성되었으니 실행 및 결과받기
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//게시글 상세조회
		public TourBoard selectBoard(Connection conn, int bno) {
			
			TourBoard b = null; //게시글정보담을 객체변수
			ResultSet rset = null; 
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectBoard");
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					b = new TourBoard(rset.getInt("TOURBOARD_NO")
								  ,rset.getString("TOURBOARD_TITLE")
								  ,rset.getString("TOURBOARD_CONTENT")
								  ,rset.getString("USER_ID")
								  ,rset.getDate("TOURCREATE_DATE"));
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
		//사진게시글 상세보기 첨부파일 목록 조회
		public ArrayList<TourPhoto> selectAttachmentList(Connection conn, int boardNo) {
			//select
			ResultSet rset = null;
			ArrayList<TourPhoto> tpList = new ArrayList<>();
			PreparedStatement pstmt = null;
			//기존에 일반게시판에서 작성했던 첨부파일 정보 조회 구문 사용하기 
			String sql =prop.getProperty("selectAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				//한 게시글에 여러 첨부파일이 존재할수있으니 반복처리 전부 불러오기 
				if(rset.next()) {
					tpList.add(new TourPhoto(rset.getInt("PHOTO_NO")
											 ,rset.getString("ORIGIN_NAME")
											 ,rset.getString("CHANGE_NAME")
											 ,rset.getString("FILE_PATH")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return tpList;
		}




		public TourPhoto selectAttachmentList1(Connection conn, int boardNo) {
			//select
			ResultSet rset = null;
			TourPhoto tp = new TourPhoto();
			PreparedStatement pstmt = null;
			//기존에 일반게시판에서 작성했던 첨부파일 정보 조회 구문 사용하기 
			String sql =prop.getProperty("selectAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				//한 게시글에 여러 첨부파일이 존재할수있으니 반복처리 전부 불러오기 
				if(rset.next()) {
					tp = new TourPhoto(rset.getInt("PHOTO_NO")
											 ,rset.getString("ORIGIN_NAME")
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
			return tp;
		}

		//================== 메인페이지 로드 ==============
		public ArrayList<TourBoard> selectTour(Connection conn) {
			ResultSet rset = null;
			ArrayList<TourBoard> list = new ArrayList<>();
			Statement stmt = null;
			
			String sql = prop.getProperty("selectTour");
					
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);
				
				while(rset.next()) {
					list.add(new TourBoard(
							          rset.getString("TOURBOARD_TITLE")
							          ,rset.getString("TOURBOARD_CONTENT")
							          ,rset.getString("CHANGE_NAME")
							          ,rset.getString("FILE_PATH")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
			}
			return list;
		}
		
		
}
