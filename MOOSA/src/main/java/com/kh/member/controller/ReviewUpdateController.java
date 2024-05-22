package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.lodging.model.vo.Review;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class ReviewUpdateController
 */
@WebServlet("/updateReview.me")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		Review r = new MemberService().selectReview(reviewNo);
		request.setAttribute("r", r);
		request.getRequestDispatcher("/views/member/updateReview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int count=0;
		if(request.getParameter("count")!=null) {
			count = Integer.parseInt(request.getParameter("count"));
		}
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String reviewContent = request.getParameter("reviewContent");
		Review r = new Review();
		r.setCount(count);
		r.setReviewNo(reviewNo);
		r.setReviewContent(reviewContent);
		int result = new MemberService().updateReview(r);
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("alertMsg", "수정되었습니다. 마이페이지에서 다시 확인해주세요");
			response.sendRedirect(request.getContextPath()+"/myReview.me?userNo="+Integer.parseInt(request.getParameter("userNo")));
		}else {
			session.setAttribute("alertMsg", "수정되지 않았습니다. 관리자에게 문의하세요");
			response.sendRedirect(request.getContextPath()+"/myReview.me?userNo="+Integer.parseInt(request.getParameter("userNo")));
		}
		
	}
}	


