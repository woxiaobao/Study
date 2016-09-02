package com.justcode.jdbc;

import java.util.Date;

public class Person {
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"userName\":\"" + userName
				+ "\", \"password\":\"" + password + "\", \"email\":\"" + email
				+ "\", \"phone\":\"" + phone + "\", \"nickname\":\"" + nickname
				+ "\", \"status\":\"" + status + "\", \"enabled\":\"" + enabled
				+ "\", \"dateCreated\":\"" + dateCreated
				+ "\", \"lastUpdated\":\"" + lastUpdated
				+ "\", \"lastLoginTime\":\"" + lastLoginTime
				+ "\", \"icon\":\"" + icon + "\"}";
	}

	private String id;
	
	private String userName;//用户名
	
	private String password;//密码
	
	private String email;//邮箱

	private String phone;//电话

	private String nickname;//昵称

	private Integer status=1;//状态

	private boolean enabled = true;//是否能登陆

	private Date dateCreated;//创建时间

	private Date lastUpdated;//最后一次修改时间

	private Long lastLoginTime;//最后一次登陆时间

	private String icon;//头像路径

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String id, String userName, String password, String email,
			String phone, String nickname, Integer status, boolean enabled,
			Date dateCreated, Date lastUpdated, Long lastLoginTime, String icon) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.nickname = nickname;
		this.status = status;
		this.enabled = enabled;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.lastLoginTime = lastLoginTime;
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

}
