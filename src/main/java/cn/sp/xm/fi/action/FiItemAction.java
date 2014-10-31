package cn.sp.xm.fi.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.sp.action.CrudActionSupport;
import cn.sp.xm.fi.entity.FiItem;
import cn.sp.xm.fi.service.FiItemService;

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
	private String month;
	
	private String defaultDate;
	
	@Autowired
	private FiItemService service;
	
	private List<FiItem> items ;

	@Override
	public FiItem getModel() {
		if (item==null) {
			item = new FiItem();
		}
		return item;
	}

	@Override
	public String list() throws Exception {
		
		if (StringUtils.isBlank(month)) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int year = c.get(Calendar.YEAR);
			int month= c.get(Calendar.MONTH)+1;
			this.month = year+"-"+month;
		}
		defaultDate=month+"-01";
		String[] s = month.split("-");
		int year =Integer.parseInt(s[0]);
		int month=Integer.parseInt(s[1]);
		items = service.getByDate(year,month);
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDefaultDate() {
		return defaultDate;
	}

	public void setDefaultDate(String defaultDate) {
		this.defaultDate = defaultDate;
	}

	
	

}
