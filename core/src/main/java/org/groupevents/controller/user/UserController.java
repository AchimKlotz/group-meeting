package org.groupevents.controller.user;

import javax.validation.Valid;

import org.groupevents.dto.UserDTO;
import org.groupevents.dto.UserSignupDTO;
import org.groupevents.service.data.UserService;
import org.groupevents.service.exceptions.ServiceException;
import org.groupevents.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable long userId, Model model) {
		UserDTO user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user/user-edit";
	}

	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("success_message", "Die Daten wurden erfolgreich gespeichert");
		return "redirect:/user/" + user.getId();
	}

	@ExceptionHandler(ServiceException.class)
	public String handleError(Exception ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error_message", ex.getMessage());

		return "redirect:/";
	}
}
