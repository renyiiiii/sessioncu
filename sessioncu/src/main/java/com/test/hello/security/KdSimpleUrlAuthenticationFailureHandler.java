package com.test.hello.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class KdSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	public static String SESSION_FAIL_TIMES="SPRING_SESSION_FAIL_TIMES";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 HttpSession session = request.getSession(false);

         if (session != null ) {
        	 Object attribute = session.getAttribute(SESSION_FAIL_TIMES);
        	 Integer times;
        	 if(attribute!=null){
        		 times = (Integer) attribute;
        		 times++;
        	 }else{
        		 times = 1;
        	 }
             request.getSession().setAttribute(SESSION_FAIL_TIMES, times);
         }
		super.onAuthenticationFailure(request, response, exception);
	}

}
