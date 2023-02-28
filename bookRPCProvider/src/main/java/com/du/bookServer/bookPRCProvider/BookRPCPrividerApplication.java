package com.du.bookServer.bookPRCProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRPCPrividerApplication {
    private static final Logger logger = LoggerFactory.getLogger(BookRPCPrividerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookRPCPrividerApplication.class, args);
        logger.info("ProviderApplication start!");
    }
}
