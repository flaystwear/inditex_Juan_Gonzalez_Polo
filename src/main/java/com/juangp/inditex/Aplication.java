package com.juangp.inditex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Aplication {
    private static final Logger logger = LoggerFactory.getLogger(Aplication.class);
    public static void main(String[] args) {
        SpringApplication.run(Aplication.class, args);
        logger.info("\n------------------APPLICATION STARTED-----------------\n");
    }
}