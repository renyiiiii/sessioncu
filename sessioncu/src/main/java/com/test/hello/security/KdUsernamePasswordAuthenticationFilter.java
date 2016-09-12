package com.test.hello.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class KdUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	 private boolean postOnly = true;
	 public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        if (this.postOnly && !request.getMethod().equals("POST")) {
	            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	        }

	        String username = obtainUsername(request);
	        String password = obtainPassword(request);
	        String type = request.getParameter("j_type");

	        if (username == null) {
	            username = "";
	        }

	        if (password == null) {
	            password = "";
	        }
	        
	        if (type == null) {
	        	type = "1";
	        }

	        username = username.trim();

	        Authentication authRequest;
	        if(type.equals("1")){
	        	 authRequest = new UsernamePasswordAuthenticationToken(username, password);
	        }else{
	        	 authRequest = new KdUsernamePasswordAuthenticationToken(username, password,type);
	        }
	       
	        
	       

	        // Allow subclasses to set the "details" property
	        setDetails(request, (AbstractAuthenticationToken)authRequest);

	        return this.getAuthenticationManager().authenticate(authRequest);
	    }
	 
	    /**
	     * Provided so that subclasses may configure what is put into the authentication request's details
	     * property.
	     *
	     * @param request that an authentication request is being created for
	     * @param authRequest the authentication request object that should have its details set
	     */
	    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
	        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	    }



}
