package cn.nubia.demo.service;

import java.util.List;

import cn.nubia.demo.model.User;
import cn.nubia.framework.core.Service;

public interface UserService extends Service {

	public List<User> findUser();
	
	public User findUserByName(String userName);
}