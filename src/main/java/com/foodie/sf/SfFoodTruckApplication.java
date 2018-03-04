package com.foodie.sf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan
@SpringBootApplication
public class SfFoodTruckApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SfFoodTruckApplication.class, args);

	}

}
