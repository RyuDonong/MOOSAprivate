package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
import com.kh.common.model.vo.WishList;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.member.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();

	public MemberDao() {
		String filePath = MemberDao.class.getResource("/resources/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//로그인
	public Member loginMember(Connection conn, String userId, String userPwd) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		Member m = null;
		
		String sql = prop.getProperty("loginMember");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO")
							  ,rset.getString("USER_ID")
							  ,rset.getString("USER_PWD")
							  ,rset.getString("USER_NAME")
							  ,rset.getString("PHONE")
							  ,rset.getString("EMAIL")
							  ,rset.getString("ADDRESS")
							  ,rset.getDate("ENROLL_DATE")
							  ,rset.getDate("MODIFY_DATE")
							  ,rset.getString("STATUS")
							  ,rset.getString("GENDER")
							  ,rset.getInt("GRADE_NO")
							  ,rset.getInt("PHOTO_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
	}
	//회원 정보 수정 메소드
	public int updateMember(SqlSession sqlSession, Member updateM) {
		
		return sqlSession.update("memberMapper.updateMember", updateM);
	}
	//프로필 사진 수정 메소드
	public int updateProfile(SqlSession sqlSession, Member updateM, Photo p) {
		
		p.setUserId(updateM.getUserId());//유저 정보 담아주기
		return sqlSession.update("memberMapper.updateProfile",p);
	}
	//세션에 프로필 사진 담기 위한 조회 메소드
	public Photo selectProfile(Connection conn, String userId) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectProfile");
		Photo profile=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				profile = new Photo(rset.getString("THUMBNAIL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return profile;
	}
	//비밀번호 수정 메소드
	public int updatePwd(SqlSession sqlSession, Member m) {
		
		return sqlSession.update("memberMapper.updatePwd", m);
	}
	//회원 아이디로 회원 조회하기
	public Member selectMember(SqlSession sqlSession, String userId) {
		
		return sqlSession.selectOne("memberMapper.selectMember", userId);
	}
	//회원 탈퇴 메소드 (status를 N으로 바꾸기)
	public int deleteMember(SqlSession sqlSession, String userId) {
		
		return sqlSession.delete("memberMapper.deleteMember", userId);
	}
    //회원가입 메소드
		public int insertMember(Connection conn,Member m) {
			
			PreparedStatement pstmt = null; 
			int result = 0; 
			String sql = prop.getProperty("insertMember");
			try {
				pstmt = conn.prepareStatement(sql);
			 	
				pstmt.setString(1, m.getUserId());
				pstmt.setString(2, m.getUserPwd());
				pstmt.setString(3, m.getUserName());
				pstmt.setString(4, m.getPhone());
				pstmt.setString(5, m.getEmail());
				pstmt.setString(6, m.getAddress());	
				pstmt.setString(7, m.getGender());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		//아이디 중복확인 메소드 
		public boolean checkId(Connection conn, String inputId) {
			
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("checkId");
			
			boolean flag = false;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, inputId);
				
				rset = pstmt.executeQuery();
				flag = rset.next();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return flag; 
		}
		//기존 프로필 사진이 없는경우 프로필 사진 넣기
		public int insertProfile(SqlSession sqlSession, Member updateM, Photo p) {
			
			p.setUserId(updateM.getUserId());
			return sqlSession.insert("memberMapper.insertProfile", p);
		}
		//마이페이지에서 나의 리뷰 조회
		public ArrayList<Review> selectMyReview(SqlSession sqlSession, int userNo) {
			
			return (ArrayList)sqlSession.selectList("memberMapper.selectMyReview", userNo);
		}
		//마이페이지에서 리뷰 사진 조회
		public ArrayList<Photo> selectMyReviewPhoto(SqlSession sqlSession, int userNo) {
			
			return (ArrayList)sqlSession.selectList("memberMapper.selectMyReviewPhoto", userNo);
		}
		//마이페이지에서 리뷰 글 수정을 위해 조회
		public Review selectReview(SqlSession sqlSession, int reviewNo) {
			
			return sqlSession.selectOne("memberMapper.selectReview", reviewNo);
		}
		//마이페이지에서 리뷰 사진 수정을 위한 조회
		public ArrayList<Photo> selectReviewPhoto(Connection conn, int reviewNo) {
			ArrayList<Photo> pList = new ArrayList<>();
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectReviewPhoto");
			ResultSet rset = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, reviewNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Photo p = new Photo(rset.getInt("PHOTO_NO")
							 		   ,rset.getString("ORIGIN_NAME")
							 		   ,rset.getString("CHANGE_NAME")
							 		   ,rset.getString("THUMBNAIL")
							 		   ,rset.getInt("REVIEW_NO"));
					pList.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return pList;
		}
		//마이페이지에서 리뷰 수정
		public int updateReview(SqlSession sqlSession, Review r) {
			
			return sqlSession.update("memberMapper.updateReview", r);
		}
		//마이페이지에서 리뷰 삭제(status N으로 변경)
		public int deleteReview(SqlSession sqlSession, int reviewNo) {
			
			return sqlSession.update("memberMapper.deleteReview", reviewNo);
		}
		
		//위시리스트 추가하기
		public int addWishList(SqlSession sqlSession, WishList wishList) {
			
			return sqlSession.insert("memberMapper.addWishList", wishList);
		}
		
		//마이페이지에서 위시리스트 조회
		public ArrayList<Lodging> selectWishList(SqlSession sqlSession, int userNo) {
			
			return (ArrayList)sqlSession.selectList("memberMapper.selectWishList", userNo);
		}
		
		//마이페이지에서 위시리스트 삭제
		public int deleteWishList(SqlSession sqlSession, int userNo, String[] deleteWishList) {
			int result = 1;//delete문 여러번 사용하니 한번이라도 0이라면 0이 되게 처리
				for(String s : deleteWishList) {
					WishList wishList = new WishList(Integer.parseInt(s),userNo);
					result *= sqlSession.delete("memberMapper.deleteWishList", wishList);
				}
			return result;
		}
		
		//숙소 리스트뷰 에서 위시리스트 제거
		public int deleteWishList(SqlSession sqlSession,WishList wishList) {
			
			return sqlSession.delete("memberMapper.deleteWishList", wishList);
		}
		


	
}
