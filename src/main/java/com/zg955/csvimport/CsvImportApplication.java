package com.zg955.csvimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvImportApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CsvImportApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
