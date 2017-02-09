package org.hsw.testMavenPro.domain;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseDomain{
	//用户名
	private String userName;
	//密码
	private String password;
	
	private List<Image> images = new ArrayList<Image>();

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
