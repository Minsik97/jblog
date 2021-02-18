package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogMain(@RequestParam("id")String id ,Model model) {
		System.out.println("[BlogController blogMain]");
		model.addAttribute("id", id);
		
		return "/blog/blog-main";
	}
	
}
