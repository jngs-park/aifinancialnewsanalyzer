package com.jp.financialnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancialnewsaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialnewsaiApplication.class, args);
	}
}