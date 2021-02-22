package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogMain(@PathVariable("id") String id ,Model model) {
		System.out.println("[BlogController blogMain]");
		
		BlogVo blogVo = blogService.getBlog(id);
		
		System.out.println("asd blogVo :  " + blogVo);
		
		if(blogVo == null) {
			return "/error/403";
		}
		
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/blog-main";
	}
	
	@RequestMapping(value = "/{id}/admin/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminForm(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController adminForm]");
		
		BlogVo blogVo = blogService.getBlog(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/basic";
	}
	
	@RequestMapping(value = "/{id}/fileUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileUpdate(@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[BlogController fileUpdate]");
		 
		blogService.fileUpdate(blogVo, file);
		
		
		return "redirect:/"+ blogVo.getId() + "/admin/basic";
	}
	
	public String category() {
		
		return "";
	}
	
}
