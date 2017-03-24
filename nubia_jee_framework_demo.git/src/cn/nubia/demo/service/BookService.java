package cn.nubia.demo.service;

import java.util.List;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.BookCategory;
import cn.nubia.framework.core.PageBean;
import cn.nubia.framework.core.Service;

public interface BookService extends Service {

	public List<Book> findBook(PageBean pageBean);
	
	public Book findByBookId(int bookId);
	
	public Book findByBookId2(int bookId);
	
	public List<BookCategory> findBookCategory();
	
	public BookCategory findByCategoryId(int categoryId);
}