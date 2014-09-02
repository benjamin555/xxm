package cn.sp.xm.miyu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.sp.persistent.BaseEntity;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-19 下午2:12:37
* @email benjaminchen555@gmail.com
*/
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Riddle extends BaseEntity<Long>{
	
	private String question;
	
	private String answer;
	
	private int zan;
	
	@OneToMany(mappedBy="riddle",cascade = CascadeType.ALL)
	@OrderBy(value="ord")
	@Fetch(value=FetchMode.JOIN)
	private List<Tip> tips;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public List<Tip> getTips() {
		return tips;
	}

	public void setTips(List<Tip> tips) {
		this.tips = tips;
	}

	

}
