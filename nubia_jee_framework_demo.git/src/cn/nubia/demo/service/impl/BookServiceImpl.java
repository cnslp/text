package cn.nubia.demo.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.BookCategory;
import cn.nubia.demo.service.BookService;
import cn.nubia.framework.core.BaseService;
import cn.nubia.framework.core.PageBean;
import cn.nubia.framework.core.SessionCallback;

@Service("bookService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl extends BaseService implements BookService {


	public List<Book> findBook(PageBean pageBean) {
		//return dao.find("from Book b left join fetch b.press p");
		long totalCount =(Long) dao.unique("select count(*) from Book b");
    	pageBean.setTotalCount(totalCount);
    	return dao.findPaginated("from Book b left join fetch b.press p left join fetch b.bookCategory bc order by b.bookId desc",pageBean.getPageno(),pageBean.getPagesize());
    	
	}

	@Override
	public Book findByBookId(int bookId) {
		List<Book> list=dao.find("from Book b left join fetch b.press p left join fetch b.bookCategory where b.bookId=?",bookId);
		return list.isEmpty()?null:list.get(0);
	}
	
	@Override
	public Book findByBookId2(final int bookId) {
		Object obj=dao.execute(new SessionCallback(){
			@Override
			public Object doInSession(Session session) {
				return session.createSQLQuery("select name from tbl_book where book_id=?").setInteger(0, bookId).uniqueResult();
			}});
		Book book=new Book();
		book.setBookId(bookId);
		book.setName((String)obj);
		return book;
	}

	
	@Override
	public List<BookCategory> findBookCategory() {
		return dao.find("from BookCategory bc");
	}

	@Override
	public BookCategory findByCategoryId(int categoryId) {
		List<BookCategory> list=dao.find("from BookCategory bc where bc.categoryId=?",categoryId);
		return list.isEmpty()?null : list.get(0);
	}
}