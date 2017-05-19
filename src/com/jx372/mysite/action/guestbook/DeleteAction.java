package com.jx372.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.EmailListDao;
import com.jx372.mysite.vo.EmailListVo;
import com.jx372.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tempno=request.getParameter("no");
		Long no=Long.parseLong(tempno);
		String pwd = request.getParameter("pwd");
		String name=request.getParameter("name");

		//System.out.println(no+" "+pwd);

		EmailListVo vo = new EmailListVo();
		vo.setNo(no);
		vo.setPwd(pwd);
		vo.setName(name);

		new EmailListDao().delete(no, pwd, name);

		//redirect 응답
		response.sendRedirect(request.getContextPath()+"/guestbook"); 

	}

}
