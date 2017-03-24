package cn.nubia.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.nubia.demo.model.Author;
import cn.nubia.demo.service.AuthorService;
import cn.nubia.framework.util.StringUtil;

@Controller
@RequestMapping("/author")//在类前 是相对应web的根目录
public class AuthorController {

	@Resource  //将authorService注入
	private AuthorService authorService;
	
	
	@RequestMapping("/listAuthor")	//对于listAutor这个请求调用这个方法
	public ModelAndView listAuthor(Model model) {
		model.addAttribute("list", authorService.findAuthor());			//往前台视图传参数，类似于request.setAttribute("key",key对应的value)效果一样
		return new ModelAndView("/author/listAuthor");		//跳转到/author/listAuthor的url去    自己跳转自己
	}

	@RequestMapping("/saveAuthor")
	@ResponseBody
	public String  saveAuthor(HttpServletRequest request,Author author,Model model) {
		String authorId=request.getParameter("authorId");
		if(StringUtil.isEmpty(authorId)){
			authorService.save(author);
		}else{
			authorService.update(author);
		}
		 return "success";
	}

	@RequestMapping("/viewAuthor")
	public ModelAndView viewAuthor(HttpServletRequest request,Model model) {
		String authorId=request.getParameter("authorId");
		if(!StringUtil.isEmpty(authorId)){
			model.addAttribute("author", authorService.get(Author.class,Integer.valueOf(authorId)));
		}
		return new ModelAndView("/author/viewAuthor");
	}

	@RequestMapping("/delAuthor")
	@ResponseBody
	public String  delAuthor(@RequestParam("authorId") Integer authorId,Model model) {
		authorService.delete(Author.class,authorId);
		return "success";
	}
}