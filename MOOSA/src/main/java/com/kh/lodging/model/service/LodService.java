package com.kh.lodging.model.service;

import java.util.ArrayList;

import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.lodging.model.vo.Room;

public interface LodService {

	ArrayList<Lodging> selectLodgingList(String category);
	
	Lodging selectDetailLodging(int lno);
	
	ArrayList<Room> selectRoom(int lno);
	
	ArrayList<Review> selectEveryReview(int lno);
	
	ArrayList<Photo> selectRoomPhoto(int lno);
	
	ArrayList<Photo> selectReviewPhoto(int lno);
	
	int insertReview(Review r,ArrayList<Photo> pList,int lno);
}
