package cn.nubia.demo;

import cn.nubia.demo.model.User;
import cn.nubia.demo.service.UserService;
import cn.nubia.framework.core.ApplicationContextHelper;

public class BeanTest {

	public User test(String username){
		UserService us=ApplicationContextHelper.getBean(UserService.class);
		return us.findUserByName(username);
	}
}
