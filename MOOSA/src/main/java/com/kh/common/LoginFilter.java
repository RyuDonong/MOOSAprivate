package com.kh.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ViewFilter
 */


// 로그인 안하면 못보게 할 주소들
// 예시 @WebFilter({"/insert.bo","/list.bo"})

@WebFilter({"/insert.bo","/update.bo"})
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest htRequest = (HttpServletRequest)request;
		//HttpServletRequest : http의 다양한 정보를 불러올 수 있음.
		HttpSession session = htRequest.getSession();
		
		if(session.getAttribute("loginUser")==null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			((HttpServletResponse)response).sendRedirect(htRequest.getContextPath()+"/loginPage.go");

		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
