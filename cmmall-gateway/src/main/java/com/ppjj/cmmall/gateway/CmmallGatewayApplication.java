package com.ppjj.cmmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CmmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmmallGatewayApplication.class, args);
    }

}
