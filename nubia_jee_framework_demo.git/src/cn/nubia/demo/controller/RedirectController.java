package cn.nubia.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redirect")
public class RedirectController  {
	
	
	@RequestMapping("/goto")
	@ResponseBody
	public String  savePress(HttpServletRequest request,Model model) {
		 String url = request.getParameter("url");
		 return "success";
	}
}
