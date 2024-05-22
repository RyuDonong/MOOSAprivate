package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lodging.model.vo.Lodging;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class AddWishListController
 */
@WebServlet("/addWishList.me")
public class AddWishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWishListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("여기 옴");
		int lno = Integer.parseInt(request.getParameter("lno"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		ArrayList<Lodging> list = new MemberService().selectWishList(userNo);//중복 확인 하기 위한 조회 
		//중복이라면 제거 하기
		int result = 0;//결과값 받을 변수 준비
		if(!list.isEmpty()) {//조회해온 데이터가 있다면 
			boolean flag = false; // 조회해온 데이터에서 중복값이 있는지 없는지 담아둘 변수 
			for(Lodging l : list) {
				if(l.getLodNo()==lno) {//조회해온 데이터에서 새로 위시리스트 담을 숙소 번호 비교해서 있다면 flag true
					flag = true;
				}
			}
			if(flag) {//조회해온 숙소 있다면 
				result = new MemberService().deleteWishList(userNo, lno);
//				System.out.println("==lno"+lno);
			}else {//조회해온 숙소가 없다면 
				result = new MemberService().addWishList(lno,userNo);
//				System.out.println("!=lno"+lno);
			}
			
		}else {//조회 해온 데이터가 없다면
			result = new MemberService().addWishList(lno,userNo);
		}
		
//		System.out.println(lno);
//		System.out.println(userNo);
		//위시리스트에 넣는 사용자와 숙소 식별자 담아옴
		//담아온 사용자와 숙소 정보 넘겨 결과받기
		//결과 넘겨주기
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
