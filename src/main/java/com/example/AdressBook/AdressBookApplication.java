package com.example.AdressBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@EnableAspectJAutoProxy
@SpringBootApplication
public class AdressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdressBookApplication.class, args);
	}

}
