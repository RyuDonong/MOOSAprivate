package com.kh.lodging.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.lodging.model.vo.Room;


public class LodgingDao {

	private Properties prop = new Properties();

	public LodgingDao() {
		String filePath = LodgingDao.class.getResource("/resources/sql/lodging-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//숙소 카테고리별 조회 
	public ArrayList<Lodging> selectLodgingList(SqlSession sqlSession,String category) {

		ArrayList<Lodging> list = (ArrayList)sqlSession.selectList("lodMapper.selectLodgingList", category);
		return list;
	}

	//숙소 상세 정보 
	public Lodging selectDetailLodging(SqlSession sqlSession, int lno) {
		Lodging lod = sqlSession.selectOne("lodMapper.selectDetailLodging", lno);
		return lod;
	}
	
	//숙소 방 정보 조회
	public ArrayList<Room> selectRoom(SqlSession sqlSession, int lno) {
		return (ArrayList)sqlSession.selectList("lodMapper.selectRoom",lno);
	}
	
	//숙소 리뷰 조회
	public ArrayList<Review> selectEveryReview(SqlSession sqlSession, int lno) {
		return (ArrayList)sqlSession.selectList("lodMapper.selectEveryReview", lno);
	}

	//리뷰 번호 추출 메소드
	public int selectReviewNo(SqlSession sqlSession) {
		
		return sqlSession.selectOne("lodMapper.selectReviewNo");
	}

	//리뷰 글 작성메소드
	public int insertReview(SqlSession sqlSession, Review r) {
		
		return sqlSession.insert("lodMapper.insertReview",r);
	}

	public int insertReviewPhoto(SqlSession sqlSession, ArrayList<Photo> pList, int reviewNo,int lno) {
		int result = 1; //사진이 여러개 왔을때 하나라도 0이라면 0이 되게 처리
			for(Photo p : pList) {
				//사진 객체마다 reviewNo넣어 주고
				p.setReviewNo(reviewNo);
				//사진 객체마다 숙소 식별자 번호 넣기 (숙소 이름을 뽑기 위해 String으로 선언했으나 자료를 넣을때는 int로 형변환 해야함)
				p.setLodNo(Integer.toString(lno));
				//mybatis실행해서 결과값 곱해 넣기
				result *= sqlSession.insert("lodMapper.insertReviewPhoto", p);
			}
		return result;
	}
	
	//방 사진 조회 메소드
	public ArrayList<Photo> selectRoomPhoto(SqlSession sqlSession, int lno) {
		return (ArrayList)sqlSession.selectList("lodMapper.selectRoomPhoto",lno);
	}

	//========== 메인페이지 로드 =================
	public ArrayList<Lodging> selectLodgingMain(Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLodgingMain");
		ArrayList<Lodging> lList = new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				lList.add(new Lodging(rset.getString("LOD_NAME")
									,rset.getString("LOD_ADDRESS")
									,rset.getString("FILE_PATH")
									,rset.getString("CHANGE_NAME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return lList;
	}
	
	//방 리뷰 사진 조회
	public ArrayList<Photo> selectReviewPhoto(SqlSession sqlSession, int lno) {
		return (ArrayList)sqlSession.selectList("lodMapper.selectReviewPhoto", lno);
	}
	
}
