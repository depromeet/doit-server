package com.server.doit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value="/") public String homeController() {
		System.out.println("확인");
		return "main";
	}
}
