package cn.sp.xm.fi.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import cn.sp.web.utils.ServletUtils;
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
			,@Result(name="rList",type="redirect",location="fi-item!list.action",params={"monthStr","${monthStr}"})
})
public class FiItemAction extends CrudActionSupport<FiItem> {
	private FiItem item;
	
	private Map<String, String> searchMap ;
	/**
	 * 月份
	 */
	private String monthStr;
	
	private String defaultDate;
	
	@Autowired
	private FiItemService service;
	@Autowired
	private MonthSumService  monthSumService;
	
	private Set<FiItem> items ;
	
	/**
	 * 期初值
	 */
	private double init;
	
	/**
	 * 本月合计
	 */
	private MonthSum monthSum;
	
	private long pk;
	private String name;
	private String value;
	

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
		return "rList" ;
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
	
	
	public String updateSingleField() throws Exception {
		
		long id = pk;
		String fieldName = name;
		service.updateSingleField(id ,fieldName ,value);
		
		return null;
	}
	
	public String search() throws Exception{
		ServletUtils.filterEmpty(searchMap);
		if (searchMap!=null&&!searchMap.isEmpty()) {
			
			List<FiItem> ls = service.find(searchMap);
			items=new HashSet<FiItem>();
			items.addAll(ls);
		}
		return "search";
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



	public Set<FiItem> getItems() {
		return items;
	}

	public void setItems(Set<FiItem> items) {
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


	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}

	
	

}
