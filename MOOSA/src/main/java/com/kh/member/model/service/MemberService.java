package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;


public class MemberService {
//로그인
	public Member loginMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn,userId,userPwd);
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	//회원 정보 수정 메소드
	public int updateMember(Member updateM,Photo p) {
		int result2=1;
		//프로필 사진 없을 경우 대비
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,updateM);
		// 회원정보가 수정이 됐고 수정하려는 프로필 사진이 있다면 
		if(result>0 && p!=null) {
			//디폴트사진이 아닌 회원이 수정했던 사진이 있다면
			if(updateM.getPhotoNo()!=1) {
				result2 = new MemberDao().updateProfile(conn,updateM,p);
			}else {
				//수정했던 사진이 없다면
				result2 = new MemberDao().insertProfile(conn,updateM,p);
			}
		}
		if(result*result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result*result2;
	}
	//프로필 사진 세션에 담기 위해 조회해오기
	public Photo selectProfile(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		Photo profile = new MemberDao().selectProfile(conn,userId);
		JDBCTemplate.close(conn);
		return profile;
	}
	//모달 창으로 비밀번호 변경 메소드
	public int updatePwd(String userId, String userPwd, String updatePwd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updatePwd(conn,userId,userPwd,updatePwd);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//정보 수정후 정보 갱신을 위한 회원 정보 조회 메소드
	public Member selectMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		Member updateMember = new MemberDao().selectMember(conn,userId);
		JDBCTemplate.close(conn);
		return updateMember;
	}

	public int deleteMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,userId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

    // 회원가입 메소드
	public int insertMember(Member m) {

		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

}
	 //아이디 중복체크 메소드
   	public Boolean checkId(String inputId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Boolean flag = new MemberDao().checkId(conn,inputId);
		
		JDBCTemplate.close(conn);
		
		return flag;
		
	}
   	//마이페이지에서 리뷰 조회
	public ArrayList<Review> selectMyReview(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> rList = new MemberDao().selectMyReview(conn,userNo);
		JDBCTemplate.close(conn);
		return rList;
	}
	//마이페이지에서 리뷰 사진 조회
	public ArrayList<Photo> selectMyReviewPhoto(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Photo> pList = new MemberDao().selectMyReviewPhoto(conn, userNo);
		JDBCTemplate.close(conn);
		return pList;
	}
	//마이페이지에서 리뷰 수정을 위해 리뷰글 조회
	public Review selectReview(int reviewNo) {
		Connection conn = JDBCTemplate.getConnection();
		Review r = new MemberDao().selectReview(conn,reviewNo);
		JDBCTemplate.close(conn);
		return r;
	}
	//마이페이지에서 리뷰 수정을 위해 
	public ArrayList<Photo> selectReviewPhoto(int reviewNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Photo> pList = new MemberDao().selectReviewPhoto(conn, reviewNo);
		JDBCTemplate.close(conn);
		return pList;
	}
	//마이페이지에서 리뷰 수정
	public int updateReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateReview(conn,r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//마이페이지에서 리뷰 삭제 (status N으로 변경)
	public int deleteReview(int reviewNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteReview(conn,reviewNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//위시리스트 버튼 눌렀을때 위시리스트 등록
	public int addWishList(int lno, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().addWishList(conn,lno,userNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//마이페이지에서 위시리스트 조회
	public ArrayList<Lodging> selectWishList(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Lodging> list = new MemberDao().selectWishList(conn,userNo);
		JDBCTemplate.close(conn);
		return list;
	}
	//마이페이지에서 위시리스트 제거 
	public int deleteWishList(int userNo, String[] deleteWishList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteWishList(conn,userNo,deleteWishList);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//숙소 리스트뷰에서 숙소 위시리스트 제거
	public int deleteWishList(int userNo, int lno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteWishList(conn, userNo, lno);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}

	

