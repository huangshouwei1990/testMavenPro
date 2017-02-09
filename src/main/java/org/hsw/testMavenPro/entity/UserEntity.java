package org.hsw.testMavenPro.entity;

public class UserEntity extends BaseEnity{
	//用户名
	private String userName;
	//密码
	private String password;
	//图片
	private String images;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
}
