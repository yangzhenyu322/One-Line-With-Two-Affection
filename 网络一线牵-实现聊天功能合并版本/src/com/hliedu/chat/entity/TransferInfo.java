package com.hliedu.chat.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 数据封装
 * 
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：107184365
 *
 */
public class TransferInfo implements Serializable{

	private static final long serialVersionUID = 6543722756249559791L;
	
	private String account;
	private String password;
	private String	userName;
	private String	sex;
	private String	age;
	private String	motto;
	private String	picture;
	private String	background;
	private String	friend;
	
	//聊天消息内容
	private List<FontStyle> content;
	
	//系统消息
	private String notice;
	
	//登录成功标志
	private Boolean loginSucceessFlag = false;
	
	//消息类型枚举
	private ChatStatus statusEnum;
	
	//在线的用户列表
	private String[] userOnlineArray;
	
	//发送人
	private String sender;
	
	//接收人
	private String reciver;
	
	//文件数据
	private byte[] fileByte;
	
	//文件名
	private String fileName;
	
	
	public byte[] getFileByte() {
		return fileByte;
	}
	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public String[] getUserOnlineArray() {
		return userOnlineArray;
	}
	public void setUserOnlineArray(String[] userOnlineArray) {
		this.userOnlineArray = userOnlineArray;
	}
	public ChatStatus getStatusEnum() {
		return statusEnum;
	}
	public void setStatusEnum(ChatStatus statusEnum) {
		this.statusEnum = statusEnum;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Boolean getLoginSucceessFlag() {
		return loginSucceessFlag;
	}
	public void setLoginSucceessFlag(Boolean loginSucceessFlag) {
		this.loginSucceessFlag = loginSucceessFlag;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String userName) {
		this.account = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.userName = picture;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.userName = background;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}

	public List<FontStyle> getContent() {
		return content;
	}
	public void setContent(List<FontStyle> content) {
		this.content = content;
	}
}
