<%@page pageEncoding="UTF-8"%><%cn.nubia.demo.BeanTest test = new cn.nubia.demo.BeanTest();
	cn.nubia.demo.model.User user= test.test("jxva");
	out.println(user.getUserId());
%>
