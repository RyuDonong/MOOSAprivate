package com.kh.course.model.vo;

public class Course {
	private int courseNo;
	private String courseName; // COURSE_CONTNENT VARCHAR2(1000 BYTE)
	private String courseContent;
	private String coursePhoto; // COURSE_NAME VARCHAR2(40 BYTE)
	// COURSE_NO NUMBER
	// COURSE_PHOTO VARCHAR2(1000 BYTE)

	public Course() {
		super();
	}

	public Course(int courseNo, String courseName, String courseContent, String coursePhoto) {
		super();
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.courseContent = courseContent;
		this.coursePhoto = coursePhoto;
	}
	
	

	public Course(int courseNo, String courseName, String courseContent) {
		super();
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.courseContent = courseContent;
	}

	public int getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCoursePhoto() {
		return coursePhoto;
	}

	public void setCoursePhoto(String coursePhoto) {
		this.coursePhoto = coursePhoto;
	}

	@Override
	public String toString() {
		return "Course [courseNo=" + courseNo + ", courseName=" + courseName + ", courseContent=" + courseContent
				+ ", coursePhoto=" + coursePhoto + "]";
	}

}
