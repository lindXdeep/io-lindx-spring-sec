package io.lindx.sec.config;

import javax.servlet.Filter;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet.
 */
@Order(1)
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

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
    return new Class<?>[] { WebConfig.class, SecurityConfig.class };
  }

  /**
   * Front controller.
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  /**
   * HiddenFieldFilter-getServletFilters.
   */
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
  }
}
