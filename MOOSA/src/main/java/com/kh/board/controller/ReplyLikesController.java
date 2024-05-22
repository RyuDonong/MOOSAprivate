package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class ReplyLikesController
 */
@WebServlet("/likes.re")
public class ReplyLikesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyLikesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyLikes = Integer.parseInt(request.getParameter("replyLikes"));
		
		//좋아요 취소 업데이트
		int result = new BoardService().likesCancel(replyLikes);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyLikes = Integer.parseInt(request.getParameter("replyLikes"));
		
		//좋아요 업데이트
		int result = new BoardService().likesUpdate(replyLikes);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		
		
		
	}

}
