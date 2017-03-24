package cn.nubia.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.nubia.demo.model.Press;
import cn.nubia.demo.service.PressService;
import cn.nubia.framework.core.BaseService;

@Service("pressService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PressServiceImpl extends BaseService implements PressService {


	public List<Press> findPress() {
		return dao.find("from Press");
	}
}