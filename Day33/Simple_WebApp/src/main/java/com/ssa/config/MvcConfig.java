package com.ssa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ssa")
public class MvcConfig extends WebMvcConfigurerAdapter {
   
    @Bean
   public SpringTemplateEngine getTemplateEngine() {
       SpringTemplateEngine templateEngine = new SpringTemplateEngine();
       templateEngine.setTemplateResolver(getTemplateResolver());
       return templateEngine;
   }
   
   @Bean
   public ThymeleafViewResolver getViewResolver() {
       ThymeleafViewResolver resolver = new ThymeleafViewResolver();
       resolver.setTemplateEngine(getTemplateEngine());
       return resolver;
   }
   
   @Bean
   public SpringResourceTemplateResolver getTemplateResolver() {
       SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

       templateResolver.setPrefix("/WEB-INF/View/");
       templateResolver.setSuffix(".html");
       templateResolver.setTemplateMode("HTML5");
       templateResolver.setCacheable(false);
       return templateResolver;
   }

	}
