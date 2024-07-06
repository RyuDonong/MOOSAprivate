package com.kh.lodging.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.lodging.model.dao.LodgingDao;
import com.kh.lodging.model.vo.Lodging;

public class LodgingService {

	
	//========== 메인페이지 로드 =================
	public ArrayList<Lodging> selectLodgingMain() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Lodging> rList = new LodgingDao().selectLodgingMain(conn);
		JDBCTemplate.close(conn);
		return rList;
	}
	
	

	
	
}
