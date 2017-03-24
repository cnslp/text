package cn.nubia.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.nubia.demo.model.User;
import cn.nubia.demo.service.UserService;
import cn.nubia.framework.core.BaseService;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends BaseService implements UserService {


	public List<User> findUser() {
		return dao.find("from User");
	}
	
	public User findUserByName(String userName) {
		List<User> list = dao.find("from User where username=?", userName);
		return list.isEmpty() ? null : list.get(0);
	}


}