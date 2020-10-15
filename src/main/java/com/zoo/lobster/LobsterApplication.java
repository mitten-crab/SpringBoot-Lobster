package com.zoo.lobster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LobsterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LobsterApplication.class, args);
    }

}
