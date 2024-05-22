package com.kh.lodging.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MoosaFileRenamePolicy;
import com.kh.common.model.vo.Photo;
import com.kh.lodging.model.service.LodgingService;
import com.kh.lodging.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insertReview.lo")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//요청이 multipart 형식인지 확인하기
		if(ServletFileUpload.isMultipartContent(request)) {
			int size = 10*1024*1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/reviewImages/");
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,size,"UTF-8",new MoosaFileRenamePolicy());
			//userNo는 int자료형이여야 하나 사용자 이름으로 꺼내 사용하고자 vo에서 String으로 선언
			String userNo = multiRequest.getParameter("userNo");
			int lno = Integer.parseInt(multiRequest.getParameter("lno"));
			int count=0;
			if(multiRequest.getParameter("count")!=null) {
				count = Integer.parseInt(multiRequest.getParameter("count"));
			}else {
				count = 0;
			}
			
			String reviewContent = multiRequest.getParameter("review-content");
			String roomNo = multiRequest.getParameter("roomNo");
			Review r = new Review();
			r.setUserNo(userNo);
			r.setCount(count);
			r.setReviewContent(reviewContent);
			r.setRoomNo(roomNo);
			
			//photo정보들을 담아둘 list미리 준비
			ArrayList<Photo> pList = new ArrayList<>();
			//키값 반복하여 뽑아주기 
			for(int i=1;i<=3;i++) {
				String key = "reviewImg"+i;
				if(multiRequest.getOriginalFileName(key)!=null) {
				//해당 키값으로 넘어온 데이터가 있을때 photo에 담아 list에 담기
					Photo p = new Photo();
					p.setOriginName(multiRequest.getOriginalFileName(key));
					p.setChangeName(multiRequest.getFilesystemName(key));
					p.setFilePath("/resources/reviewImages/");
					p.setFileLevel(2);
					pList.add(p);
				}
			}
			
			int result = new LodgingService().insertReview(r,pList,lno);
			HttpSession session = request.getSession();
			String msg = "";
			if(result>0) {
				msg = "리뷰 등록!";
			}else{
				msg = "리뷰 등록 실패! 관리자에게 문의하세요";
			}
			session.setAttribute("alertMsg", msg);
			response.sendRedirect(request.getContextPath()+"/lodDetail.lo?lno="+lno);
		}
	}

}
