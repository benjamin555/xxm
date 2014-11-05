package cn.sp.xm.fi.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sp.service.IBaseService;
import cn.sp.utils.JxlsUtils;
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
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MonthSumService implements IBaseService<MonthSum, Long> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MonthSumDao dao;

	@Override
	public MonthSum getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void save(MonthSum entityObject) {
		dao.save(entityObject);
	}

	@Override
	public void delete(MonthSum entityObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MonthSum> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public MonthSum getByMonth(int year, int month) {
		Assert.isTrue(month > 0 && month <= 12, "月份必须大于0小于等于12");
		MonthSum sum = dao.findByMonth(year, month);
		if (sum == null) {
			sum = add(year, month);
		}
		return sum;
	}

	/**
	 * 新增汇总
	 * 找上个月的汇总，以此为基础新建
	 * @param year
	 * @param month
	 * @return
	 */
	public MonthSum add(int year, int month) {
		MonthSum sum;
		MonthSum last = getLastMonth(year, month);
		sum = new MonthSum();
		sum.setLast(last);
		sum.setYear(year);
		sum.setMonth(month);
		if (last != null) {
			sum.setRest(last.getRest());
		}
		dao.save(sum);
		return sum;
	}

	/**
	 * 获取上个月的汇总，如果拿不到，新增一个汇总
	 * @param year
	 * @param month
	 * @return
	 */
	public MonthSum getLastMonth(int year, int month) {
		if (month == 1) {
			year--;
			month = 12;
		} else {
			month--;
		}
		MonthSum last = dao.findByMonth(year, month);
		return last;
	}

	public void exportExcel(String transformFilePath,OutputStream os, Long id) throws IOException {
		
		MonthSum sum = getById(id);
		Map<String, Object> dateMap = new HashMap<String, Object>();
		List<FiItem> items = sum.getItems();
		sum.countItemsRest();
		if (items == null) {
			logger.info("items is null");
			items = new ArrayList<FiItem>();
		}
		dateMap.put("dataList", items);
		dateMap.put("sum", sum);
		
		Workbook w = JxlsUtils.makeExcel(transformFilePath, dateMap);
		w.write(os);
	}
	
	
	/**
	 * 更新随后的汇总
	 * @param sum
	 */
	public void updateNexts(MonthSum sum,double in,double out) {
		double re = in - out;
		List<MonthSum> l = dao.findNexts(sum.getYear(),sum.getMonth());
		for (MonthSum monthSum : l) {
			logger.info("update {}",monthSum);
			monthSum.setRest(monthSum.getRest()+re);
		}
	}
	
	/**
	 * 更新随后的汇总
	 * @param sum
	 */
	public void updateNexts(MonthSum sum) {
		List<MonthSum> l = dao.findNexts(sum.getYear(),sum.getMonth());
		for (MonthSum monthSum : l) {
			logger.info("update {}",monthSum);
			monthSum.recount();
			dao.save(monthSum);
		}
	}

}
