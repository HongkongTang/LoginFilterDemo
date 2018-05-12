package com.imooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//System.out.println("------------sdafdsadfa------------");
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = request.getSession();
		String noLoginPaths = config.getInitParameter("noLoginPaths");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(noLoginPaths!=null){
			String[] paths = noLoginPaths.split(";");
			for(String path:paths ){
				if(path==null||path.length()==0)continue;
				if(request.getRequestURI().indexOf(path)!=-1){
					arg2.doFilter(arg0, arg1);
					return ;
				}
			}
		}
		//session.invalidate();
		/*
		if(request.getRequestURI().indexOf("login.jsp")!=-1){
			arg2.doFilter(arg0, arg1);
			return ;
		}
		if(request.getRequestURI().indexOf("LoginServlet")!=-1){
			arg2.doFilter(arg0, arg1);
			return ;
		}*/
		if(session.getAttribute("username")!=null){
			arg2.doFilter(arg0, arg1);
		}else{
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		config = arg0;
	}

}
