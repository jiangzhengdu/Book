package com.du.bookServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class bookServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(bookServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(bookServerApplication.class, args);
        logger.info("Book Application start!");

    }
}
