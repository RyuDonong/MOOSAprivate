package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
import com.kh.common.model.vo.Template;
import com.kh.common.model.vo.WishList;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemServiceImpl implements MemService{

	//마이페이지에서 리뷰 삭제
	@Override
	public int deleteReview(int reviewNo) {
		SqlSession sqlSession = Template.getSqlSession();
		int result= new MemberDao().deleteReview(sqlSession,reviewNo);
		sqlSession.close();
		return result;
	}
	
	//위시리스트 조회
	@Override
	public ArrayList<Lodging> selectWishList(int userNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Lodging> list= new MemberDao().selectWishList(sqlSession,userNo);
		sqlSession.close();
		return list;
	}
	//위시리스트 추가(버튼 눌릴때)
	@Override
	public int addWishList(WishList wishList) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().addWishList(sqlSession, wishList);
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}
	
	//위시리스트 삭제(버튼 눌릴떄)
	@Override
	public int deleteWishList(WishList wishList) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().deleteWishList(sqlSession, wishList);
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}
	
	//마이페이지 위시리시트에서 삭제 하기
	@Override
	public int deleteWishList(int userNo, String[] deleteWishList) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().deleteWishList(sqlSession, userNo,deleteWishList);
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}
	
	//마이페이지에서 리뷰 조회
	@Override
	public ArrayList<Review> selectMyReview(int userNo) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Review> list = new MemberDao().selectMyReview(sqlSession,userNo);
		sqlSession.close();
		return list;
	}
	
	//마이페이지에서 리뷰 사진 조회
	@Override
	public ArrayList<Photo> selectMyReviewPhoto(int userNo) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Photo> list = new MemberDao().selectMyReviewPhoto(sqlSession,userNo);
		sqlSession.close();
		return list;
	}
	
	//비밀번호 변경
	@Override
	public int updatePwd(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().updatePwd(sqlSession,m);
		sqlSession.close();
		return result;
	}
	
	//리뷰 수정을 위한 마이페이지 리뷰 조회
	@Override
	public Review selectReview(int reviewNo) {
		SqlSession sqlSession = Template.getSqlSession();
		Review r = new MemberDao().selectReview(sqlSession,reviewNo);
		sqlSession.close();
		return r;
	}
	
	//회원 정보 수정
	@Override
	public int updateMember(Member updateMem, Photo p) {
		int result2=1;
		//프로필 사진 없을 경우 대비
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().updateMember(sqlSession,updateMem);
		// 회원정보가 수정이 됐고 수정하려는 프로필 사진이 있다면 
		if(result>0 && p!=null) {
			//디폴트사진이 아닌 회원이 수정했던 사진이 있다면
			if(updateMem.getPhotoNo()!=1) {
				result2 = new MemberDao().updateProfile(sqlSession,updateMem,p);
			}else {
				//수정했던 사진이 없다면
				result2 = new MemberDao().insertProfile(sqlSession,updateMem,p);
			}
		}
		if(result*result2>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result*result2;
	}
	
	//수정한 정보 갱신을 위한 조회
	@Override
	public Member selectMember(String userId) {
		SqlSession sqlSession = Template.getSqlSession();
		Member m = new MemberDao().selectMember(sqlSession, userId);
		sqlSession.close();
		return m;
	}
	
	//리뷰 수정
	@Override
	public int updateReview(Review r) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().updateReview(sqlSession, r);
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}
	
	//회원 탈퇴
	@Override
	public int deleteMember(String userId) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = new MemberDao().deleteMember(sqlSession, userId);
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}
}

