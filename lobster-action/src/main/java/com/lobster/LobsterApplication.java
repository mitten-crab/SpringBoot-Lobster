package com.lobster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class LobsterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LobsterApplication.class, args);
    }

}
