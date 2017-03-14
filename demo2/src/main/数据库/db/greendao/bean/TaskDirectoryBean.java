package db.greendao.bean;

import java.io.Serializable;

/**
 * 创建者：wanglei
 * <p>时间：16/6/27  16:30
 * <p>类描述：作业目录
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class TaskDirectoryBean implements Serializable {

    private Long ID;//数据库用的唯一键
    private long classID;//班级ID
    private long userId;//用户ID
    private long taskID;//作业ID
    private long unitID;//
    private long lesenID;//
    private long partID;//题ID
    private long type;//句子类型1：跟读！2：朗读！3：背诵
    private boolean isHaveTask;//真句子表没有存过，假局子表存过
    private String partName;//条目名
    private long repeatTotal;//跟读总数
    private long repeatCurrent;//跟读当前数
    private long repeatTime;//跟读耗时

    public TaskDirectoryBean(Long ID, long classID, long userId, long taskID, long unitID, long lesenID, long partID, long type, boolean isHaveTask, String partName, long repeatTotal, long repeatCurrent, long repeatTime/*, long repeatTime_1*/) {
        this.ID = ID;
        this.classID = classID;
        this.userId = userId;
        this.taskID = taskID;
        this.unitID = unitID;
        this.lesenID = lesenID;
        this.partID = partID;
        this.type = type;
        this.isHaveTask = isHaveTask;
        this.partName = partName;
        this.repeatTotal = repeatTotal;
        this.repeatCurrent = repeatCurrent;
        this.repeatTime = repeatTime;
//        this.repeatTime_1 = repeatTime_1;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public long getUnitID() {
        return unitID;
    }

    public void setUnitID(long unitID) {
        this.unitID = unitID;
    }

    public long getLesenID() {
        return lesenID;
    }

    public void setLesenID(long lesenID) {
        this.lesenID = lesenID;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public boolean isHaveTask() {
        return isHaveTask;
    }

    public void setHaveTask(boolean haveTask) {
        isHaveTask = haveTask;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public long getRepeatTotal() {
        return repeatTotal;
    }

    public void setRepeatTotal(long repeatTotal) {
        this.repeatTotal = repeatTotal;
    }

    public long getRepeatCurrent() {
        return repeatCurrent;
    }

    public void setRepeatCurrent(long repeatCurrent) {
        this.repeatCurrent = repeatCurrent;
    }

    public long getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(long repeatTime) {
        this.repeatTime = repeatTime;
    }

//    public long getRepeatTime_1() {
//        return repeatTime_1;
//    }
//
//    public void setRepeatTime_1(long repeatTime_1) {
//        this.repeatTime_1 = repeatTime_1;
//    }
}
