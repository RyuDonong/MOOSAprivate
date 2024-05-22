package com.kh.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.MoosaFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//작성 페이지에서 option에 넣을 카테고리 종류 가져오기
		ArrayList<Category> cgList = new BoardService().boardInsert();
		
		
		request.setAttribute("cgList", cgList);
		request.getRequestDispatcher("views/board/boardInsertView.jsp").forward(request, response);
		//가져온 카테고리 리스트 정보를 insertView 페이지에 넘기고, 다음 받을 정보는 Post 에서
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 20 * 1024* 1024; //파일 최대크기 10mb
			
			//현재 구동되고 있는 웹어플 기준으로 경로 잡아주기
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MoosaFileRenamePolicy());
			
			
			String category = multiRequest.getParameter("board-category");
			String title = multiRequest.getParameter("board-title");
			String content = multiRequest.getParameter("board-content");
			String writer = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategoryName(category);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setBoardWriter(writer);
			
			System.out.println(content);
			
			Attachment at = null;
						
			
			if(multiRequest.getOriginalFileName("uploadFile")!=null) {
				
				at = new Attachment();
				at.setFileName(multiRequest.getOriginalFileName("uploadFile"));
				at.setChangeName(multiRequest.getFilesystemName("uploadFile"));
				at.setFilePath("/resources/uploadFiles/");
			}
			
			int result = new BoardService().insertBoard(b,at);
			
			HttpSession session = request.getSession();
			
			if(result>0) {
				
				session.setAttribute("alertMsg", "게시글 등록성공!!");
				response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1"); 
				
			}else {
				
				if(at!=null) {
					new File(savePath+at.getChangeName()).delete();
				}
				
				session.setAttribute("alertMsg", "게시글 등록실패 ㅠㅠ");
				response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1"); 
				
			}
			
		}
	
	}

}














