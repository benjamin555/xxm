package cn.sp.xm.fi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sp.utils.DateUtils;
import cn.sp.utils.JxlsUtils;
import cn.sp.xm.fi.entity.FiItem;
import cn.sp.xm.fi.entity.MonthSum;

/**
* @author 陈嘉镇
* @version 创建时间：2013-10-27 下午4:21:00
* @email benjaminchen555@gmail.com
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test_applicationContext_fi.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class FiItemServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FiItemService service;
	@Autowired
	private MonthSumService monthService;

	@Before
	public void before() {

	}

	@Test
	public void testSave() throws Exception {
		FiItem i = new FiItem();
		i.setDat(DateUtils.getCurrentDateStr("yyyy-MM-dd"));
		i.setDescription("初始值");
		i.setIncome(10000);
		service.save(i);
	}

	@Test
	public void testGetByDate() throws Exception {
		List<FiItem> fiItems = service.getByDate(2013, 10);
		logger.info(fiItems.toString());
	}

	/**
	 * 新增行项目更新月收入与新增，并在下个月中使用
	 * @throws Exception
	 */
	@Test
	public void testLastMonthRest() throws Exception {
		logger.info("testaa");
		insertSomeData();

		MonthSum sum = monthService.getByMonth(2013, 9);
		Assert.isTrue(sum.getRest() == 10200);

		MonthSum sum10 = monthService.getByMonth(2013, 10);
		Assert.isTrue(sum10.getRest() == 10200);

		FiItem i4 = new FiItem();
		i4.setDat("2013-10-02");
		i4.setDescription("收入");
		i4.setIncome(400);
		service.save(i4);
		Assert.isTrue(sum10.getRest() == 10600);

		//考虑跨年的情况
		MonthSum sum11 = monthService.getByMonth(2013, 11);
		Assert.isTrue(sum11.getRest() == 10600);

		MonthSum sum12 = monthService.getByMonth(2013, 12);
		Assert.isTrue(sum12.getRest() == 10600);

		MonthSum sum13 = monthService.getByMonth(2014, 1);
		Assert.isTrue(sum13.getRest() == 10600);

		//考虑期中插入的情况

		FiItem i5 = new FiItem();
		i5.setDat("2013-09-03");
		i5.setDescription("收入");
		i5.setIncome(300);
		service.save(i5);
		Assert.isTrue(sum.getRest() == 10500);

		Assert.isTrue(sum10.getRest() == 10600 + 300);

		Assert.isTrue(sum13.getRest() == 10600 + 300);

	}

	protected void insertSomeData() {
		FiItem i = new FiItem();
		i.setDat("2013-09-01");
		i.setDescription("期初值");
		i.setIncome(10000);
		service.save(i);

		FiItem i2 = new FiItem();
		i2.setDat("2013-09-02");
		i2.setDescription("吃饭");
		i2.setOutput(200);
		service.save(i2);

		FiItem i3 = new FiItem();
		i3.setDat("2013-09-02");
		i3.setDescription("收入");
		i3.setIncome(400);
		service.save(i3);
	}

	@Test
	public void testXlsExport() throws Exception {
		String destFilePath = "ex_output.xls";
		String transformFilePath = "src/main/webapp/WEB-INF/xls/fiitem-list.xls";

		insertSomeData();
		MonthSum sum = monthService.getByMonth(2013, 10);

		FiItem i4 = new FiItem();
		i4.setDat("2013-10-02");
		i4.setDescription("收入");
		i4.setIncome(400);
		service.save(i4);
		Assert.isTrue(sum.getRest() == 10600);
		
		sum = monthService.getById(sum.getId());

		Map<String, Object> dateMap = new HashMap<String, Object>();
		List<FiItem> items = sum.getItems();
		if (items == null) {
			logger.info("items is null");
			items = new ArrayList<FiItem>();
		}
		dateMap.put("dataList", items);
		dateMap.put("sum", sum);
		logger.info("testtset");
		Workbook w = JxlsUtils.export(destFilePath, transformFilePath, dateMap);

		Sheet sheet = w.getSheet("fiitem-list");
		Double init = Double.parseDouble(sheet.getRow(0).getCell(1).toString());
		Assert.isTrue(10200 == init);

	}

}