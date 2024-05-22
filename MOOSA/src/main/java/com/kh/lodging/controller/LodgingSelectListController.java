package com.kh.lodging.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.lodging.model.service.LodgingService;
import com.kh.lodging.model.vo.Lodging;

/**
 * Servlet implementation class LodgingSelectController
 */
@WebServlet("/selectList.lo")
public class LodgingSelectListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LodgingSelectListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //숙소 카테고리별 조회 ajax
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");//카테고리 가져오고
		ArrayList<Lodging> list = new LodgingService().selectLodgingList(category);
		
		
		response.setContentType("json/application;charset=UTF-8");
//		System.out.println(list);
		new Gson().toJson(list,response.getWriter());
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
