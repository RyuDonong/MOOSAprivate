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
 * Servlet implementation class BoardListViewController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//페이징바 만들기
		//아래 순서대로 하나씩 찾아가면서 채워가자
		
		int listCount; // 게시글 총갯수
		int currentPage; //현재페이지
		int pageLimit; // 페이지 최대갯수
		int boardLimit; // 한페이지에서 보여줄 개시글 총 갯수
		
		int maxPage;
		int startPage;
		int endPage; // 페이징바의 끝수
		
		//DB에 저장돼 있는 게시글 갯수 불러오기
		listCount = new BoardService().listCount();
		
		
		//ListView 페이지에서 현재페이지 가져오기?
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//페이지 최대갯수는 5개
		pageLimit = 5;
		//한페이지에서 보여줄 게시글 갯수는 DB가 얼마 없기에 3개씩
		boardLimit = 5;
		
		//맨 마지막 페이지수는?
		//총게시글 수 = 20 / 한페이지 보여줄 게시글수 = 3 == 6 페이지, 7페이지에서 게시글 2개
		// 이전(disabled)  1  2  3  4  5  다음 =>  이전  6  7  8  9  다음(disabled)
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		//System.out.println(maxPage); == 7
		
		//다음페이지에 첫 페이징바 숫자?
		//(currentPage -1) / pageLimit * pageLimit+1;
		//첫페이지 : (현재페이지 1 -1) / 5 * 5+1 == 1
		//두번째페이지 : (현재페이지 2 - 1) / 5*5 +1 == 2
		startPage = (currentPage -1)/pageLimit * pageLimit+1;
		//이건 이해못함 ㅠㅠ
		//System.out.println("스타트페이지 : "+startPage);
		
		//페이지에 마지막 페이징바 숫자
		endPage = startPage + pageLimit-1;
		//System.out.println("엔드페이지 : "+endPage);
		
		//maxPage에 도달 시 그 이상을 보여주면 안됨 endPage = maxPage
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		
		//위 정보들을 BoardPagingBar 필드에 담아주기
		BoardPagingBar bp = new BoardPagingBar(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		//리스트 전부 가져오기
		ArrayList<Board> list = new BoardService().boardList(bp);
		
		request.setAttribute("bp", bp);
		request.setAttribute("list", list);
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
