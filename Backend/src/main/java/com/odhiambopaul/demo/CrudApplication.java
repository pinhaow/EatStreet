package com.odhiambopaul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan( excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.odhiambopaul\\.demo\\.websocketapp.*")
})
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

}
