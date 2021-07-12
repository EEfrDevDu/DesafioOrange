package br.com.marvelstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarvelStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelStoreApplication.class, args);
	}

}
