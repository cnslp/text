package cn.nubia.demo.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.User;
import cn.nubia.demo.model.UserBookRelation;
import cn.nubia.demo.service.UserBookRelationService;
import cn.nubia.demo.service.UserService;
import cn.nubia.framework.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController  {

	@Resource
	private UserService userService;
	
	@Resource
	private UserBookRelationService userBookRelationService;
	
	@RequestMapping("/listUser")
	public String listUser(HttpServletRequest request) {
		request.setAttribute("list", userService.findUser());
		return "/user/listUser";
	}

	@RequestMapping("/saveUser")
	@ResponseBody
	public String  saveUser(HttpServletRequest request, User user) {
		String userId=request.getParameter("userId");
		if(StringUtil.isEmpty(userId)){
			userService.save(user);
		}else{
			//User _user = userService.get(User.class, Integer.valueOf(request.getParameter("userId")));

			userService.update(user);
		}
		 return "success";
	}


	@RequestMapping("/viewUser")
	public String viewUser(HttpServletRequest request,Model model) {
		String userId=request.getParameter("userId");
		if(!StringUtil.isEmpty(userId)){
			model.addAttribute("user", userService.get(User.class,Integer.valueOf(userId)));
		}
		return "/user/viewUser";
	}

	@RequestMapping("/delUser")
	@ResponseBody
	public String  delUser(@RequestParam("userId") Integer userId,Model model) {
		userService.delete(User.class,userId);
		return "success";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user,Model model) {
		User usr = userService.findUserByName(user.getUsername());
		if (usr != null && usr.getPassword().equals(user.getPassword()))
		{
			model.addAttribute("user", usr);
			model.addAttribute("bookList", userBookRelationService.findBookByUserId(usr.getUserId()));
			model.addAttribute("otherBookList", userBookRelationService.findOtherBookByUserId(usr.getUserId()));
			return "/user/admin";
		}
		return "failed";
	}
	
	@RequestMapping("/borrowBook")
	@ResponseBody
	public String borrowBook(@RequestParam("userId") Integer userId,@RequestParam("bookId") Integer bookId,Model model) {
		UserBookRelation ub=new UserBookRelation();
		User user=new User();
		user.setUserId(userId);
		
		Book book=new Book();
		book.setBookId(bookId);
		
		ub.setUser(user);
		ub.setBook(book);
		ub.setBorrowTime(new Timestamp(System.currentTimeMillis()));
		userBookRelationService.save(ub);
		
		return "success";
	}
	
	@RequestMapping("/returnBook")
	@ResponseBody
	public String returnBook(@RequestParam("returnId") Integer returnId,Model model) {
		userBookRelationService.delete(UserBookRelation.class,returnId);
		
		return "success";
	}
}