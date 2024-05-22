package com.kh.course.model.vo;

import java.sql.Date;

public class Board {
	private Date createDate; // CREATE_DATE DATE
	private String boardWriter; // BOARD_WRITER NUMBER
	private String category; // CATEGORY_NO NUMBER
	private int boardNo; // BOARD_NO NUMBER
	private int likes; // LIKES NUMBER
	private int count; // COUNT NUMBER
	private String status; // STATUS VARCHAR2(1 BYTE)
	private String boardTitle; // BOARD_TITLE VARCHAR2(100 BYTE)
	private String boardContent; // BOARD_CONTENT VARCHAR2(4000 BYTE)
	
	private String filePath;
	private String changeName;

	public Board() {
		super();
	}

	public Board(String boardTitle, String boardContent, String filePath, String changeName) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.filePath = filePath;
		this.changeName = changeName;
	}

	public Board(Date createDate, String boardWriter, String category, int boardNo, int likes, int count, String status,
			String boardTitle, String boardContent) {
		super();
		this.createDate = createDate;
		this.boardWriter = boardWriter;
		this.category = category;
		this.boardNo = boardNo;
		this.likes = likes;
		this.count = count;
		this.status = status;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}

	public Board(Date createDate, String boardWriter, String category, int boardNo, int count, String boardTitle) {
		super();
		this.createDate = createDate;
		this.boardWriter = boardWriter;
		this.category = category;
		this.boardNo = boardNo;
		this.count = count;
		this.boardTitle = boardTitle;
	}

	public Board(Date createDate, String boardWriter, String category, int boardNo, String boardTitle,
			String boardContent) {
		super();
		this.createDate = createDate;
		this.boardWriter = boardWriter;
		this.category = category;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Board [createDate=" + createDate + ", boardWriter=" + boardWriter + ", category=" + category
				+ ", boardNo=" + boardNo + ", likes=" + likes + ", count=" + count + ", status=" + status
				+ ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", filePath=" + filePath
				+ ", changeName=" + changeName + "]";
	}

	
	
	
}
