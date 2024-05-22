package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int count;
	private int likes;
	private Date createDate;
	private String status;
	private String boardWriter;
	private String categoryNo;
	private String userId;
	private String categoryName;
	private int rank;
	private String filePath;
	private String changeName;
	
	private String thumbnailImg;
	
	public Board() {
		super();
	}

	public Board(int boardNo, String boardTitle, String boardContent, int count, int likes, Date createDate,
			String boardWriter, String userId, String categoryName, int rank, String filePath, String changeName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.count = count;
		this.likes = likes;
		this.createDate = createDate;
		this.boardWriter = boardWriter;
		this.userId = userId;
		this.categoryName = categoryName;
		this.rank = rank;
		this.filePath = filePath;
		this.changeName = changeName;
	}

	public Board(int boardNo, String boardTitle, String boardContent, Date createDate, String userId,
			String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.createDate = createDate;
		this.userId = userId;
		this.categoryName = categoryName;
	}

	public Board(int boardNo, String boardTitle, int count,String boardContent,String thumbnailImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.count = count;
		this.boardContent = boardContent;
		this.thumbnailImg = thumbnailImg;
		
	}

	public Board(String boardTitle, String boardContent, int count, int likes, Date createDate, String userId,
			String categoryName) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.count = count;
		this.likes = likes;
		this.createDate = createDate;
		this.userId = userId;
		this.categoryName = categoryName;
	}

	public Board(int boardNo, String boardTitle, String boardContent, int count, int likes, Date createDate,
			String boardWriter, String userId, String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.count = count;
		this.likes = likes;
		this.createDate = createDate;
		this.boardWriter = boardWriter;
		this.userId = userId;
		this.categoryName = categoryName;
	}

	public Board(int boardNo, String boardTitle, String boardContent,String boardWriter, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}
	
	public Board(int boardNo, String boardTitle, String boardContent, int count, int likes, Date createDate,
			String userId, String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.count = count;
		this.likes = likes;
		this.createDate = createDate;
		this.userId = userId;
		this.categoryName = categoryName;
	}

	public Board(int boardNo, String boardTitle, String boardContent, int count, int likes, Date createDate,
			String status, String boardWriter, String categoryNo, String userId, String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.count = count;
		this.likes = likes;
		this.createDate = createDate;
		this.status = status;
		this.boardWriter = boardWriter;
		this.categoryNo = categoryNo;
		this.userId = userId;
		this.categoryName = categoryName;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
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

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}
	
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", count=" + count + ", likes=" + likes + ", createDate=" + createDate + ", status=" + status
				+ ", boardWriter=" + boardWriter + ", categoryNo=" + categoryNo + ", userId=" + userId
				+ ", categoryName=" + categoryName + ", rank=" + rank + ", filePath=" + filePath + ", changeName="
				+ changeName + "]";
	}

}
	
