package com.simple.test;

import java.io.File;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simple.test.utils.Constants;

@SpringBootApplication
public class SimpleTestLuisSerratoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTestLuisSerratoApplication.class, args);
	}

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Mexico/General"));
	}

	@PostConstruct
	public void createRoot() {
		File userDir = new File(Constants.ROOT_PATH);
		userDir.mkdirs();
	}

}
