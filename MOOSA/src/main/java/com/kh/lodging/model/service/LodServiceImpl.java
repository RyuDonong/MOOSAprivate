package com.kh.lodging.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.model.vo.Photo;
import com.kh.common.model.vo.Template;
import com.kh.lodging.model.dao.LodgingDao;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.lodging.model.vo.Room;

public class LodServiceImpl implements LodService{

	@Override
	//비동기 통신 숙소 리스트 조회
	public ArrayList<Lodging> selectLodgingList(String category) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Lodging> list = new LodgingDao().selectLodgingList(sqlSession,category);
		sqlSession.close();
		return list;
	}
	
	@Override
	//숙소 상세페이지 정보 조회 
	public Lodging selectDetailLodging(int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		Lodging lod = new LodgingDao().selectDetailLodging(sqlSession,lno);
		sqlSession.close();
		return lod;
	}
	
	@Override
	//숙소 방 정보 조회
	public ArrayList<Room> selectRoom(int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Room> rList = new LodgingDao().selectRoom(sqlSession,lno);
		sqlSession.close();
		return rList;
	}
	
	@Override
	//숙소 리뷰 조회
	public ArrayList<Review> selectEveryReview(int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Review> list = new LodgingDao().selectEveryReview(sqlSession,lno);
		sqlSession.close();
		return list;
	}
	
	@Override
	//숙소 방 사진 조회
	public ArrayList<Photo> selectRoomPhoto(int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Photo> rpList = new LodgingDao().selectRoomPhoto(sqlSession,lno);
		sqlSession.close();
		return rpList;
	}
	
	@Override
	//숙소 리뷰 사진 조회
	public ArrayList<Photo> selectReviewPhoto(int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Photo> pList = new LodgingDao().selectReviewPhoto(sqlSession,lno);
		sqlSession.close();
		return pList;
	}

	@Override
	public int insertReview(Review r, ArrayList<Photo> pList, int lno) {
		SqlSession sqlSession = Template.getSqlSession();
		//reviewNo를 뽑아오기
		int reviewNo = new LodgingDao().selectReviewNo(sqlSession);
		r.setReviewNo(reviewNo); //뽑아온 시퀀스 reviewNo 넣어주기
		int result = new LodgingDao().insertReview(sqlSession,r);
		int result2=1; //첨부파일이 없더라도 수행될수 있도록 1로 만들기
		if(result>0&&pList!=null) {
			//리뷰글이 등록 되고 리뷰사진이 있을경우 
			//등록할 리뷰 번호와 함께 등록하기
			result2=new LodgingDao().insertReviewPhoto(sqlSession,pList,reviewNo,lno);
		}
		if(result*result2>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result*result2;
	}
}
