package cn.sp.xm.core.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sp.xm.core.dao.UserDao;
import cn.sp.xm.core.entity.User;
import cn.sp.xm.core.service.UserService;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午9:24:51
* @email benjaminchen555@gmail.com
*/
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	

	public void create(User user, String operator) {
		user.setCreator(operator);
		user.setCreateTime(new Date());
		dao.create(user);
	}





}
