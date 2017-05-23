package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.dao.EmailListDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.EmailListVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
		Long no=authUser.getNo();					

		Long userno = Long.parseLong(request.getParameter("userno"));
		Long bno = Long.parseLong(request.getParameter("bno"));
		BoardVo boardvo = new BoardDao().get(bno);

		request.setAttribute("bno", bno);

		boardvo.setNo(bno);
		boardvo.setUserno(userno);
		
		if(no==userno)
		{
			new BoardDao().delete(bno, userno);
		}

		// redirect 응답
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
