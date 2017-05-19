package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. session uservo 존재하는지 확인(로그인 상태인지 아닌지) 인증여부
				HttpSession session=request.getSession(true);
				
				if(session != null && session.getAttribute("authUser")!=null){
					//로그아웃 처리
					session.removeAttribute("authUser");
					session.invalidate(); 					//세션id를 새로 발급(크롬을 종료시켜 세션을 종료하면 기존의 세션id가 없어짐. 다시 크롬창을 켜면 새로운 세션id가 발급됨.)	
				}
				
				WebUtils.redirect("/mysite/main", request, response); //로그인안하고 들어온 경우 바로 main으로 화면을 넘겨준다

	}

}
