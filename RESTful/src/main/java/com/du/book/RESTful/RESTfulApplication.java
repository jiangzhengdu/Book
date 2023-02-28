package com.du.book.RESTful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/du/book/RESTful/mapper")
public class RESTfulApplication {
    public static void main(String[] args) {
        SpringApplication.run(RESTfulApplication.class, args);
    }

}
