package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiMenuItemUI;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MoosaFileRenamePolicy;
import com.kh.common.model.vo.Photo;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/updateMember.me")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("views/member/updateMember.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("여기 옴");
		request.setCharacterEncoding("UTF-8");
		if(ServletFileUpload.isMultipartContent(request)) {
			//용량 정하기
			int maxSize = 10*1024*1024;
			//저장경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/profileImages/");
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MoosaFileRenamePolicy());
			String userId= multiRequest.getParameter("userId");
			String phone= multiRequest.getParameter("phone");
			String address= multiRequest.getParameter("address");
			String email = multiRequest.getParameter("email");
			int photoNo=Integer.parseInt(multiRequest.getParameter("photoNo"));
			Member updateM = new Member(userId,phone,email,address,photoNo);
			
			//순서가 잘못 되었음 파일 생성이 먼저여야 수정이 가능
			Photo p = null;
			if(multiRequest.getOriginalFileName("profile")!=null) {
				p= new Photo();
				p.setOriginName(multiRequest.getOriginalFileName("profile"));
				p.setChangeName(multiRequest.getFilesystemName("profile"));
				p.setFilePath("/resources/profileImages/");
				p.setFileLevel(3);
				p.setThumbnail(p.getFilePath()+p.getChangeName());
				
			}
			//데이터 담은 Photo객체와 유저 정보 같이 넘기기
			int result= new MemberService().updateMember(updateM,p);
			
			//정보 갱신을 위한 조회
			Member updateMember= new MemberService().selectMember(userId);
			
			HttpSession session = request.getSession();
			String msg = "";
			if(result>0) {
				msg = "정보가 수정되었습니다!";
				session.setAttribute("loginUser", updateMember);
				session.setAttribute("profile", p);
				//System.out.println(p);
			}else {
				msg = "정보 수정 실패했습니다. 관리자에게 문의하세요.";
			}
			session.setAttribute("alertMsg", msg);
			response.sendRedirect(request.getContextPath()+"/updateMember.me?photoNo="+photoNo);
			}
		}
		

}
