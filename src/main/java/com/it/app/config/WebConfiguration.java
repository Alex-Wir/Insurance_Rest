package com.it.app.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@EnableWebMvc
public class WebConfiguration {
    @Bean
    public Mapper mapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return beanMapper;
    }
}