package org.groupevents.utils;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.groupevents.utils.LocaleFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LocaleFunctionsTest {
	@Autowired
	LocaleFunctions localeFunctions;
	
	

	@Test
	public void getAllCountriesNotNull() {
		Map<String, String> countries = localeFunctions.getAllCountries();
		assertNotNull(countries);
		assertNotNull(countries.entrySet());

	}
}
