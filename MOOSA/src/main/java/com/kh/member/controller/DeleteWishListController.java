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
 * Servlet implementation class DeleteWishListController
 */
@WebServlet("/deleteWishList.me")
public class DeleteWishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWishListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		String[] deleteWishList =request.getParameterValues("deleteWishList");
		
		int result = new MemberService().deleteWishList(userNo,deleteWishList);
		HttpSession session = request.getSession();
		String msg = "";
		if(result>0) {
			msg = "위시리스트가 제거 되었습니다.";
		}else {
			msg = "위시리스트가 제거 되지 않았습니다. 관리자에게 문의하세요.";
		}
		session.setAttribute("alertMsg", msg);
		response.sendRedirect(request.getContextPath()+"/selectWishList.me?userNo="+userNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
