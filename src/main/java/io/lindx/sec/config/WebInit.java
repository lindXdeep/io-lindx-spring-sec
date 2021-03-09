package io.lindx.sec.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet.
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Root context.
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  /**
   * Local context.
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebConfig.class };
  }

  /**
   * Front controller.
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/*" };
  }
}
