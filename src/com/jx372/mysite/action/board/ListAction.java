package com.jx372.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 페이지 갯수 등등 넘겨야 할 정보가 많음
		
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

		List<BoardVo> list = new BoardDao().getList();

		Long no=authUser.getNo();
		request.setAttribute("no", no);
		request.setAttribute("list", list);

		WebUtils.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}
