package com.kh.board.model.vo;

import java.sql.Date;

public class Attachment {
	
	private int fileNo;
	private String fileName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private String fileLevel;
	private String status;
	private int refNo;
	
	public Attachment() {
		super();
	}

	public Attachment(String fileName) {
		super();
		this.fileName = fileName;
	}

	public Attachment(int fileNo, String fileName, String changeName, String filePath) {
		super();
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.changeName = changeName;
		this.filePath = filePath;
	}



	public Attachment(int fileNo, String fileName, String changeName, String filePath, Date uploadDate,
			String fileLevel, String status, int refNo) {
		super();
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
		this.refNo = refNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(String fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRefNo() {
		return refNo;
	}

	public void setRefNo(int refNo) {
		this.refNo = refNo;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", fileName=" + fileName + ", changeName=" + changeName + ", filePath="
				+ filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", status=" + status
				+ ", refNo=" + refNo + "]";
	}
	
	
	
	
	
	
}
