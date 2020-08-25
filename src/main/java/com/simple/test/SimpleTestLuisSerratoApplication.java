package com.simple.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.CSVWriter;
import com.simple.test.utils.Constants;
import com.simple.test.utils.Utils;

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
	public void createRoot() throws IOException {
		File rootDir = new File(Constants.ROOT_PATH);
		rootDir.mkdirs();

		if (rootDir.listFiles().length == 0) {

			File userDir = new File(Constants.ROOT_PATH.concat("1066"));
			userDir.mkdirs();

			for (int i = 0; i < 6; i++) {
				File newTransaction = new File(Constants.ROOT_PATH.concat("1066"),
						Utils.generateUuid().concat(Constants.CSV_EXTENSION));
				newTransaction.getAbsoluteFile().createNewFile();

				FileWriter output = new FileWriter(newTransaction);
				CSVWriter writer = new CSVWriter(output, ',', CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

				writer.writeNext(Constants.CSV_HEADERS);

				writer.writeNext(new String[] { "1066", "120.30", "DesTransac", Constants.INIT_DATES[i] });
				writer.close();

			}

		}
	}

}
