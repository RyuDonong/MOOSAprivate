package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class DeleteReviewController
 */
@WebServlet("/deleteReview.me")
public class DeleteReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
//		System.out.println("여기 옴");
		int result = new MemberService().deleteReview(reviewNo);
		HttpSession session = request.getSession();
		String msg = "";
		if(result>0) {
			msg = "삭제되었습니다.";
		}else {
			msg = "삭제되지 않았습니다. 관리자에게 문의하세요.";
		}
		session.setAttribute("alertMsg", msg);
		response.sendRedirect(request.getContextPath()+"/myReview.me?userNo="+Integer.parseInt(request.getParameter("userNo")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
