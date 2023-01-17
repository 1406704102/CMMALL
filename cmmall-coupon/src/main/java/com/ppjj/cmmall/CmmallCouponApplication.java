package com.ppjj.cmmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CmmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmmallCouponApplication.class, args);
    }

}
