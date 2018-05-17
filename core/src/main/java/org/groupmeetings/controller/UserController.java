package org.groupmeetings.controller;

import org.groupmeetings.dto.UserDTO;
import org.groupmeetings.service.UserService;
import org.groupmeetings.utils.DateFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable long userId, Model model) {
		UserDTO user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "profile-show";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public  String signup(Model model) {
		
		model.addAttribute("maxYear", 
				DateFunctions.maxAllowedYear());
				
		model.addAttribute("user", new UserDTO());
		return "signup";
	}
}
