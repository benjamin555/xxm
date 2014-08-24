package cn.sp.xm.core.dao.impl;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.sp.xm.core.dao.UserDao;
import cn.sp.xm.core.entity.User;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-12 下午2:40:39
* @email benjaminchen555@gmail.com
*/
@Repository
public class UserDaoImpl extends HibernateDao<User, String>  implements UserDao {

	public void create(User u) {
		this.save(u);
	}

	public User findById(String id) {
		return this.getById(id);
	}

}
