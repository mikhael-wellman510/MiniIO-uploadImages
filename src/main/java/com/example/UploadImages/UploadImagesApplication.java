package com.example.UploadImages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UploadImagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadImagesApplication.class, args);
	}

}
