package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
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

	//프로필 사진 세션에 담기 위해 조회해오기
	public Photo selectProfile(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		Photo profile = new MemberDao().selectProfile(conn,userId);
		JDBCTemplate.close(conn);
		return profile;
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
	//마이페이지에서 리뷰 수정을 위해 사진 조회
	public ArrayList<Photo> selectReviewPhoto(int reviewNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Photo> pList = new MemberDao().selectReviewPhoto(conn, reviewNo);
		JDBCTemplate.close(conn);
		return pList;
	}
}

	

