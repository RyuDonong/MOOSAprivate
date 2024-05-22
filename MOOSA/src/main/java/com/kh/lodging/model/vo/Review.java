package com.kh.lodging.model.vo;

import java.sql.Date;

public class Review {

	private int reviewNo;
	private String reviewContent;
	private String userNo;
	private Date createDate;
	private String status;
	private String roomNo;
	private int count;
	private int photoNo;
	private String thumbnail;
	
	public Review() {
		super();
	}
	
	public Review(int reviewNo, String reviewContent, Date createDate, String roomNo, int count) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.createDate = createDate;
		this.roomNo = roomNo;
		this.count = count;
	}

	public Review(int reviewNo, String reviewContent, String userNo, Date createDate, String roomNo, int count) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.userNo = userNo;
		this.createDate = createDate;
		this.roomNo = roomNo;
		this.count = count;
		
	}
	public Review(int reviewNo, String reviewContent, String userNo, Date createDate, String status, String roomNo,
			int count, int photoNo) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.userNo = userNo;
		this.createDate = createDate;
		this.status = status;
		this.roomNo = roomNo;
		this.count = count;
		this.photoNo = photoNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", userNo=" + userNo
				+ ", createDate=" + createDate + ", status=" + status + ", roomNo=" + roomNo + ", count=" + count
				+ ", photoNo=" + photoNo + "]";
	}
	
	
}

