package com.jx372.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
	
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
        //어플리케이션이 올라갈때
    	//어플리케이션 전체적으로 사용하는 객체가 있으면 여기서 초기화 해준다.
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	System.out.println("컨테이너 시작 하였습니다. - "+contextConfigLocation);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	//어플리케이션이 내려갈때
    	
         
    }


	
}
