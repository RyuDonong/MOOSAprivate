package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
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
	public int updateMember(Connection conn, Member updateM) {
		
		String sql = prop.getProperty("updateMember");
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,updateM.getPhone());
			pstmt.setString(2,updateM.getEmail());
			pstmt.setString(3,updateM.getAddress());
			pstmt.setString(4,updateM.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//프로필 사진 수정 메소드
	public int updateProfile(Connection conn, Member updateM, Photo p) {
		int result = 0;
		String sql = prop.getProperty("updateProfile");
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,p.getOriginName());
			pstmt.setString(2, p.getChangeName());
			pstmt.setString(3, p.getFilePath());
			pstmt.setString(4, updateM.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
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
	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//회원 아이디로 회원 조회하기
	public Member selectMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMember");
		ResultSet rset = null;
		Member updateMember = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				updateMember = new Member(rset.getInt("USER_NO")
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
		return updateMember;
	}
	//회원 탈퇴 메소드 (status를 N으로 바꾸기)
	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		String sql=prop.getProperty("deleteMember");
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
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
		public int insertProfile(Connection conn, Member updateM, Photo p) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertProfile");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,p.getOriginName());
				pstmt.setString(2, p.getChangeName());
				pstmt.setString(3, p.getFilePath());
				pstmt.setInt(4, p.getFileLevel());
				pstmt.setString(5, updateM.getUserId());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//마이페이지에서 나의 리뷰 조회
		public ArrayList<Review> selectMyReview(Connection conn, int userNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectMyReview");
			ArrayList<Review> rList = new ArrayList<>();
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Review r = new Review(rset.getInt("REVIEW_NO")
										 ,rset.getString("REVIEW_CONTENT")
										 ,rset.getDate("CREATE_DATE")
										 ,rset.getString("ROOM_NO")
										 ,rset.getInt("COUNT"));
					rList.add(r);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return rList;
		}
		//마이페이지에서 리뷰 사진 조회
		public ArrayList<Photo> selectMyReviewPhoto(Connection conn, int userNo) {
			ArrayList<Photo> pList = new ArrayList<>();
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectMyReviewPhoto");
			ResultSet rset = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
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
		//마이페이지에서 리뷰 글 수정을 위해 조회
		public Review selectReview(Connection conn, int reviewNo) {
			Review r = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectReview");
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, reviewNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					r = new Review(rset.getInt("REVIEW_NO")
								  ,rset.getString("REVIEW_CONTENT")
								  ,rset.getDate("CREATE_DATE")
								  ,rset.getString("ROOM_NO")
								  ,rset.getInt("COUNT"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
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
		public int updateReview(Connection conn, Review r) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateReview");
			int result = 0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, r.getCount());
				pstmt.setString(2, r.getReviewContent());
				pstmt.setInt(3, r.getReviewNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//마이페이지에서 리뷰 삭제(status N으로 변경)
		public int deleteReview(Connection conn, int reviewNo) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteReview");
			int result = 0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, reviewNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//위시리스트 추가하기
		public int addWishList(Connection conn, int lno, int userNo) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("addWishList");
			int result = 0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, lno);
				pstmt.setInt(2, userNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//마이페이지에서 위시리스트 조회
		public ArrayList<Lodging> selectWishList(Connection conn, int userNo) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectWishList");
			ArrayList<Lodging> list = new ArrayList<>();
			ResultSet rset = null;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,userNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Lodging l = new Lodging(rset.getInt("LOD_NO")
										   ,rset.getString("LOD_NAME")
										   ,rset.getString("LOD_ADDRESS")
										   ,rset.getString("LOD_INFO")
										   ,rset.getString("LOD_CATEGORY_NAME")
										   ,rset.getString("THUMBNAIL"));
					list.add(l);
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
		//마이페이지에서 위시리스트 삭제
		public int deleteWishList(Connection conn, int userNo, String[] deleteWishList) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteWishList");
			int result = 1;//delete문 여러번 사용하니 한번이라도 0이라면 0이 되게 처리
			try {
				for(String s : deleteWishList) {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,Integer.parseInt(s));
					pstmt.setInt(2, userNo);
					result *= pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//숙소 리스트뷰 에서 위시리스트 제거
		public int deleteWishList(Connection conn, int userNo, int lno) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteWishList");
			int result = 0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, lno);
				pstmt.setInt(2, userNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		


	
}
