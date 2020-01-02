package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "cn.com.demo.mvc.controller", "cn.com.demo.javaweb.shopping.service" })
@MapperScan(basePackages = { "cn.com.demo.javaweb.shopping.dao" })
@SpringBootApplication
public class UsedShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedShoppingApplication.class, args);
	}

}
