package cn.nubia.demo.service;

import java.util.List;

import cn.nubia.demo.model.Press;
import cn.nubia.framework.core.Service;

public interface PressService extends Service {

	public List<Press> findPress();
}