package com.proyecto.devolucion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DevolucionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevolucionApplication.class, args);
	}

}
