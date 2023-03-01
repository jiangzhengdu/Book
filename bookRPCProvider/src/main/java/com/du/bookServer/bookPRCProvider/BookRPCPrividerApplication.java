package com.du.bookServer.bookPRCProvider;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/du/bookServer/bookPRCProvider/mapper")
public class BookRPCPrividerApplication {
    private static final Logger logger = LoggerFactory.getLogger(BookRPCPrividerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookRPCPrividerApplication.class, args);
        logger.info("ProviderApplication start!");
    }
}
