package com.kh.tour.model.vo;

public class TourPhoto {
	
	private int photoNo; 
	private int refBno;
	private String originName;// ORIGIN_NAME VARCHAR2(255 BYTE)
	private String changeName;// CHANGE_NAME VARCHAR2(255 BYTE)
	private String filePath;// FILE_PATH VARCHAR2(1000 BYTE)
	private String Status;	//VARCHAR2(1 BYTE)
	private int lodNo;	//NUMBER
	private int fileLevel;	
	private int tourNo;
	public TourPhoto() {
		super();
		
		
		
	}
	public TourPhoto(int photoNo, String originName, String changeName, String filePath) {
		super();
		this.photoNo = photoNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public int getRefBno() {
		return refBno;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
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
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getLodNo() {
		return lodNo;
	}
	public void setLodNo(int lodNo) {
		this.lodNo = lodNo;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public int getTourNo() {
		return tourNo;
	}
	public void setTourNo(int tourNo) {
		this.tourNo = tourNo;
	}
	@Override
	public String toString() {
		return "TourPhoto [photoNo=" + photoNo + ", refBno=" + refBno + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", Status=" + Status + ", lodNo=" + lodNo + ", fileLevel="
				+ fileLevel + ", tourNo=" + tourNo + "]";
	}

	
	
	
}
	
	