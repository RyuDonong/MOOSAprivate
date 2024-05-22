package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.BoardPagingBar;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Board> list = new ArrayList<>();
		BoardService bs = new BoardService();
		//검색할 내용
		String sc = request.getParameter("searchContent");
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); 
		//제목,내용,아이디 선택옵션
		int searchOption = Integer.parseInt(request.getParameter("searchOption"));
		
		int listCount; // 게시글 총갯수
		int pageLimit; // 페이지 최대갯수
		int boardLimit; // 한페이지에서 보여줄 개시글 총 갯수
		int maxPage;
		int startPage;
		int endPage; // 페이징바의 끝수
		
		//검색 결과에 따른 게시글 갯수
		
		listCount = new BoardService().sListCount(searchOption);
		
		pageLimit = 5; //페이지 최대갯수는 5개
		
		boardLimit = 5; //한페이지에서 보여줄 게시글 갯수는 DB가 얼마 없기에 3개씩
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage -1)/pageLimit * pageLimit+1;
		
		endPage = startPage + pageLimit-1; //페이지에 마지막 페이징바 숫자
		
		if(endPage>maxPage) {//maxPage에 도달 시 그 이상을 보여주면 안됨 endPage = maxPage
			endPage = maxPage;
		}
		
		BoardPagingBar bp = new BoardPagingBar(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		
		
		
		switch(searchOption) {
			case 1 : list = bs.searchTitle(sc,bp); break; //제목으로 검색
			case 2 : list = bs.searchContent(sc,bp); break; //내용으로 검색
			case 3 : list = bs.searchUserId(sc,bp); break; //아이디로 검색
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("bp", bp);
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
