package com.kh.common.model.vo;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	//mybatis sqlSession 얻어오기
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		String resource = "/mybatis-config.xml"; // classes폴더의 최상위
		try {
			//입력한 config파일 경로로 연결하는 inputStream 얻어오기
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//얻어온 inputStream을 이용하여 SqlSessionFactory얻어오기
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//sqlSessionFactory로부터 mybatis 구문을 사용하기 위한 sqlSession 객체 얻어오기 매개변수 false/true 는 autoCommit에 대한 설정
			sqlSession = sqlSessionFactory.openSession(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSession;
	}

}
