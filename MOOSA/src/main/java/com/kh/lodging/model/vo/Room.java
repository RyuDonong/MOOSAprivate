package com.kh.lodging.model.vo;

public class Room {

	private int roomNo;//	ROOM_NO	NUMBER
	private String roomName;//	ROOM_NAME	VARCHAR2(100 BYTE)
	private String roomInfo;//	ROOM_INFO	VARCHAR2(2000 BYTE)
	private String status;//	STATUS	VARCHAR2(1 BYTE)
	private int lodNo;//	LOD_NO	NUMBER
	private int photoNo;//	PHOTO_NO	NUMBER
	
	public Room() {
		super();
	}

	public Room(int roomNo, String roomName, String roomInfo, int photoNo) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.roomInfo = roomInfo;
		this.photoNo = photoNo;
	}

	public Room(int roomNo, String roomName, String roomInfo, String status, int lodNo, int photoNo) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.roomInfo = roomInfo;
		this.status = status;
		this.lodNo = lodNo;
		this.photoNo = photoNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLodNo() {
		return lodNo;
	}

	public void setLodNo(int lodNo) {
		this.lodNo = lodNo;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomName=" + roomName + ", roomInfo=" + roomInfo + ", status=" + status
				+ ", lodNo=" + lodNo + ", photoNo=" + photoNo + "]";
	}
	
	
	
}
