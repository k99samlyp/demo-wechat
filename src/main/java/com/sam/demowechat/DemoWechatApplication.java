package com.sam.demowechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class DemoWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWechatApplication.class, args);
    }
}
