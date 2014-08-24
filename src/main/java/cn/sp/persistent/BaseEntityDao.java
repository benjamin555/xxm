package cn.sp.persistent;

import java.io.Serializable;
import java.util.Date;

import org.springside.modules.orm.hibernate.HibernateDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseEntityDao<T extends BaseEntity, PK extends Serializable> extends HibernateDao<T, PK> {

	@Override
	public void save(BaseEntity entityObject) {
		if (entityObject.getCreateTime() == null) {
			entityObject.setCreateTime(new Date());
		} else {
			entityObject.setUpdateTime(new Date());
		}
		super.save((T) entityObject);
	}

}