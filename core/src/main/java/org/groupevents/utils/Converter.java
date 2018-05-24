package org.groupevents.utils;

import org.springframework.stereotype.Component;

@Component
public class Converter {
public String convertNameToUrlPath(String name) {
	String converted = name.toLowerCase();
	converted = converted.replaceAll("\\s+|_+|-+","-").replaceAll("ü","ue").replaceAll("ö","oe")
			.replaceAll("ü", "ue");
	
	converted = converted.replaceAll("[^A-Za-z\\d-]","");
	return converted;
}

}
