package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemServiceImpl;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class PwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class PwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdUpdateController() {
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
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		// 이미 jsp에서 유효성 검사를 하고넘어 왔기때문에 업데이트할 비밀번호를 바로 VO에 담아 보냄
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(updatePwd);
		
		int result = new MemServiceImpl().updatePwd(m);
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("alertMsg", "비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
			session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath());
			//성공시 메세지 담아 메인 메뉴로 보내기
		}else {
			session.setAttribute("alertMsg", "비밀번호 변경 실패 다시 확인해 주세요.");
			response.sendRedirect(request.getContextPath()+"/updateMember.me");
		}
	}

}
