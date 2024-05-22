package com.kh.tour.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.tour.model.dao.TourDao;
import com.kh.tour.model.vo.TourBoard;
import com.kh.tour.model.vo.TourPhoto;

public class TourService {

	//사진게시글 작성메소드
			public int insertThumbnail(TourBoard b, ArrayList<TourPhoto> tpList) {
				Connection conn = JDBCTemplate.getConnection();
				
				//게시글 번호 먼저 추출하기 (해당 번호를 첨부파일이 참조해야하기때문에)  
				int boardNo = new TourDao().selectBoardNo(conn);
				
				//게시글 등록 
				b.setBoardNo(boardNo); //게시글번호 넣어주기 
				int result = new TourDao().insertThumbnail(conn, b);
						
				//사진 등록(파일처리) 
				//참조할 게시글 번호 추가해주기 
				int result2 = new TourDao().insertTourPhotoList(conn,tpList,boardNo);
				

				//트랜잭션처리하기
				if(result*result2>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
				
				JDBCTemplate.close(conn);
				
				return result*result2;
			}
			public ArrayList<TourBoard> selectThumbnailList() {
				Connection conn = JDBCTemplate.getConnection();
				
				ArrayList<TourBoard> list = new TourDao().selectThumbnailList(conn);
				
				//select니까 자원반납만 
				JDBCTemplate.close(conn);
				
				return list;
			
			}
	
	
			//조회수 증가메소드
			public int increaseCount(int bno) {
				Connection conn = JDBCTemplate.getConnection();
				
				int result = new TourDao().increaseCount(conn,bno);
				
				//DML- UPDATE 구문이 수행되었으니 트랜잭션 처리 필수
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
				//트랜잭션 처리되었으니 자원반납
				JDBCTemplate.close(conn);
				
				return result;
			}
			//게시글 상세조회
			public TourBoard selectBoard(int bno) {
				Connection conn = JDBCTemplate.getConnection();
				
				TourBoard b = new TourDao().selectBoard(conn,bno);
				//select구문이니 트랜잭션 처리필요없음 
				JDBCTemplate.close(conn);
				
				return b;
			}
			
			//사진게시글 상세보기 사진목록 조회 메소드 
			public ArrayList<TourPhoto> selectAttachmentList(int boardNo) {
				
				Connection conn = JDBCTemplate.getConnection();
				
				ArrayList<TourPhoto> tpList = new TourDao().selectAttachmentList(conn, boardNo);
				
				JDBCTemplate.close(conn);
				
				return tpList;
			}
			public TourPhoto selectAttachmentList1(int boardNo) {
				
				Connection conn = JDBCTemplate.getConnection();
				
				TourPhoto tp = new TourDao().selectAttachmentList1(conn,boardNo);
				
				JDBCTemplate.close(conn);
				return tp;
			}
			
			//================== 메인페이지 로드 ==============
			public ArrayList<TourBoard> selectTour() {
				Connection conn = JDBCTemplate.getConnection();
				
				ArrayList<TourBoard> list = new TourDao().selectTour(conn);
				
				//select니까 자원반납만 
				JDBCTemplate.close(conn);
				
				return list;
			}
}
