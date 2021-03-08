package io.lindx.sec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webconfig.
 */
@Configuration
@ComponentScan(value = "io.lindx.sec")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  
}
