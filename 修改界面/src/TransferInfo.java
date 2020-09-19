import java.io.Serializable;
import java.util.List;
public class TransferInfo implements Serializable{

    private static final long serialVersionUID = 6543722756249559791L;

    private String account;
    private String password;

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
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<FontStyle> getContent() {
        return content;
    }
    public void setContent(List<FontStyle> content) {
        this.content = content;
    }


}
