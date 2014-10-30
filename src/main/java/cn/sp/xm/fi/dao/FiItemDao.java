package cn.sp.xm.fi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sp.persistent.BaseEntityDao;
import cn.sp.xm.fi.entity.FiItem;
/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:49:38
* @email benjaminchen555@gmail.com
*/
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FiItemDao  extends BaseEntityDao<FiItem, Long>{

	public List<FiItem> getByDate(int year, int month) {
		Object[] values = new Object[]{""+year+"-"+month+"%"};
		String hql = " from FiItem f where f.dat like ? ";
		return find(hql, values);
	}

}
