package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. session uservo 존재하는지 확인(로그인 상태인지 아닌지) 인증여부
		HttpSession session=request.getSession();
		if(session == null){
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
		}
		
		Long bno=Long.parseLong(request.getParameter("bno"));
		BoardVo boardvo = new BoardDao().get(bno);
		Long userNo=boardvo.getUserno();
		Long no=authUser.getNo();						//수정작성하기
		
		if(userNo!=no){
			WebUtils.redirect("/mysite/board", request, response);
			return;
		}
		
		request.setAttribute("boardvo", boardvo);										
		WebUtils.forward("/WEB-INF/views/board/modify.jsp", request, response);
		
		
	}

}
