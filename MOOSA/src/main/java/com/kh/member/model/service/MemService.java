package com.kh.member.model.service;

import java.util.ArrayList;

import com.kh.common.model.vo.Photo;
import com.kh.common.model.vo.WishList;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.member.model.vo.Member;

public interface MemService {

	int addWishList(WishList wishList);
	
	ArrayList<Lodging> selectWishList(int userNo);
	
	int deleteWishList(WishList wishList);
	
	int deleteWishList(int userNo,String[] deleteWishList);
	
	ArrayList<Review> selectMyReview(int userNo);
	
	ArrayList<Photo> selectMyReviewPhoto(int userNo);
	
	int deleteReview(int reviewNo);
	
	int updatePwd(Member m);
	
	int updateMember(Member updateMem,Photo p);
	
	Review selectReview(int reviewNo);
	
	Member selectMember(String userId);
	
	int updateReview(Review r);
	
	int deleteMember(String userId);
	
}
