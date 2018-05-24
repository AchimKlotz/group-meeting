package org.groupevents.controller.group;

import javax.validation.Valid;

import org.groupevents.controller.utils.ExceptionMessageHandler;
import org.groupevents.dto.GroupDTO;
import org.groupevents.service.data.GroupService;
import org.groupevents.service.exceptions.FieldException;
import org.groupevents.service.exceptions.ServiceException;
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



@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	ExceptionMessageHandler exceptionHandler;
	
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showCreateGroup(Model model) {
		model.addAttribute("group", new GroupDTO());
		
		return "group/group-new";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createGroup(Model model, @Valid @ModelAttribute("group") GroupDTO group,BindingResult bindingResult) {
		try {
			group= groupService.createGroup(group);
		} catch (FieldException e) {
			exceptionHandler.handleException(e,bindingResult);
		}
		if (bindingResult.hasErrors()) {
			return "group/group-new";
		}
		return "redirect:/group/"+group.getId();
	}
	
	@RequestMapping(value="/{groupId}", method=RequestMethod.GET)
	public String editGroup(@PathVariable Long groupId,Model model) {
		model.addAttribute("group",groupService.findGroupById(groupId));
		return "group/group-edit";
	}
	
	@ExceptionHandler(ServiceException.class)
	public String handleError(Exception ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error_message", ex.getMessage());

		return "redirect:/";

	}

}
