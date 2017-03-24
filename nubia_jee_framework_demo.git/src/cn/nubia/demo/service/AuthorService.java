package cn.nubia.demo.service;

import java.util.List;

import cn.nubia.demo.model.Author;
import cn.nubia.framework.core.Service;

public interface AuthorService extends Service {

	public List<Author> findAuthor();
}