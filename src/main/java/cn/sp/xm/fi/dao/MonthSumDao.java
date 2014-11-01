package cn.sp.xm.fi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.sp.persistent.BaseEntityDao;
import cn.sp.xm.fi.entity.MonthSum;
/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:49:38
* @email benjaminchen555@gmail.com
*/
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MonthSumDao  extends BaseEntityDao<MonthSum, Long>{

	/**
	 * 根据月份获取月汇总
	 * @param year
	 * @param month
	 * @return
	 */
	public MonthSum findByMonth(int year, int month) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("year", year);
		values.put("month", month);
		String hql = "from MonthSum s where s.year=:year and s.month=:month ";
		return findUnique(hql , values);
	}

	/**
	 * 获取某月之后的汇总
	 * @param year
	 * @param month
	 * @return
	 */
	public List<MonthSum> findNexts(int year, int month) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("year", year);
		values.put("month", month);
		String hql = "from MonthSum s where s.year>:year or (s.year=:year and s.month>:month) ";
		return find(hql , values);
	}


}
