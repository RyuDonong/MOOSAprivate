package com.kh.common.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.dao.LodgingDao;

public class PhotoDao {

	private Properties prop = new Properties();
	public PhotoDao(){
		String filePath = LodgingDao.class.getResource("/resources/sql/photo-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Photo> selectReviewPhoto(Connection conn, int lno) {
		String sql = prop.getProperty("selectReviewPhoto");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Photo> pList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pList.add(new Photo(rset.getString("THUMBNAIL")
								   ,rset.getInt("REVIEW_NO")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}

}
