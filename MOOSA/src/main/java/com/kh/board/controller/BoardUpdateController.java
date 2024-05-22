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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService bs = new BoardService();
		
		//글정보(번호,카테고리번이름,제목,내용,유저아이디,작성일)
		Board b = bs.selectBoard(bno);
		
		//카테고리 목록
		ArrayList<Category> caList = bs.boardInsert();
		
		//첨부파일
		Attachment at = bs.selectAt(bno);
		
		request.setAttribute("b", b);
		request.setAttribute("caList", caList);
		request.setAttribute("at", at);
		request.getRequestDispatcher("/views/board/boardUpdateView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 20*1024*1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles");
			
			MultipartRequest multiRequest 
			= new MultipartRequest(request,savePath,maxSize,"UTF-8",new MoosaFileRenamePolicy());
			
			//수정된 파일정보
			int originFileNo = Integer.parseInt(multiRequest.getParameter("originFileNo"));
			String originFileName = multiRequest.getParameter("originFileName");
			
			
			
			
			//수정된 글정보 가져와서 다시 b에 넣어주기
			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo"));
			String content = multiRequest.getParameter("board-content");
			String title = multiRequest.getParameter("board-title");
			String categoryNo = multiRequest.getParameter("board-category");
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardContent(content);
			b.setBoardTitle(title);
			b.setCategoryNo(categoryNo);
			
			//수정된 첨부파일
			Attachment at = null; //첨부파일 없을경우
			
			if(multiRequest.getOriginalFileName("reUploadFile") != null) {
				
				at = new Attachment();
				at.setFileName(multiRequest.getOriginalFileName("reUploadFile"));
				at.setChangeName(multiRequest.getFilesystemName("reUploadFile"));
				at.setFilePath("/resources/uploadFiles");
				
				//기존 게시글에 첨부파일 있으면 기존 fileNo, 없으면 새로운 boardNo 
				if(multiRequest.getParameter("originFileNo")!=null) {
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				}else {
					at.setRefNo(boardNo);
				}
				
			}
			
			int result = new BoardService().updateBoard(b,at);
			
			HttpSession session = request.getSession();
			if(result>0) {
				session.setAttribute("alertMsg", "글수정에 성공 했습니다!");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
				
				//기존 첨부파일 삭제
				if(at!=null && at.getFileNo()!=0) {
					//파일경로+원본파일(키값)
					new File(savePath+multiRequest.getParameter("originFileName")).delete();
				}
				
			}else {
				session.setAttribute("alertMsg", "글수정에 실패하였습니다.");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
				
			}
			
		}
		
		
		
	}

}























