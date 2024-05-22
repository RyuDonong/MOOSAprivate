package com.kh.board.model.vo;

import java.sql.Date;

public class BoardReply {
	
	private int replyNo;
	private String replyContent;
	private String likes;
	private Date createDate;
	private String status;
	private int replyWriter;
	private String refBno;
	private String userId;
	private int recommend;
	private int hate;
	
	public BoardReply() {
		super();
	}
	
	
	

	public BoardReply(int replyNo, String replyContent, Date createDate, int replyWriter, String userId, int recommend,
			int hate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.replyWriter = replyWriter;
		this.userId = userId;
		this.recommend = recommend;
		this.hate = hate;
	}




	public BoardReply(int replyNo, String replyContent, String likes, Date createDate, int replyWriter, String userId,
			int recommend, int hate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.likes = likes;
		this.createDate = createDate;
		this.replyWriter = replyWriter;
		this.userId = userId;
		this.recommend = recommend;
		this.hate = hate;
	}

	public BoardReply(int replyNo, String replyContent, String likes, Date createDate, String status, int replyWriter,
			String refBno, String userId, int recommend, int hate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.likes = likes;
		this.createDate = createDate;
		this.status = status;
		this.replyWriter = replyWriter;
		this.refBno = refBno;
		this.userId = userId;
		this.recommend = recommend;
		this.hate = hate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
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

	public int getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(int replyWriter) {
		this.replyWriter = replyWriter;
	}

	public String getRefBno() {
		return refBno;
	}

	public void setRefBno(String refBno) {
		this.refBno = refBno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	@Override
	public String toString() {
		return "BoardReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", likes=" + likes
				+ ", createDate=" + createDate + ", status=" + status + ", replyWriter=" + replyWriter + ", refBno="
				+ refBno + ", userId=" + userId + ", recommend=" + recommend + ", hate=" + hate + "]";
	}

	
	

}
