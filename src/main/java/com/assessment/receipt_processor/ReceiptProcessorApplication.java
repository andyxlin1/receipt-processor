package com.assessment.receipt_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptProcessorApplication.class, args);
	}

}
