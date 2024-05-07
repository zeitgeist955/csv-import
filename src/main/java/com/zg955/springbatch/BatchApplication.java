package com.zg955.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BatchApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
