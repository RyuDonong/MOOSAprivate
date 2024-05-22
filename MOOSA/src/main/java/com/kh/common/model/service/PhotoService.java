package com.kh.common.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.dao.PhotoDao;
import com.kh.common.model.vo.Photo;

public class PhotoService {

	public ArrayList<Photo> selectReviewPhoto(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Photo> pList = new PhotoDao().selectReviewPhoto(conn,lno);
		JDBCTemplate.close(conn); 
		return pList;
	}

}
