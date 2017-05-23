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

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		if (session == null) {
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
		}

		Long userno = authUser.getNo();
		Long bno = 0L;
		int gno = 0;
		int ono = 0;
		int depth = 0;

		if (request.getParameter("bno") != null) {
			
			bno = Long.parseLong(request.getParameter("bno"));
			gno = Integer.parseInt(request.getParameter("gno"));
			ono = Integer.parseInt(request.getParameter("ono"));
			depth = Integer.parseInt(request.getParameter("depth"));
				
		} 
		request.setAttribute("userno", userno);
		request.setAttribute("bno", bno);
		request.setAttribute("gno", gno);
		request.setAttribute("ono", ono);
		request.setAttribute("depth", depth);

//		System.out.println(
//				"userno : " + userno + " 게시판번호 : " + bno + " 부모그룹번호 : " + gno + " 순서번호 : " + ono + " 깊이 : " + depth);
		WebUtils.forward("/WEB-INF/views/board/write.jsp", request, response);

	}

}
