
/**
 * 消息类型枚举
 */
public enum ChatStatus {
	
	LOGIN(1 , "登录消息"),NOTICE(2,"系统消息"),CHAT(3 , "聊天消息"),
	DD(4,"抖动消息"),ULIST(5,"在线用户列表"),QUIT(6,"退出"),SEND_FILE(7,"发送文件"),
	REGESTER(8,"注册"),REFLRSH(9,"刷新"),PHONE(10,"语音电话"),TIP_OFF(11, "举报消息"),
	BAN(12,"封禁账号"),SENDPHONE(13,"接收者的语音电话"),PHONEBACK(14,"发送者的语音电话");
	
	private Integer status;
	private String desc;
	
	private ChatStatus(int status , String desc) {
		this.status = status;
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
