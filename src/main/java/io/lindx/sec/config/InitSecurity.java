package io.lindx.sec.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class InitSecurity extends AbstractSecurityWebApplicationInitializer {
  
}
