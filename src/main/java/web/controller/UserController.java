package web.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public String userList(@NotNull Model model) {
		model.addAttribute("userList", userService.userList());
		return "list";
	}
	
	@GetMapping("/users/{id}")
	public String getUser(@PathVariable("id") Long id, @NotNull Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "show";
	}
	@GetMapping("/users/new")
	public String addUser(@ModelAttribute("user") User user) {
		return "new";
	}
	
	@PostMapping("/users/new")
	public String add(@ModelAttribute("user") @Valid User user, @NotNull BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new";
		} else {
			userService.addUser(user);
			return "redirect:/users";
		}
	}
	
	@GetMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") Long id, @NotNull Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "edit";
	}
	@PatchMapping("/users/edit")
	public String update(@ModelAttribute("user") @Valid User user, @NotNull BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit";
		} else {
			userService.updateUser(user);
			return "redirect:/users";
		}
	}

	@DeleteMapping("/users/delete/{id}")
	public String removeUser(@PathVariable("id") Long id) {
		userService.removeUser(id);
		return "redirect:/users";
	}
}
