package cn.sp.xm.fi.entity;

import javax.persistence.Entity;

import cn.sp.persistent.BaseEntity;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午3:44:09
* @email benjaminchen555@gmail.com
*/
@Entity
public class FiItem extends BaseEntity<Long>{
	
	private String dat;
	
	private String description;
	
	private double income;
	
	private double output;
	
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

	public double getRest() {
		return rest;
	}

	public void setRest(double rest) {
		this.rest = rest;
	}

	
	
	

}
