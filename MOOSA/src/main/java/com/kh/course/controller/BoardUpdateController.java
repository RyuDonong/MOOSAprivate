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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.rv")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardUpdateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		BoardService bs = new BoardService();

		Board b = bs.selectBoard(bno);

		ArrayList<Category> cList = bs.selectCategoryList();

		Attachment at = bs.selectAttachment(bno);

		request.setAttribute("b", b);
		request.setAttribute("cList", cList);
		request.setAttribute("at", at);

		request.getRequestDispatcher("views/course/boardUpdateView.jsp").forward(request, response);
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

			String savePath = request.getSession().getServletContext().getRealPath("/resources/coursereviewImages/");

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String category = multiRequest.getParameter("category");

			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setCategory(category);

			Attachment at = null;
			if (multiRequest.getOriginalFileName("reUploadFile") != null) {

				at = new Attachment();
				at.setFileName(multiRequest.getOriginalFileName("reUploadFile"));
				at.setChangeName(multiRequest.getFilesystemName("reUploadFile"));
				at.setFilePath("/resources/coursereviewImages/");

				if (multiRequest.getParameter("originFileNo") != null) {

					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));

				} else {
					at.setRefno(boardNo);
				}

			}

			int result = new BoardService().updateBoard(b, at);

			HttpSession session = request.getSession();
			if (result > 0) {// 성공시
				session.setAttribute("alertMsg", "게시글 수정완료");
				response.sendRedirect(request.getContextPath() + "/detail.rv?bno=" + boardNo);
				if (at != null && at.getFileNo() != 0) {
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
				}

			} else {
				session.setAttribute("alertMsg", "게시글 수정실패");
				response.sendRedirect(request.getContextPath() + "/detail.rv?bno=" + boardNo);
			}
		}

	}
}
