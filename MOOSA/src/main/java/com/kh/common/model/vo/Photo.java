package com.kh.common.model.vo;

public class Photo {

	private int photoNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String status;
	private String lodNo;
	private int fileLevel;
	private int tourNo;
	private String thumbnail;
	private int reviewNo;
	private int roomNo;
	private String userId;
	
	public Photo() {
		super();
	}
	
	public Photo(String thumbnail) {
		super();
		this.thumbnail = thumbnail;
	}

	public Photo(String thumbnail, int reviewNo) {
		super();
		this.thumbnail = thumbnail;
		this.reviewNo = reviewNo;
	}
	
	public Photo(String thumbnail,String lodNo,  int roomNo) {
		super();
		this.thumbnail = thumbnail;
		this.lodNo = lodNo;
		this.roomNo = roomNo;
	}

	public Photo(int photoNo, String originName,String changeName, String thumbnail, int reviewNo) {
		super();
		this.photoNo = photoNo;
		this.originName = originName;
		this.changeName=changeName;
		this.thumbnail = thumbnail;
		this.reviewNo = reviewNo;
	}

	public Photo(int photoNo, String originName, String changeName, String filePath, String status, String lodNo,
			int fileLevel, int tourNo, String thumbnail) {
		super();
		this.photoNo = photoNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.status = status;
		this.lodNo = lodNo;
		this.fileLevel = fileLevel;
		this.tourNo = tourNo;
		this.thumbnail = thumbnail;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
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
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLodNo() {
		return lodNo;
	}
	public void setLodNo(String lodNo) {
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", originName=" + originName + ", changeName=" + changeName + ", filePath="
				+ filePath + ", status=" + status + ", lodNo=" + lodNo + ", fileLevel=" + fileLevel + ", tourNo="
				+ tourNo + ", thumbnail=" + thumbnail + ", reviewNo=" + reviewNo + ", roomNo=" + roomNo + ", userId="
				+ userId + "]";
	}

	
	
	
}
