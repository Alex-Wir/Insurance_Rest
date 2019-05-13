package com.it.app.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

/**
 * Web configuration class, imports the Spring MVC configuration
 */
@EnableWebMvc
public class WebConfiguration {

    /**
     * Create Dozer Mapper Bean
     * Add configuration from dozerJdk8Converters.xml to mapping LocalDateTime
     *
     * @return - Dozer Mapper instance
     */
    @Bean
    public Mapper mapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return beanMapper;
    }

    /**
     * Create BCryptPasswordEncoder Bean for encoding users' passwords
     *
     * @return - BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}