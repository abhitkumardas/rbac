package com.test.rbac;

import com.test.rbac.service.ApplicationIntializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class RbacApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RbacApplication.class, args);

        ApplicationIntializer applicationIntializer = (ApplicationIntializer) configurableApplicationContext.getBean(ApplicationIntializer.class);
        applicationIntializer.initialize();
    }

}
