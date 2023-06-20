package com.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoes.model.User;
import com.sportyshoes.service.UserService;
import com.sportyshoes.validator.EmailValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	String errMessage;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login/{emailId}")
	public String loginAction(ModelMap map, HttpServletRequest request, HttpSession session, Model model,
			@RequestParam(value = "emailId", required = true) String emailId,
			@RequestParam(value = "password", required = true) String password) {
		if (EmailValidator.isValid(emailId)) {

			User userDB = userService.getUser(emailId, password);

			if (userDB != null) {
				System.out.println("User : " + userDB.getFirstName());

				String userRole;

				if (userDB.getDiscriminatorValue().equals("Admin")) {
					userRole = "Admin";
					map.addAttribute(userRole);
					session = request.getSession();

					session.setAttribute("userEmailId", emailId);
					session.setAttribute("userName", userDB.getFirstName());
					return "redirect:/admin";
				} else {
					userRole = "Customer";

					map.addAttribute(userRole);
					session = request.getSession();

					session.setAttribute("userEmailId", emailId);
					session.setAttribute("userName", userDB.getFirstName());
					model.addAttribute("alert", "logged in...");
					return "redirect:/home";
				}
			} else {
				errMessage = "Invalid Credentials";
				map.addAttribute("errorMessage", errMessage);
				model.addAttribute("error", "Invalid username or password");
				session.setAttribute("errMessage", "Invalid Credentials");
				return "redirect:/login";
			}
		} else {
			errMessage = "Invalid email id";
			map.addAttribute("errorMessage", errMessage);

			System.out.println("################### " + errMessage);

			return "redirect:/login";

		}

	}

	@GetMapping("/logout")
	public String logout(jakarta.servlet.http.HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			return "redirect:/home";
		}
		return "redirect:/home";
	}

	@GetMapping("/login/changePassword/{emailID}")
	public String changePassword(@PathVariable String emailID, HttpServletRequest request) {

		request.setAttribute("emailIdVal", emailID);

		return "changePassword";
	}

	@GetMapping("/login/changePassword")
	public ModelAndView changePassword() {
		ModelAndView modelAndView = new ModelAndView("changePassword");
		// modelAndView.addObject("message", "test data");
		modelAndView.addObject("emailIdVal", "dev@123");
		return modelAndView;
	}

//	@PostMapping("/login/changePassword")
//	public String saveChangePassword(@ModelAttribute("user") User user, HttpServletRequest request) {
//
//		User user1 = userService.getUser(user.getEmailId());
//		System.out.println(user.getEmailId());
//		user1.setPassword(user.getPassword());
//		userService.updateUser(user1);
//		request.setAttribute("changePasswordMsg", "Password changed");
//
//		return "home";
//	}

	@PostMapping("/login/changePassword")
	public String changePass(@RequestParam("password") String password,
			@RequestParam("confirm_password") String confirm_password) {
		System.out.println(password);
		System.out.println(confirm_password);

		return "redirect:/home";
	}

	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register/save")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {

		userService.addUser(user);
		return "home";
	}

}
