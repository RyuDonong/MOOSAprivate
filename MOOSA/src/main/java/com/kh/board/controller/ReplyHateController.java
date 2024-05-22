package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class ReplyHateController
 */
@WebServlet("/hate.re")
public class ReplyHateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyHateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hateNo = Integer.parseInt(request.getParameter("hateNo"));
		
		//비추천 도르마무!
		int result = new BoardService().replyHateCancel(hateNo);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hateNo = Integer.parseInt(request.getParameter("hateNo"));
		
		//비추천 성공!
		int result = new BoardService().replyHate(hateNo);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}


}
