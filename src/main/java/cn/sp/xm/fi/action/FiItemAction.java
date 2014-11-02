package cn.sp.xm.fi.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.sp.action.CrudActionSupport;
import cn.sp.utils.DateUtils;
import cn.sp.xm.fi.entity.FiItem;
import cn.sp.xm.fi.entity.MonthSum;
import cn.sp.xm.fi.service.FiItemService;
import cn.sp.xm.fi.service.MonthSumService;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:51:46
* @email benjaminchen555@gmail.com
*/
@SuppressWarnings({"serial","unchecked"})
@Controller
@Scope("prototype")
@Namespace("/fi")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "exception") })
@Results({ @Result(name = "exception", location = "/common/error.jsp") 
})
public class FiItemAction extends CrudActionSupport<FiItem> {
	private FiItem item;
	
	
	/**
	 * 月份
	 */
	private String monthStr;
	
	private String defaultDate;
	
	@Autowired
	private FiItemService service;
	@Autowired
	private MonthSumService  monthSumService;
	
	private List<FiItem> items ;
	
	/**
	 * 期初值
	 */
	private double init;
	
	/**
	 * 本月合计
	 */
	private MonthSum monthSum;
	

	@Override
	public FiItem getModel() {
		if (item==null) {
			item = new FiItem();
		}
		return item;
	}

	@Override
	public String list() throws Exception {
		
		if (StringUtils.isBlank(monthStr)) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int year = c.get(Calendar.YEAR);
			int month= c.get(Calendar.MONTH)+1;
			this.monthStr = year+"-"+month;
		}
		defaultDate=monthStr+"-01";
		String[] s = monthStr.split("-");
		int year =Integer.parseInt(s[0]);
		int month=Integer.parseInt(s[1]);
		
		
		monthSum = monthSumService.getByMonth(year, month);
		logger.info("sum:{}",monthSum);
		
		items = monthSum.getItems();
		
		MonthSum lastMonth = monthSum.getLast();
		if (lastMonth!=null) {
			init = lastMonth.getRest();
		}
		return "list";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		service.save(item);
		return list() ;
	}

	@Override
	public String delete() throws Exception {
		service.deleteById(item.getId());
		return list();
	}
	
	public String exportExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition", "attachment; filename=export"
				+ DateUtils.getCurrentDateStr("yyyy-MM-dd HHmmss") + ".xlsx");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		String transformFilePath = "WEB-INF/xls/fiitem-list.xlsx";
		 transformFilePath = ServletActionContext.getServletContext().getRealPath(transformFilePath);
		monthSumService.exportExcel(transformFilePath,os,monthSum.getId());
		
		return list();
	}
	
	

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public FiItem getItem() {
		return item;
	}

	public void setItem(FiItem item) {
		this.item = item;
	}


	public FiItemService getService() {
		return service;
	}

	public void setService(FiItemService service) {
		this.service = service;
	}

	public List<FiItem> getItems() {
		return items;
	}

	public void setItems(List<FiItem> items) {
		this.items = items;
	}


	public String getDefaultMonth() {
		return monthStr;
	}

	public void setDefaultMonth(String defaultMonth) {
		this.monthStr = defaultMonth;
	}

	public MonthSumService getMonthSumService() {
		return monthSumService;
	}

	public void setMonthSumService(MonthSumService monthSumService) {
		this.monthSumService = monthSumService;
	}

	public String getDefaultDate() {
		return defaultDate;
	}

	public void setDefaultDate(String defaultDate) {
		this.defaultDate = defaultDate;
	}

	public double getInit() {
		return init;
	}

	public void setInit(double init) {
		this.init = init;
	}


	public MonthSum getMonthSum() {
		return monthSum;
	}

	public void setMonthSum(MonthSum monthSum) {
		this.monthSum = monthSum;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	
	

}
