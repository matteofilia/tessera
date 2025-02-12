package org.tessera_lang.server.TesseraServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesseraServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesseraServerApplication.class, args);
		System.out.println("System is running");
	}

}
