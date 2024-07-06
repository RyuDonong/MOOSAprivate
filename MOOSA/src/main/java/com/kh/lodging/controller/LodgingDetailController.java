package com.kh.lodging.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.service.PhotoService;
import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.service.LodServiceImpl;
import com.kh.lodging.model.service.LodgingService;
import com.kh.lodging.model.vo.Lodging;
import com.kh.lodging.model.vo.Review;
import com.kh.lodging.model.vo.Room;

/**
 * Servlet implementation class LodgingDetailController
 */
@WebServlet("/lodDetail.lo")
public class LodgingDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LodgingDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //숙소 상세 페이지 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lno= Integer.parseInt(request.getParameter("lno"));
		
		Lodging lod= new LodServiceImpl().selectDetailLodging(lno);
		ArrayList<Review> list = new LodServiceImpl().selectEveryReview(lno);
		ArrayList<Room> rList = new LodServiceImpl().selectRoom(lno);
		ArrayList<Photo> rpList = new LodServiceImpl().selectRoomPhoto(lno);//방 사진
		ArrayList<Photo> pList = new LodServiceImpl().selectReviewPhoto(lno); //리뷰사진
		request.setAttribute("lod",lod );
		request.setAttribute("list",list );
		request.setAttribute("rList", rList);
		request.setAttribute("rpList", rpList);
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("views/lod/lodDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
