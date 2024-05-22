package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class ReplyCommendController
 */
@WebServlet("/commend.re")
public class ReplyCommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyCommendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commendNo = Integer.parseInt(request.getParameter("commendNo"));
		
		//따봉 도르마무!
		int result = new BoardService().replyCommendCancel(commendNo);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int commendNo = Integer.parseInt(request.getParameter("commendNo"));
		
		//따봉성공!
		int result = new BoardService().replyCommend(commendNo);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}

}
