package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		
		UserVo vo =new UserDao().get(email, pwd);
		System.out.println(email+" "+pwd);
		if(vo==null){ //인증실패
			//WebUtils.redirect("/mysite/user?a=loginform&result=fail", request, response);
			request.setAttribute("result","fail");
			WebUtils.forward("/WEB-INF/views/user/loginform.jsp", request, response);
		}
		
		//인증처리
		HttpSession session=request.getSession(true);		//로그인한 상태로 세션을 유지함.
		session.setAttribute("authUser", vo);
		
		//System.out.println("인증처리!");
		
		//main redirect
		WebUtils.redirect("/mysite/main", request, response);

		

	}

}
