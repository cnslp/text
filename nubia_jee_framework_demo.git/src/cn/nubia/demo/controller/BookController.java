package cn.nubia.demo.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.BookCategory;
import cn.nubia.demo.model.Press;
import cn.nubia.demo.service.BookService;
import cn.nubia.demo.service.PressService;
import cn.nubia.framework.core.PageBean;
import cn.nubia.framework.core.Pager;
import cn.nubia.framework.util.StringUtil;

@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;

	@Resource
	private PressService pressService;

	@RequestMapping
	@ResponseBody
	public String index() {
		return "This is an default page.";
	}

	@RequestMapping("/listBook")
	public ModelAndView listBook(HttpServletRequest request, Model model) {
		String pageno = request.getParameter("pageno");
		PageBean pageBean = new Pager(StringUtil.isEmpty(pageno) ? 1 : Integer.valueOf(pageno), 10);
		model.addAttribute("list", bookService.findBook(pageBean));
		pageBean.setPageNum("listBook.do?pageno=");
		model.addAttribute("pageBean", pageBean);
		return new ModelAndView("/book/listBook");
	}

	@RequestMapping("/saveBook")
	@ResponseBody
	public String saveBook(HttpServletRequest request, Book book, Model model) {
		String bookId = request.getParameter("bookId");

		// Book book= new Book();

		// book.setName(request.getParameter("name"));
		String pressId = request.getParameter("pressId");
		Press press = new Press();
		press.setPressId(Integer.valueOf(pressId));
		// pressService.get(Press.class,Integer.valueOf(pressId));
		book.setPress(press);

		String categoryId = request.getParameter("categoryId");
		BookCategory category = new BookCategory();
		category.setCategoryId(Integer.valueOf(categoryId));
		// bookService.get(BookCategory.class,Integer.valueOf(categoryId));
		book.setBookCategory(category);

		if (StringUtil.isEmpty(bookId)) {
			bookService.save(book);
		} else {
			bookService.update(book);
		}
		return "success";
	}

	@RequestMapping("/viewBook")
	public ModelAndView viewBook(HttpServletRequest request, Model model) {
		String bookId = request.getParameter("bookId");
		model.addAttribute("listPress", pressService.findPress());
		model.addAttribute("listCategory", bookService.findBookCategory());
		if (!StringUtil.isEmpty(bookId)) {
			model.addAttribute("book", bookService.findByBookId(Integer.valueOf(bookId)));
		}
		return new ModelAndView("/book/viewBook");
	}

	@RequestMapping("/delBook")
	@ResponseBody
	public String delBook(@RequestParam("bookId") Integer bookId, Model model) {
		bookService.delete(Book.class, bookId);
		return "success";
	}
}