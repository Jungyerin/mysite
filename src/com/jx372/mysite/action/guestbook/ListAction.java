package com.jx372.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.EmailListDao;
import com.jx372.mysite.vo.EmailListVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<EmailListVo> list=new EmailListDao().getList();
		
		request.setAttribute("list", list);										//list를 뽑아서 값을 포워딩
		WebUtils.forward("/WEB-INF/views/guestbook/list.jsp", request, response);

	}

}
