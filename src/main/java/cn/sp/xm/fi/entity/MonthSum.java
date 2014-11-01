package cn.sp.xm.fi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import cn.sp.persistent.BaseEntity;

/**
 * 月合计
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:44:09
* @email benjaminchen555@gmail.com
*/
@Entity
public class MonthSum extends BaseEntity<Long>{
	
	private int year;
	
	private int month;
	
	private double income;
	
	private double output;
	
	private double rest;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="last_sum_id")
	private MonthSum last;

	@OneToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "sum")
	@Where(clause="is_Use='Y'")
	@OrderBy(clause="dat asc  ")
	private List<FiItem> items;




	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public double getRest() {
		return rest;
	}

	public void setRest(double rest) {
		this.rest = rest;
	}

	@Transient
	/**
	 * 重计算
	 * @param income2
	 * @param output2
	 */
	public void recount(double income2, double output2) {
		
		income +=income2;
		output +=output2;
		rest = rest +income2-output2;
		
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "MonthSum [year=" + year + ", month=" + month + ", income=" + income + ", output=" + output + ", rest="
				+ rest + "]";
	}

	public List<FiItem> getItems() {
		return items;
	}

	public void setItems(List<FiItem> items) {
		this.items = items;
	}

	public MonthSum getLast() {
		return last;
	}

	public void setLast(MonthSum last) {
		this.last = last;
	}
	/**
	 * 获取期初值
	 * @return
	 */
	@Transient
	public double getInit() {
		if (last==null) {
			return 0;
		}
		return last.getRest();
	}

	public void remove(FiItem entityObject) {
		items.remove(entityObject);
		recount(-entityObject.getIncome(), -entityObject.getOutput());
	}

	/**
	 * 计算行项目的余额
	 */
	public void countItemsRest() {
		double init = getInit();
		for (FiItem item : items) {
			double income= item.getIncome(); 
			double output= item.getOutput();
			double re = init+income -output;
			item.setRest(re);
			init=re;
		}
	}

}
