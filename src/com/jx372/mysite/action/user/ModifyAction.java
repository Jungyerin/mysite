package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no=Long.valueOf(request.getParameter("no"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");

		
		String gender=request.getParameter("gender");
		
		UserVo vo=new UserVo();
		
		vo.setName(name);
		vo.setEmail(email);
		vo.setGender(gender);
		

		//System.out.println("name : "+vo.getName()+"gender :  "+vo.getGender());
		System.out.println("pw확인 : "+vo.getPwd());
		
		if(pwd==""){
			//System.out.println("pw null");
			new UserDao().update(vo, no);
		
		}
		else{
			
			vo.setPwd(pwd);
			//System.out.println("pw change");
			new UserDao().updatepw(vo,no);
			
		}
	
		
		WebUtils.redirect(request.getContextPath()+"/user", request, response);

	}

}
