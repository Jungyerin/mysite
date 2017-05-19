package com.jx372.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿으로 프로그램을 할때 반복되는 메소드를 static으로 만들어서 사용.
public class WebUtils {
	
	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		response.sendRedirect(url);
	}
	
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		RequestDispatcher rd=request.getRequestDispatcher(path);	//내부에서 웹info에 접근
		rd.forward(request, response);
		
	}

	
}
