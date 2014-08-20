
package com.entity;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private List<String> phones;
	private List<String> emails;
	private boolean selected;
	
	public UserEntity() {
		super();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
