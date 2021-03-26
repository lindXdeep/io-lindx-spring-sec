package io.lindx.sec;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import io.lindx.sec.config.WebMvcConfig;
import io.lindx.sec.config.SecurityConfig;

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
    return new Class<?>[] { WebMvcConfig.class, SecurityConfig.class };
  }

  /**
   * Front controller.
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
  }
}
