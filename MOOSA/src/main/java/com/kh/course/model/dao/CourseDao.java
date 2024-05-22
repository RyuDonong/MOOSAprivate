package com.kh.course.model.dao;

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
import com.kh.course.model.vo.Attachment;
import com.kh.course.model.vo.Course;
import com.kh.course.model.vo.Reply;

public class CourseDao {

	private Properties prop = new Properties();

	public CourseDao() {

		String filePath = CourseDao.class.getResource("/resource/sql/course-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertReply(Connection conn, Reply r) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBno());
			pstmt.setString(3, r.getReplyWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Reply> replyList(Connection conn, int refBno) {

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Reply> list = new ArrayList<>();

		String sql = prop.getProperty("replyList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refBno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Reply(rset.getDate("CREATE_DATE"), rset.getString("USER_ID"), rset.getInt("REPLY_NO"),
						rset.getString("REPLY_CONTENT")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Course> selectCourseReviewList(Connection conn) {
		ResultSet rset = null;
		ArrayList<Course> list = new ArrayList<>();
		Statement stmt = null;

		String sql = prop.getProperty("selectThumbnailList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				list.add(new Course(rset.getInt("Course_NO"), rset.getString("Course_TITLE"), rset.getInt("COUNT"),
						rset.getString("THUMBNAIL")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public int selectCourseNo(Connection conn) {
		int CourseNo = 0;
		ResultSet rset = null;
		Statement stmt = null;

		String sql = prop.getProperty("selectCourseNo");

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				CourseNo = rset.getInt("CNO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}

		return CourseNo;
	}

	public int insertThumbnail(Connection conn, Course c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertThumbnail");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCourseNo());
			pstmt.setString(2, c.getCourseTitle());
			pstmt.setString(3, c.getCourseContent());
			pstmt.setString(4, c.getCourseWriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertAttachmentList(Connection conn, ArrayList<Attachment> atList, int CourseNo) {

		int result = 1;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAttachmentList");

		try {
			// 전부 넣어야하니 향상된 for문 이용하기
			for (Attachment at : atList) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, CourseNo);
				pstmt.setString(2, at.getFileName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				pstmt.setInt(5, at.getFileLevel());

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

	public int increaseCount(Connection conn, int cno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Course selectCourse(Connection conn, int cno) {

		Course c = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCourse");

		return null;
	}

	public Attachment selectAttachment(Connection conn, int cno) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Attachment at = null; // 조회된 데이터 있으면 생성하기 위해 변수선언
		String sql = prop.getProperty("selectAttachment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), rset.getString("FILE_NAME"), rset.getString("CHANGE_NAME"),
						rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return at;
	}

	public ArrayList<Attachment> selectAttachmentList(Connection conn, int courseNo) {
		ResultSet rset = null;
		ArrayList<Attachment> atList = new ArrayList<>();
		PreparedStatement pstmt = null;
		String sql =prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, courseNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				atList.add(new Attachment(rset.getInt("FILE_NO")
										 ,rset.getString("FILE_NAME")
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
		return atList;
	}
}