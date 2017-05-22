package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no = Long.valueOf(request.getParameter("userno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();

		vo.setUserno(no);
		vo.setTitle(title);
		vo.setContent(content);

		new BoardDao().insert(vo);

		WebUtils.redirect(request.getContextPath() + "/board", request, response);

	}

}
