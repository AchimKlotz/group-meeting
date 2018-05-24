package org.groupevents.controller;



import org.groupevents.dto.SimpleValue;
import org.groupevents.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilsController {
	
	@Autowired
	Converter converter;
	@RequestMapping(value="/convert-name-to-url",method=RequestMethod.POST
			,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	
	public SimpleValue convertNameToUrl(@RequestParam String name) {
	  return SimpleValue.builder().value(converter.convertNameToUrlPath(name)).build();
	}
	

}
