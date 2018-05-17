package org.groupmeetings;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
	Locale.setDefault(Locale.GERMANY);
		SpringApplication.run(CoreApplication.class, args);
	}
}
