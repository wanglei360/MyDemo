package db.greendao.bean;

import java.io.Serializable;

/**
 * 创建者：wanglei
 * <p>时间：16/6/29  10:11
 * <p>类描述：最好句子表
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class BestSentenceBean implements Serializable {
    private Long ID;//数据库用的唯一键
    private long userId;//用户ID
    private long classID;//用户ID
    private long taskID;//作业ID
    private long partID;//题ID
    private long sentenceID;//句子ID
    private long type;//句子类型1：跟读！2：朗读！3：背诵
    private String text;//文本
    private String textColor;//变色文本
    private String netAddress;//泛音地址
    private String myAddress;//我的声音本地地址
    private String chishengNetAddress;//弛声的网络声音地址
    private long myVoiceTime;//我的声音时长
    private long myNum;//我的分数
    private boolean state;//否超过上次的分数

    public BestSentenceBean(Long ID, long userId, long classID, long taskID, long partID, long sentenceID, long type, String text, String textColor, String netAddress, String myAddress, String chishengNetAddress, long myVoiceTime, long myNum, boolean state) {
        this.ID = ID;
        this.userId = userId;
        this.classID = classID;
        this.taskID = taskID;
        this.partID = partID;
        this.sentenceID = sentenceID;
        this.type = type;
        this.text = text;
        this.textColor = textColor;
        this.netAddress = netAddress;
        this.myAddress = myAddress;
        this.chishengNetAddress = chishengNetAddress;
        this.myVoiceTime = myVoiceTime;
        this.myNum = myNum;
        this.state = state;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public long getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(long sentenceID) {
        this.sentenceID = sentenceID;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getNetAddress() {
        return netAddress;
    }

    public void setNetAddress(String netAddress) {
        this.netAddress = netAddress;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getChishengNetAddress() {
        return chishengNetAddress;
    }

    public void setChishengNetAddress(String chishengNetAddress) {
        this.chishengNetAddress = chishengNetAddress;
    }

    public long getMyVoiceTime() {
        return myVoiceTime;
    }

    public void setMyVoiceTime(long myVoiceTime) {
        this.myVoiceTime = myVoiceTime;
    }

    public long getMyNum() {
        return myNum;
    }

    public void setMyNum(long myNum) {
        this.myNum = myNum;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
