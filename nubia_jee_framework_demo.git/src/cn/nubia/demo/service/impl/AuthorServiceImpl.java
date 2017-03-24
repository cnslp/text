package cn.nubia.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.nubia.demo.model.Author;
import cn.nubia.demo.service.AuthorService;
import cn.nubia.framework.core.BaseService;

@Service("authorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorServiceImpl extends BaseService implements AuthorService {


	public List<Author> findAuthor() {
		return dao.find("from Author");
	}
}