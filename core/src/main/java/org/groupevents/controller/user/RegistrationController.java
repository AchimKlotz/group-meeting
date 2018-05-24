package org.groupevents.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.groupevents.dto.UserSignupDTO;
import org.groupevents.service.data.UserService;
import org.groupevents.service.exceptions.ActivationKeyException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class RegistrationController {
	@Autowired
	UserService userService;

	@Autowired
	PasswordValidator passwordValidator;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new UserSignupDTO());
		return "user/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupSubmit(@Valid @ModelAttribute("user") UserSignupDTO user, BindingResult bindingResult,
			Model model) {

		passwordValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {

			return "user/signup";
		}

		UserSignupDTO createdUser = userService.createUser(user);
		return "redirect:/user/" + user.getId();

	}

	@RequestMapping(value = "/user/activate/{activationKey}", method = RequestMethod.GET)
	public String activateUser(@PathVariable String activationKey, RedirectAttributes redirectAttributes) {
		userService.activateUser(activationKey);
		redirectAttributes.addFlashAttribute("success_message",
				"Ihr Benutzer wurde erfolgreich aktiviert.\nSie können Sie jetzt einloggen.");
		return "redirect:/";
	}

	@RequestMapping(value = "user/request_activation", method = RequestMethod.GET)
	public String requestNewActivationKey() {
		return "user/requestActivationKey";
	}

	@RequestMapping(value = "user/request_activation", method = RequestMethod.POST)
	public String requestNewActivationKeyPost(@RequestParam String email) {
		userService.requestNewActivationKey(email);
		return "redirect:/";
	}

	@ExceptionHandler(ServiceException.class)
	public String handleError(Exception ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error_message", ex.getMessage());

		return "redirect:/";

	}

	@ExceptionHandler(ActivationKeyException.class)
	public String handleActivationKeyError(Exception ex, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("error_message", ex.getMessage());

		return "redirect:" + request.getHeader("referer");

	}
}
