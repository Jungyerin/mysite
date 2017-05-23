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
		Long bno = 0L;
		int gno = 0;
		int ono = 0;
		int depth = 0;
		

		BoardVo vo = new BoardVo();

		vo.setUserno(no);
		vo.setTitle(title);
		vo.setContent(content);
		
		if (!request.getParameter("bno").equals("0")) {
			
			bno = Long.parseLong(request.getParameter("bno"));
			gno = Integer.parseInt(request.getParameter("gno"));
			ono = Integer.parseInt(request.getParameter("ono"));
			depth = Integer.parseInt(request.getParameter("depth"));
			vo.setNo(bno);
			vo.setGno(gno);
			vo.setOno(ono);
			vo.setDepth(depth);
			new BoardDao().insertC(vo);
		} 
		else
		{
			new BoardDao().insert(vo);
		}

//		//System.out.println(
//				"writeAction userno : " + no + " 게시판번호 : " + bno + " 부모그룹번호 : " + gno + " 순서번호 : " + ono + " 깊이 : " + depth);

		WebUtils.redirect(request.getContextPath() + "/board", request, response);

	}

}
