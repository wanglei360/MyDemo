package db.greendao.bean;

import java.io.Serializable;

/**
 * 创建者：wanglei
 * <p>时间：16/7/5  10:40
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ClassTabelBean implements Serializable {

    private Long ID;//数据库用的唯一键
    private long userID;//用户ID
    private long classID;//班级ID
    private long taskLatestTime;//最近一次留作业的时间
    private boolean isShowReddot;//是否显示小红点
    private long classState;//1,已加入！2，待审核
    private String className;//班级名字


    public ClassTabelBean(Long ID, long userID, long classID, long taskLatestTime, boolean isShowReddot, long classState, String className) {
        this.ID = ID;
        this.userID = userID;
        this.classID = classID;
        this.taskLatestTime = taskLatestTime;
        this.isShowReddot = isShowReddot;
        this.classState = classState;
        this.className = className;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public long getTaskLatestTime() {
        return taskLatestTime;
    }

    public void setTaskLatestTime(long taskLatestTime) {
        this.taskLatestTime = taskLatestTime;
    }

    public boolean isShowReddot() {
        return isShowReddot;
    }

    public void setShowReddot(boolean showReddot) {
        isShowReddot = showReddot;
    }

    public long getClassState() {
        return classState;
    }

    public void setClassState(long classState) {
        this.classState = classState;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
