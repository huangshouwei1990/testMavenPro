package org.hsw.testMavenPro.entity;

import java.util.Date;

public class BaseEnity {
	private String id;
	private Date createTime;
	private Date lastUpdateTime;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
