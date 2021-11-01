package com.libreapp.store.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class MsStoreCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreCustomerApplication.class, args);
		log.info("--> Load Complete!!!");
	}

}
