package com.test.hello.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KdDaoAuthenticationProvider extends KdAbstractUserDetailsAuthenticationProvider{
	
	private UserDetailsService userDetailsService = new KdJdbcDaoImpl();

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (KdUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			KdUsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		 if (authentication.getCredentials() == null) {
	            logger.debug("Authentication failed: no credentials provided");

	            throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
	        }

	        String presentedPassword = authentication.getCredentials().toString();

	        if (!userDetails.getPassword().equals(presentedPassword)) {
	            logger.debug("Authentication failed: password does not match stored value");

	            throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
	        }else{
	        	KdJdbcDaoImpl.userpass.remove(userDetails.getUsername());
	        }
		
	}
	
	

	

	@Override
	protected UserDetails retrieveUser(String username,
			KdUsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		 UserDetails loadedUser;

	        try {
	            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
	        } catch (UsernameNotFoundException notFound) {
	            throw notFound;
	        } catch (Exception repositoryProblem) {
	            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
	        }

	        if (loadedUser == null) {
	            throw new InternalAuthenticationServiceException(
	                    "UserDetailsService returned null, which is an interface contract violation");
	        }
	        return loadedUser;
	}
	
	
}
