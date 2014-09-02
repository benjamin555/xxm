package cn.sp.xm.miyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.Page;

import cn.sp.persistent.BaseEntityDao;
import cn.sp.persistent.utils.HqlUtil;
import cn.sp.xm.miyu.entity.Riddle;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-19 下午2:22:41
* @email benjaminchen555@gmail.com
*/
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RiddleDao extends BaseEntityDao<Riddle, Long>{

	public Page<Riddle> findPage(int start, int size, Map<String, String> searchMap) {
		
		Page<Riddle> page = new Page<Riddle>();
		page.setPageSize(size);
		page.setStart(start);
		Map<String, Object> values = new HashMap<String, Object>();
		String append = HqlUtil.buildHqlAppend(searchMap, values);
		String hql = "select r from Riddle r  where r.isUse='Y' ";
		hql += append;
		logger.info(hql);
		return findPage(page, hql, values);
	}

	public long getTotalCount() {
		Map<String, Object> values = null ;
		String hql = "select r from Riddle r";
		long l = countHqlResult(hql,values);
		return l;
	}

	
	public Page<Riddle> findRandomPageByLen( int size, int len) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "select r from Riddle r  where r.isUse='Y' ";
		String append = " and LENGTH(r.answer) = :len ";
		hql += append ;
		values.put("len", Long.parseLong(len*3+""));
		
		
		
		logger.info(hql);
		
		Page<Riddle> page = findRandomPage(hql, size, values);
		return page;
	}

	protected Page<Riddle> findRandomPage(String hql, int size, Map<String, Object> values) {
		Query q = createQuery(hql, values);
		Page<Riddle> page = new Page<Riddle>();
		page.setPageSize(size);
		
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		long totalCount = page.getTotalCount();
		int random = getRandomStart(size, totalCount);
		logger.info("start:{}",random);
		page.setStart(random);
		setPageParameter(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	protected int getRandomStart(int size, long totalCount) {
		int random = (int) (Math.random()*(totalCount-size));
		return random;
	}
	
	
	public Page<Riddle> findRandomPage( int size, Map<String, String> searchMap) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "select r from Riddle r  where r.isUse='Y' ";
		String append = HqlUtil.buildHqlAppend(searchMap, values);
		hql += append;
		logger.info(hql);
		Page<Riddle> page = findRandomPage(hql, size, values);
		return page;
	}


}
