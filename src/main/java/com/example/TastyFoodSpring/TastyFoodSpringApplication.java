package com.example.TastyFoodSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class TastyFoodSpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TastyFoodSpringApplication.class, args);
		Logger rootLogger = Logger.getLogger("");
		FileHandler fileHandler;

		{
			try {
				fileHandler = new FileHandler("mylogger.log", true);
				fileHandler.setFormatter(new SimpleFormatter());
				fileHandler.setLevel(Level.INFO);
				rootLogger.addHandler(fileHandler);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		Logger.getLogger("com.example.TastyFoodSpring").log(Level.SEVERE,"System is Running");

	}


}
