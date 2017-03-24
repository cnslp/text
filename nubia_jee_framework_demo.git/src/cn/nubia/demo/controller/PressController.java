package cn.nubia.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.nubia.demo.model.Press;
import cn.nubia.demo.service.PressService;
import cn.nubia.framework.util.StringUtil;

@Controller
@RequestMapping("/press")
public class PressController {

	@Resource
	private PressService pressService;
	
	
	@RequestMapping("/listPress")
	public ModelAndView listPress(Model model) {
		model.addAttribute("list", pressService.findPress());
		return new ModelAndView("/press/listPress");
	}

	@RequestMapping("/savePress")
	@ResponseBody
	public String  savePress(HttpServletRequest request,Press press,Model model) {
		String pressId=request.getParameter("pressId");
		if(StringUtil.isEmpty(pressId)){
			pressService.save(press);
		}else{
			pressService.update(press);
		}
		 return "success";
	}

	@RequestMapping("/viewPress")
	public ModelAndView viewPress(HttpServletRequest request,Model model) {
		String pressId=request.getParameter("pressId");
		if(!StringUtil.isEmpty(pressId)){
			model.addAttribute("press", pressService.get(Press.class,Integer.valueOf(pressId)));
		}
		return new ModelAndView("/press/viewPress");
	}

	@RequestMapping("/delPress")
	@ResponseBody
	public String  delPress(@RequestParam("pressId") Integer pressId,Model model) {
		pressService.delete(Press.class, pressId);
		return "success";
	}
}