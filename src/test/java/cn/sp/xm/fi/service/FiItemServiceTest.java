package cn.sp.xm.fi.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sp.utils.DateUtils;
import cn.sp.xm.fi.entity.FiItem;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午4:21:00
* @email benjaminchen555@gmail.com
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class FiItemServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FiItemService service;
	
	@Test
	public void testSave() throws Exception {
		FiItem i = new FiItem();
		i.setDat(DateUtils.getCurrentDateStr("yyyy-MM-dd"));
		i.setDescription("初始值");
		i.setIncome(10000);
		i.setRest(10000);
		service.save(i);
	}
	
	@Test
	public void testGetByDate() throws Exception {
		List<FiItem> fiItems = service.getByDate(2014, 10);
		logger.info(fiItems.toString());
	}

}
