package com.kh.course.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.course.model.dao.CourseDao;
import com.kh.course.model.vo.Attachment;
import com.kh.course.model.vo.Course;
import com.kh.course.model.vo.Reply;

public class CourseService {

	public int insertReply(Reply r) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new CourseDao().insertReply(conn, r);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	// 댓글 목록 조회
	public ArrayList<Reply> replyList(int refBno) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Reply> list = new CourseDao().replyList(conn, refBno);

		JDBCTemplate.close(conn);

		return list;
	}
	
	public int increaseCount(int cno) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new CourseDao().increaseCount(conn,cno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Course> selectCourseReviewList() {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Course> list = new CourseDao().selectCourseReviewList(conn);

		// select니까 자원반납만
		JDBCTemplate.close(conn);

		return list;
	}

	public int insertCourseView(Course c, ArrayList<Attachment> atList) {
		Connection conn = JDBCTemplate.getConnection();

		int CourseNo = new CourseDao().selectCourseNo(conn);

		// 게시글 등록
		c.setCourseNo(CourseNo);
		int result = new CourseDao().insertThumbnail(conn, c);

		// 사진 등록(파일처리)
		// 참조할 게시글 번호 추가해주기
		int result2 = new CourseDao().insertAttachmentList(conn, atList, CourseNo);

		// 트랜잭션처리하기
		if (result * result2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result * result2;
	}
	public Course selectCourse(int cno) {
		Connection conn = JDBCTemplate.getConnection();
		
		Course c = new CourseDao().selectCourse(conn,cno);
		 
		JDBCTemplate.close(conn);
		
		return c;
	}

	public ArrayList<Attachment> selectAttachmentList(int courseNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Attachment> atList = new CourseDao().selectAttachmentList(conn, courseNo);
		
		JDBCTemplate.close(conn);
		
		return atList;	
	}

	
}
