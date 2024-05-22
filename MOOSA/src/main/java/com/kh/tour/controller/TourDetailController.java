package com.kh.tour.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.Photo;
import com.kh.tour.model.service.TourService;
import com.kh.tour.model.vo.TourBoard;
import com.kh.tour.model.vo.TourPhoto;

/**
 * Servlet implementation class TourDetailController
 */
@WebServlet("/detail.tO")
public class TourDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TourDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno"));

		// 상세보기
		TourService ts = new TourService();
		// 조회수 증가시키기
		int result = ts.increaseCount(boardNo);

		// 조회수 증가처리가 성공적으로 되었다면 조회하기
		if (result > 0) {

			TourBoard b = ts.selectBoard(boardNo);

			TourPhoto tp = new TourPhoto();
			tp = ts.selectAttachmentList1(boardNo);

			System.out.println(tp);

			request.setAttribute("b", b);
			request.setAttribute("tp", tp);

			request.getRequestDispatcher("views/tour/tourDetailView.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("alertMsg", "조회실패 관리자에게 문의하세요");
			response.sendRedirect(request.getContextPath() + "/list.to");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
