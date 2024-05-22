package com.kh.course.model.vo;

import java.sql.Date;

public class Attachment {
	private int fileNo; 
	private int refno;
	private String fileName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private String status;

	
//	UPLOAD_DATE	DATE
//	FILE_NO	NUMBER
//	REF_NO	NUMBER
//	STATUS	VARCHAR2(1 BYTE)
//	FILE_LEVEL	VARCHAR2(1 BYTE)
//	FILE_PATH	VARCHAR2(2000 BYTE)
//	FILE_NAME	VARCHAR2(255 BYTE)
//	CHANGE_NAME	VARCHAR2(255 BYTE)
	
	
	public Attachment() {
		super();
	}


public Attachment(int fileNo, int refno, String fileName, String changeName, String filePath, Date uploadDate,
		int fileLevel, String status) {
	super();
	this.fileNo = fileNo;
	this.refno = refno;
	this.fileName = fileName;
	this.changeName = changeName;
	this.filePath = filePath;
	this.uploadDate = uploadDate;
	this.fileLevel = fileLevel;
	this.status = status;
}




public Attachment(int fileNo, String fileName, String changeName, String filePath) {
	super();
	this.fileNo = fileNo;
	this.fileName = fileName;
	this.changeName = changeName;
	this.filePath = filePath;
}


public int getFileNo() {
	return fileNo;
}


public void setFileNo(int fileNo) {
	this.fileNo = fileNo;
}


public int getRefno() {
	return refno;
}


public void setRefno(int refno) {
	this.refno = refno;
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


public int getFileLevel() {
	return fileLevel;
}


public void setFileLevel(int fileLevel) {
	this.fileLevel = fileLevel;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


@Override
public String toString() {
	return "Attachment [fileNo=" + fileNo + ", refBno=" + refno + ", fileName=" + fileName + ", changeName="
			+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
			+ ", status=" + status + "]";
}
	
	
	


	

}
