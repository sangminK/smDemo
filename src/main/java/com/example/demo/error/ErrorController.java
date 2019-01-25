package com.example.demo.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/error")
public class ErrorController {
	
	@RequestMapping("/404") 
	public String error404(HttpServletRequest request) { return "404"; } 
	
	@RequestMapping("/default") 
	public String errorDefault(HttpServletRequest request) { return "error"; }


}
