package cn.sp.xm.fi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.sp.persistent.BaseEntity;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:44:09
* @email benjaminchen555@gmail.com
*/
@Entity
public class FiItem extends BaseEntity<Long>{
	/**
	 * 日期分隔符
	 */
	private static final String DATE_SEP = "-";
	
	@ManyToOne
	@JoinColumn(name="sum_id")
	private MonthSum sum;

	private String dat;
	
	private String description;
	
	@Column(columnDefinition="Decimal(10,2)")
	private double income;
	@Column(columnDefinition="Decimal(10,2)")
	private double output;
	
	
	/**
	 * 经手人
	 */
	private String handler;
	
	@Transient
	private double rest;
	


	public String getDat() {
		return dat;
	}

	public void setDat(String date) {
		this.dat = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	@Transient
	public int getYear() {
		String[] strings = dat.split(DATE_SEP);
		return Integer.parseInt(strings[0]);
	}
	@Transient
	public int getMonth() {
		String[] strings = dat.split(DATE_SEP);
		return Integer.parseInt(strings[1]);
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public MonthSum getSum() {
		return sum;
	}

	public void setSum(MonthSum sum) {
		this.sum = sum;
	}

	public double getRest() {
		return rest;
	}

	public void setRest(double rest) {
		this.rest = rest;
	}

	@Override
	public String toString() {
		return "FiItem [sum=" + sum.getId() + ", dat=" + dat + ", description=" + description + ", income=" + income
				+ ", output=" + output + ", handler=" + handler + ", rest=" + rest + "]";
	}


	
	

}
