package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
    public String index (Model model)
    {
    		model.addAttribute("title", "Index");
        return "index";
    }
	
	@RequestMapping("/login")
    public String login ()
    {
        return "login";
    }
	
	@RequestMapping("/logout")
    public String logout ()
    {
        return "login";
    }
	
}
