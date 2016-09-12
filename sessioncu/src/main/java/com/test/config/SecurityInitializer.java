package com.test.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Order(100)
public class SecurityInitializer extends
		AbstractSecurityWebApplicationInitializer {

}
