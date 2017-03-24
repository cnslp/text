package cn.nubia.demo.service;

import java.util.List;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.UserBookRelation;
import cn.nubia.framework.core.Service;

public interface UserBookRelationService extends Service {
	
	public List<UserBookRelation> findBookByUserId(int userId);
	
	public List<Book> findOtherBookByUserId(int userId);
}