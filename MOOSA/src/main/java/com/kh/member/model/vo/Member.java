package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	private int userNo; // USER_NO NUMBER
	private String userId;// USER_ID VARCHAR2(30 BYTE)
	private String userPwd;// USER_PWD VARCHAR2(100 BYTE)
	private String userName;// USER_NAME VARCHAR2(15 BYTE)
	private String phone;// PHONE VARCHAR2(13 BYTE)
	private String email;// EMAIL VARCHAR2(100 BYTE)
	private String address;// ADDRESS VARCHAR2(100 BYTE)
	private Date enrollDate;// ENROLL_DATE DATE
	private Date modifyDate;// MODIFY_DATE DATE
	private String status;// STATUS VARCHAR2(1 BYTE)
	private String gender;//GENDER	VARCHAR2(1 BYTE)
	private int gradeNo;//GRADE_NO	NUMBER(10,0)
	private int photoNo;
	private String thumbnail;//PHOTO_NO	NUMBER(30,0)
	
	public Member() {
		super();
	}

	
	public Member(String userId, String phone, String email, String address,int photoNo) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.photoNo = photoNo;
	}


	public Member(String userId, String userPwd, String userName, String phone, String email,
			String address, String gender) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.gender = gender;
	}


	public Member(int userNo, String userId, String userPwd, String userName, String phone, String email,
			String address, Date enrollDate, Date modifyDate, String status, String gender, int gradeNo, String thumbnail) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.gender = gender;
		this.gradeNo = gradeNo;
		this.thumbnail = thumbnail;
	}
	

	public Member(int userNo, String userId, String userPwd, String userName, String phone, String email,
			String address, Date enrollDate, Date modifyDate, String status, String gender, int gradeNo, int photoNo) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.gender = gender;
		this.gradeNo = gradeNo;
		this.photoNo = photoNo;
	}


	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public int getPhotoNo() {
		return photoNo;
	}


	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}


	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", enrollDate=" + enrollDate
				+ ", modifyDate=" + modifyDate + ", status=" + status + ", gender=" + gender + ", gradeNo=" + gradeNo
				+ ", photoNo=" + photoNo + ", thumbnail=" + thumbnail + "]";
	}


	

	
	
}
