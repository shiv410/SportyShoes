package com.sportyshoes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sportyshoes.model.User;
import com.sportyshoes.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getUserList();
	}

	@RequestMapping("/user-list")
	public List<User> userList(Model model) {
		List<User> users = userService.getUserList();
		model.addAttribute("users", users);
		return users;
	}

	@GetMapping(path = "/users/{emailId}")
	public User getUser(@PathVariable String emailId) {
		User user = userService.getUser(emailId);

		if (user == null) {
			throw new RuntimeException("id null");// user not foundexception
		}
		return user;
	}

	@PostMapping(path = "/users") // Request body --> json data
	public ResponseEntity<User> createUser(@RequestBody User user) {

		System.out.println(user.getFirstName());

		User SaveUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(SaveUser.getEmailId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// PUT
	@PutMapping(value = "createUser")
	public User createUser2(@RequestBody User userDataInput) {
		return userService.addUser(userDataInput);
	}

	// update
	@PutMapping(value = "/users/{emailId}")
	public void updateUser(@PathVariable String emailId, @RequestBody User userDataInput) {
		userService.updateUser(userDataInput);
	}

	@GetMapping("/goToViewPage")
	public ModelAndView passParametersWithModelAndView() {
		ModelAndView modelAndView = new ModelAndView("UserList");
		modelAndView.addObject("message", "test data");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("message", "test data");

		return modelAndView;
	}

}
