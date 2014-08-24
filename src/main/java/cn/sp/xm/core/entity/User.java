package cn.sp.xm.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.sp.persistent.BaseEntity;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午8:46:34
* @email benjaminchen555@gmail.com
*/
@Entity
public class User extends BaseEntity<String> {
	
	@Column(unique=true,nullable=false)
	private String accountName;
	@Column(unique=true)
	private String nickName;
	@Column(nullable=false)
	private String password;
	
	private String email;
	
	/**
	 * 最近一次阅读的节点
	 */
	@ManyToOne
	@JoinColumn(name="latest_node_id")
	private Node latestNode;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Node getLatestNode() {
		return latestNode;
	}

	public void setLatestNode(Node latestNode) {
		this.latestNode = latestNode;
	}
	

}
