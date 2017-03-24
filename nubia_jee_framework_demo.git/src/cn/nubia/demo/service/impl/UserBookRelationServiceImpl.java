package cn.nubia.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.nubia.demo.model.Book;
import cn.nubia.demo.model.UserBookRelation;
import cn.nubia.demo.service.UserBookRelationService;
import cn.nubia.framework.core.BaseService;

@Service("userBookRelationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserBookRelationServiceImpl extends BaseService implements UserBookRelationService {
	
	public List<UserBookRelation> findBookByUserId(int userId) {
		return dao.find("from UserBookRelation ub left join fetch ub.user u left join fetch ub.book b where u.userId=?",userId);
	}
	
	public List<Book> findOtherBookByUserId(int userId) {
		return dao.find("from Book bb where not exists(from UserBookRelation ub left join ub.user u left join ub.book b where u.userId=? and bb.bookId=b.bookId)",userId);
	}
}