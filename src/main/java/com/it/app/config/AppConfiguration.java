package com.it.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.it.app")
@Import({DataBaseConfiguration.class, WebConfiguration.class,
        MessagesConfiguration.class, SecurityConfiguration.class, SwaggerConfiguration.class})
public class AppConfiguration {

}
