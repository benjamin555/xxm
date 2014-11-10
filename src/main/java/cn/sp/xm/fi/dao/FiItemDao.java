package cn.sp.xm.fi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.sp.persistent.BaseEntityDao;
import cn.sp.persistent.utils.HqlUtil;
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
		String monthString = month+"";
		if (month<10) {
			monthString="0"+month;
		}
		Object[] values = new Object[]{""+year+"-"+monthString+"%"};
		String hql = " from FiItem f where f.dat like ? and f.isUse='Y' order by f.dat";
		return find(hql, values);
	}

	public List<FiItem> find(Map<String, String> searchMap) {
		Map<String, Object> values = new HashMap<String, Object>();
		String append = HqlUtil.buildHqlAppend(searchMap, values);
		String hql = "select i from FiItem i where  i.isUse='Y' ";
		hql += append;
		return this.find(hql, values);
	}

}
