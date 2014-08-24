package cn.sp.xm.core.dao;

import cn.sp.xm.core.entity.User;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-12 下午2:39:53
* @email benjaminchen555@gmail.com
*/
public interface UserDao {
	
	void create(User u);

	User findById(String id);
}
