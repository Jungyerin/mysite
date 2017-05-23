package com.jx372.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class EncodingFilter implements Filter {

	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("encoding fileter initialize...");
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null){				//default charset
			encoding = "UTF-8";
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//request에 대한 처리
		request.setCharacterEncoding(encoding); //톰캣에서 어노페이션을 지원해주지만 지원해주지 않는 경우가 있기 때문에 확실히 하기 위해서는 xml파일에 설정해준다.(톰캣에서만 돌아가기때문에 이식성이 떨어짐.)
		chain.doFilter(request, response);
		
		//response에 대한 처리
		
	}

	public void destroy() {

	}

}
