package com.kh.course.controller;

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

import com.kh.common.MyFileRenamePolicy;
import com.kh.course.model.service.BoardService;
import com.kh.course.model.vo.Attachment;
import com.kh.course.model.vo.Board;
import com.kh.course.model.vo.Category;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.rv")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Category> cList = new BoardService().selectCategoryList();

		request.setAttribute("cList", cList);

		request.getRequestDispatcher("views/course/boardInsertView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if (ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 10 * 1024 * 1024;

			String savePath = request.getSession().getServletContext().getRealPath("/resources/creviewImage/");

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			String category = multiRequest.getParameter("board_category");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String boardWriter = multiRequest.getParameter("userNo");

			Board b = new Board();
			b.setCategory(category);
			b.setBoardContent(content);
			b.setBoardTitle(title);
			b.setBoardWriter(boardWriter);

			Attachment at = null;

			if (multiRequest.getOriginalFileName("UploadFile") != null) {

				at = new Attachment();
				at.setFileName(multiRequest.getOriginalFileName("UploadFile"));
				at.setChangeName(multiRequest.getFilesystemName("UploadFile"));
				at.setFilePath("/resources/creviewImage/");
					
			}
			int result = new BoardService().insertBoard(b, at);

			HttpSession session = request.getSession();

			if (result > 0) {
				session.setAttribute("alertMsg", "리뷰작성완료!");
				response.sendRedirect(request.getContextPath() + "/list.rv?currentPage=1");
			} else {
				if (at != null) {

					new File(savePath + at.getChangeName()).delete();
				}
				session.setAttribute("alertMsg", "리뷰 작성 실패");
				response.sendRedirect(request.getContextPath() + "/list.rv?currentPage=1");
			}
		}
	}

}
