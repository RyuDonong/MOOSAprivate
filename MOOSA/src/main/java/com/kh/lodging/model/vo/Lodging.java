package com.kh.lodging.model.vo;

public class Lodging {

	private int lodNo;
	private String lodName;
	private String lodAddress;
	private String lodInfo;
	private String status;
	private String userNo;
	private String category;
	//파일 경로와 바뀐 파일명 합친변수
	private String Thumbnail;
	
	private String filePath;
	private String changeName;
	
	public Lodging() {
		super();
	}
	
	
	public Lodging(String lodName, String lodAddress, String filePath, String changeName) {
		super();
		this.lodName = lodName;
		this.lodAddress = lodAddress;
		this.filePath = filePath;
		this.changeName = changeName;
	}




	public Lodging(int lodNo, String lodName, String lodAddress, String thumbnail) {
		super();
		this.lodNo = lodNo;
		this.lodName = lodName;
		this.lodAddress = lodAddress;
		Thumbnail = thumbnail;
	}
	
	

	public Lodging(int lodNo, String lodName, String lodAddress, String lodInfo, String category) {
		super();
		this.lodNo = lodNo;
		this.lodName = lodName;
		this.lodAddress = lodAddress;
		this.lodInfo = lodInfo;
		this.category = category;
	}


	public Lodging(int lodNo, String lodName, String lodAddress, String lodInfo, String category, String thumbnail) {
		super();
		this.lodNo = lodNo;
		this.lodName = lodName;
		this.lodAddress = lodAddress;
		this.lodInfo = lodInfo;
		this.category = category;
		Thumbnail = thumbnail;
	}


	public Lodging(int lodNo, String lodName, String lodAddress, String lodInfo, String status, String userNo,
			String category, String thumbnail) {
		super();
		this.lodNo = lodNo;
		this.lodName = lodName;
		this.lodAddress = lodAddress;
		this.lodInfo = lodInfo;
		this.status = status;
		this.userNo = userNo;
		this.category = category;
		Thumbnail = thumbnail;
	}

	public int getLodNo() {
		return lodNo;
	}
	public void setLodNo(int lodNo) {
		this.lodNo = lodNo;
	}
	public String getLodName() {
		return lodName;
	}
	public void setLodName(String lodName) {
		this.lodName = lodName;
	}
	public String getLodAddress() {
		return lodAddress;
	}
	public void setLodAddress(String lodAddress) {
		this.lodAddress = lodAddress;
	}
	public String getLodInfo() {
		return lodInfo;
	}
	public void setLodInfo(String lodInfo) {
		this.lodInfo = lodInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getThumbnail() {
		return Thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		Thumbnail = thumbnail;
	}
	@Override
	public String toString() {
		return "Lodging [lodNo=" + lodNo + ", lodName=" + lodName + ", lodAddress=" + lodAddress + ", lodInfo="
				+ lodInfo + ", status=" + status + ", userNo=" + userNo + ", category=" + category + ", Thumbnail="
				+ Thumbnail + "]";
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
	
	
	
	
}
