package cn.sp.persistent;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import net.sf.json.JSONObject;

/**
 * 业务Entity的基类
 * 
 * @author yong.gao
 */
@MappedSuperclass
public class NumberBaseEntity extends NumberIdEntity {
	public static final String USING = "Y";
	public static final String NOTUSING = "N";
	
	public NumberBaseEntity() {
		super();
	}

	/**
	 * 创建时间.
	 */
	//本属性只在save时有效,update时无效.
	@Column(name = "create_time", nullable = false, updatable = false, length = 200)
	protected Date createTime;
	/**
	 * 创建的操作员的登录名.
	 */
	@Column(name = "creator", nullable = true, updatable = false)
	protected String creator;
	/**
	 * 修改时间.
	 */
	//本属性只在update时有效,save时无效.
	@Column(name = "update_time", nullable = true, insertable = false, length = 200)
	protected Date updateTime;
	/**
	 * 最后修改的操作员的登录名.
	 */
	@Column(name = "updater", nullable = true, insertable = false)
	protected String updater;
	/**
	 * 版本字段，启用乐观锁定
	 */
	@Version
	protected Timestamp version = new Timestamp(new Date().getTime());
	/**
	 * 是否可用，用于逻辑删除
	 */
	@Column(name = "is_use", nullable = false, length = 1)
	protected String isUse = "Y";

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(@SuppressWarnings("hiding") Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date lastModifyTime) {
		this.updateTime = lastModifyTime;
	}


	/**
	 * @return the version
	 */
	public Timestamp getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(@SuppressWarnings("hiding") Timestamp version) {
		this.version = version;
	}

	/**
	 * @return the isUse
	 */
	public String getIsUse() {
		return isUse;
	}

	/**
	 * @param isUse the isUse to set
	 */
	public void setIsUse(@SuppressWarnings("hiding") String isUse) {
		this.isUse = isUse;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
 	
	

}
