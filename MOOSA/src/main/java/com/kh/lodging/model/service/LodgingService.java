package com.kh.lodging.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.dao.LodgingDao;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.lodging.model.vo.Room;

public class LodgingService {

	//비동기통신 숙소 리스트 조회
	public ArrayList<Lodging> selectLodgingList(String category) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Lodging> list = new LodgingDao().selectLodgingList(conn,category);
		JDBCTemplate.close(conn);
		
		return list;
	}
	//숙소 상세페이지 조회
	public Lodging selectDetailLodging(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		Lodging lod = new LodgingDao().selectDetailLodging(conn,lno);
		JDBCTemplate.close(conn);
		return lod;
	}
	//숙소 방 정보 조회
	public ArrayList<Room> selectRoom(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Room> rList = new LodgingDao().selectRoom(conn,lno);
		JDBCTemplate.close(conn);
		return rList;
	}
// 	리뷰 조회 메소드
	public ArrayList<Review> selectEveryReview(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> list = new LodgingDao().selectEveryReview(conn,lno);
		JDBCTemplate.close(conn);
		return list;
		
	}
	//리뷰 작성 메소드(insert)
	public int insertReview(Review r, ArrayList<Photo> pList,int lno) {
		Connection conn = JDBCTemplate.getConnection();
		//reviewNo를 뽑아오기
		int reviewNo = new LodgingDao().selectReviewNo(conn);
		r.setReviewNo(reviewNo); //뽑아온 시퀀스 reviewNo 넣어주기
		int result = new LodgingDao().insertReview(conn,r);
		int result2=1; //첨부파일이 없더라도 수행될수 있도록 1로 만들기
		if(result>0&&pList!=null) {
			//리뷰글이 등록 되고 리뷰사진이 있을경우 
			//등록할 리뷰 번호와 함께 등록하기
			result2=new LodgingDao().insertReviewPhoto(conn,pList,reviewNo,lno);
		}
		if(result*result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result*result2;
	}
	
	//숙소 디테일 뷰에서 보여줄 방 사진 조회
	public ArrayList<Photo> selectRoomPhoto(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Photo> rpList = new LodgingDao().selectRoomPhoto(conn,lno);
		JDBCTemplate.close(conn);
		return rpList;
	}
	
	//========== 메인페이지 로드 =================
	public ArrayList<Lodging> selectLodgingMain() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Lodging> rList = new LodgingDao().selectLodgingMain(conn);
		JDBCTemplate.close(conn);
		return rList;
	}
	
	

	
	
}
