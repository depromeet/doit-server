package com.server.doit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value="/") public void homeController() {
		System.out.println("서버 구동 확인");
	}
	@GetMapping(value="/doit/admin") public String adminController() {
		System.out.println("admin");
		return "main";
	}
}
