package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("This is basic user LIST/ADD/EDIT/DELETE service");
		messages.add("You can check the list of available users by clicking link below");
		model.addAttribute("messages", messages);
		return "index";
	}
	
}