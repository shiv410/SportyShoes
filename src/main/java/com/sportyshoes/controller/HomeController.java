package com.sportyshoes.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping({ "/", "/home" })
	public String home(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (Objects.isNull(session.getAttribute("cartCount")))
			session.setAttribute("cartCount", 0);
		return "home";
	}

}
