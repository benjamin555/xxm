package cn.sp.xm.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.sf.json.JSONObject;
import cn.sp.persistent.BaseEntity;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午9:04:32
* @email benjaminchen555@gmail.com
*/
@Entity
public class Node extends BaseEntity<String>{
	
	@ManyToOne
	@JoinColumn(name="parent_node_id")
	private Node parentNode;
	
	/**
	 * 内容，长度限制在500字以内
	 */
	@Column(nullable=false,length=500)
	private String content;
	
	private int zan;
	
	private long sumZan;

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public long getSumZan() {
		return sumZan;
	}

	public void setSumZan(long sumZan) {
		this.sumZan = sumZan;
	}
	
	public String toJsonString() {
		return  JSONObject.fromObject(this).toString();
	}
	
}
