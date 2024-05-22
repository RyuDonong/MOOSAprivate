package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.vo.Review;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MyReviewController
 */
@WebServlet("/myReview.me")
public class MyReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
//		System.out.println(userNo);
		ArrayList<Review> rList = new MemberService().selectMyReview(userNo);
		ArrayList<Photo> pList = new MemberService().selectMyReviewPhoto(userNo);
		request.setAttribute("rList", rList);
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("/views/member/myReview.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
