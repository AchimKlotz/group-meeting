package org.groupevents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	

	@RequestMapping(path="/" ,method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
}