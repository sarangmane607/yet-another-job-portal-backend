package com.yajp.security.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuth2RedirectController {
 
	/*@RequestMapping("/oauth2/redirect")
    public String homePage(RedirectAttributes redirectAttributes, @RequestParam String token) {
		redirectAttributes.addAttribute("token", token);
        return "redirect:/index.html";
    }*/
	
	@RequestMapping("/oauth2/redirect")
    public String homePage(HttpServletResponse response, @RequestParam(required = false) String token, @RequestParam(required = false) String error) {
		if(token != null) {
			response.setHeader("Location", "/oauth2/redirect?token=" + token);
		}else {
			response.setHeader("Location", "/oauth2/redirect?error=" + error);
		}
		return "forward:/index.html";
    }
}