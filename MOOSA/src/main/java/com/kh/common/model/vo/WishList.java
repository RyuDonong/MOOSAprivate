package com.kh.common.model.vo;

public class WishList {

	private int wishListNo;//	WISHLIST_NO	NUMBER
	private int lodNo;//	LOD_NO	NUMBER
	private int userNo;//	USER_NO	NUMBER
	
	public WishList() {
		super();
	}
	public WishList(int wishListNo, int lodNo, int userNo) {
		super();
		this.wishListNo = wishListNo;
		this.lodNo = lodNo;
		this.userNo = userNo;
	}
	public int getWishListNo() {
		return wishListNo;
	}
	public void setWishListNo(int wishListNo) {
		this.wishListNo = wishListNo;
	}
	public int getLodNo() {
		return lodNo;
	}
	public void setLodNo(int lodNo) {
		this.lodNo = lodNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "WishList [wishListNo=" + wishListNo + ", lodNo=" + lodNo + ", userNo=" + userNo + "]";
	}
	
	
}
