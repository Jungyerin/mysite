package com.jx372.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.action.guestbook.GuestBookActionFactory;
import com.jx372.web.action.Action;

//@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config");
		System.out.println(configPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.getServletContext().getInitParameter("contextConfigLocation");

		//request.setCharacterEncoding("UTF-8");
			
		String actionName=request.getParameter("a");
		Action action=new GuestBookActionFactory().getAction(actionName);
		
		action.execute(request, response);

	}

}
