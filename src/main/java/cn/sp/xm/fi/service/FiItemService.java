package cn.sp.xm.fi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sp.persistent.BaseEntity;
import cn.sp.service.IBaseService;
import cn.sp.xm.fi.dao.FiItemDao;
import cn.sp.xm.fi.dao.MonthSumDao;
import cn.sp.xm.fi.entity.FiItem;
import cn.sp.xm.fi.entity.MonthSum;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午4:21:25
* @email benjaminchen555@gmail.com
*/
@Service
@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class FiItemService implements IBaseService<FiItem,Long>{
	private Logger logger= LoggerFactory.getLogger(getClass());
	@Autowired
	private FiItemDao fiItemDao;
	@Autowired
	private MonthSumDao monthSumDao;
	@Autowired
	private MonthSumService monthService;
	public void save(FiItem i) {
		
		int year = i.getYear();
		int month = i.getMonth();
		
		MonthSum sum = monthSumDao.findByMonth(year,month);
		if (sum==null) {
			sum = addMonthSum(year, month);
		}
		sum.recount(i.getIncome(),i.getOutput());
		monthSumDao.save(sum);
		i.setSum(sum);
		fiItemDao.save(i);
		updateNexts(sum,i.getIncome(),i.getOutput());
		
	}
	
	/**
	 * 更新随后的汇总
	 * @param sum
	 */
	private void updateNexts(MonthSum sum,double in,double out) {
		monthService.updateNexts(sum, in, out);
	}

	/**
	 * 新增汇总
	 * 找上个月的汇总，以此为基础新建
	 * @param year
	 * @param month
	 * @return
	 */
	protected MonthSum addMonthSum(int year, int month) {
		return monthService.add(year, month);
	}
	@Override
	public FiItem getById(Long id) {
		return fiItemDao.getById(id);
	}
	@Override
	public void delete(FiItem entityObject) {
		deleteById(entityObject.getId());
	}
	@Override
	public void deleteById(Long id) {
		
		FiItem entityObject = getById(id);
		MonthSum sum = entityObject.getSum();
		entityObject.setIsUse(BaseEntity.NOTUSING);
		fiItemDao.save(entityObject );
		sum.remove(entityObject);
		monthSumDao.save(sum);
		updateNexts(sum, -entityObject.getIncome(), -entityObject.getOutput());
		
	}
	@Override
	public List<FiItem> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<FiItem> getByDate(int year, int month) {
		return fiItemDao.getByDate(year,month);
	}

	

}
