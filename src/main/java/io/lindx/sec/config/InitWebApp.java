package io.lindx.sec.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet.
 */
@Order(1)
public class InitWebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Root context.
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {};
  }

  /**
   * Local context.
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { ConfigWebApp.class, ConfigSecurity.class };
  }

  /**
   * Front controller.
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }
}
