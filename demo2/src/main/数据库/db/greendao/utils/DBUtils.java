package db.greendao.utils;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.MyApplication;
import db.greendao.bean.BestSentenceBean;
import db.greendao.bean.ClassTabelBean;
import db.greendao.bean.CurrentSentenceBean;
import db.greendao.bean.TaskDirectoryBean;
import db.greendao.bean.TaskListBean;
import db.greendao.communal.Session;
import db.greendao.dao.BestSentenceTabelDao;
import db.greendao.dao.ClassTabelDao;
import db.greendao.dao.CurrentSentenceTabelDao;
import db.greendao.dao.TaskDirectoryTabelDao;
import db.greendao.dao.TaskListTabelDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 创建者：wanglei
 * <p>时间：16/7/5  11:24
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class DBUtils {


    private static DBUtils dbUtils;
    private static ExecutorService ftp = Executors.newFixedThreadPool(3);


    public static DBUtils getDbUtils() {
        if (dbUtils == null) {
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }

    private DBUtils() {
    }


    /**
     * 存
     * 离开学生作业列表
     * 存：班级列表数据与小红点状态
     * 存：当前所在班级的数据
     *
     * @param classTabelList 班级数据的集合
     * @param TaskListList   作业列表数据的集合
     */
    public void leaveHome(long usreID, List<ClassTabelBean> classTabelList, List<TaskListBean> TaskListList) {
        //存：班级列表数据
        Session session = MyApplication.daoSession;
        ClassTabelDao classTabelDao = session.getClassTabelDao();
        SQLiteDatabase db = classTabelDao.getDatabase();
        db.execSQL("delete from " + ClassTabelDao.TABLENAME + " where " + ClassTabelDao.USERID + " = " + usreID + ";");
        classTabelDao.insertInTx(classTabelList);

        //存：当前所在班级的数据
        TaskListTabelDao taskListTabelDao = session.getTaskListTabelDao();
        db.execSQL("delete from " + TaskListTabelDao.TABLENAME + " where " + TaskListTabelDao.USERID + " = " + usreID + ";");
//        taskListTabelDao.deleteAll();
        taskListTabelDao.insertInTx(TaskListList);

        //todo 上面不行就用这个方法
//        leaveHome_1(usreID,classID,classTabelList,TaskListList);
    }

    public void leaveHome(final long usreID, final long classID, final long currentTime, final List<ClassTabelBean> classTabelList, final List<TaskListBean> taskListList) {
        ftp.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Session session = MyApplication.daoSession;
                    ClassTabelDao classTabelDao = session.getClassTabelDao();
                    List<ClassTabelBean> list = classTabelDao.queryBuilder().where(ClassTabelDao.Properties.userId.eq(usreID)).list();
                    for (ClassTabelBean ctb : list) {
                        classTabelDao.deleteByKey(ctb.getID());
                    }
                    classTabelDao.insertInTx(classTabelList);

                    TaskListTabelDao taskListTabelDao = session.getTaskListTabelDao();
                    List<TaskListBean> list1 = taskListTabelDao.queryBuilder().where(TaskListTabelDao.Properties.userId.eq(usreID), TaskListTabelDao.Properties.classId.eq(classID)).list();
                    for (TaskListBean tlb : list1) {
                        if (tlb.getTaskEndTime() > currentTime) {
                            deleteDBInfo(usreID, classID, tlb.getTaskID(), session);
                            taskListTabelDao.deleteByKey(tlb.getID());
                        }
                    }
                    List<TaskListBean> list3 = taskListTabelDao.queryBuilder().where(TaskListTabelDao.Properties.userId.eq(usreID), TaskListTabelDao.Properties.classId.eq(classID)).list();
                    List<TaskListBean> taskListBeen = null;
                    boolean b = taskListList.removeAll(list3);
                    if (b) {
                        taskListBeen = new ArrayList<>();
                        for (TaskListBean tb : taskListList) {
                            taskListBeen.add(tb);
                        }
                    }
                    taskListTabelDao.insertInTx(b ? taskListBeen : taskListList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void deleteDBInfo(long usreID, long classID, long taskID, Session session) {
        TaskDirectoryTabelDao taskDirectoryTabelDao = session.getTaskDirectoryTabelDao();
        List<TaskDirectoryBean> list = taskDirectoryTabelDao.queryBuilder().where(TaskDirectoryTabelDao.Properties.userId.eq(usreID), TaskDirectoryTabelDao.Properties.classID.eq(classID), TaskDirectoryTabelDao.Properties.taskID.eq(taskID)).list();
        for (TaskDirectoryBean tdb : list) {
            taskDirectoryTabelDao.deleteByKey(tdb.getID());
        }

        BestSentenceTabelDao bestSentenceTabelDao = session.getBestSentenceTabelDao();
        List<BestSentenceBean> list1 = bestSentenceTabelDao.queryBuilder().where(TaskDirectoryTabelDao.Properties.userId.eq(usreID), TaskDirectoryTabelDao.Properties.classID.eq(classID), TaskDirectoryTabelDao.Properties.taskID.eq(taskID)).list();
        for (BestSentenceBean bsb : list1) {
            deleteMediaFile(bsb.getMyAddress());
            bestSentenceTabelDao.deleteByKey(bsb.getID());
        }

        CurrentSentenceTabelDao currentSentenceTabelDao = session.getCurrentSentenceTabelDao();
        List<CurrentSentenceBean> list2 = currentSentenceTabelDao.queryBuilder().where(TaskDirectoryTabelDao.Properties.userId.eq(usreID), TaskDirectoryTabelDao.Properties.classID.eq(classID), TaskDirectoryTabelDao.Properties.taskID.eq(taskID)).list();
        for (CurrentSentenceBean csb : list2) {
            deleteMediaFile(csb.getMyAddress());
            currentSentenceTabelDao.deleteByKey(csb.getID());
        }
    }

    /**
     * 删
     * 进入学生作业列表，根据时间删除数据库过期的数据，或者根据当前页显示的内容，删除其它
     * <p>todo 如果出现并发修改异常，就用queryBuilder()查询后获取主键id删除
     *
     * @param userID      用户id
     * @param currentTime 服务器返回的当前时间
     */
    public void goOnHome(long userID, long currentTime) {
        Session session = MyApplication.daoSession;
        TaskListTabelDao taskListTabelDao = session.getTaskListTabelDao();
        QueryBuilder<TaskListBean> tlbqb = taskListTabelDao.queryBuilder();
        tlbqb.where(TaskListTabelDao.Properties.userId.eq(userID));
        List<TaskListBean> list = tlbqb.list();//获取到当前用户的所有作业
        int taskListSize = list.size();
        String classId = "";
        String taskId = "";
        for (int x = 0; x < taskListSize; x++) {
            TaskListBean tlb = list.get(taskListSize);
            //todo 这里的判断有可能反了
            if (currentTime > tlb.getTaskEndTime()) {//如果现在的时间大于作业截止时间，已过期，要删除(获取所有需要删除的用户ID和作业ID)
                if (x < taskListSize - 1) {
                    classId += tlb.getClassID() + ",";
                    taskId += tlb.getTaskID() + ",";
                } else {
                    classId += tlb.getClassID();
                    taskId += tlb.getTaskID();
                }
            }
        }

        SQLiteDatabase db = taskListTabelDao.getDatabase();
        deleteTabelInfo(db, TaskListTabelDao.TABLENAME, TaskListTabelDao.USERID, String.valueOf(userID), TaskListTabelDao.CLASSID, classId, TaskListTabelDao.TASKID, taskId);
        deleteTabelInfo(db, TaskDirectoryTabelDao.TABLENAME, TaskDirectoryTabelDao.USERID, String.valueOf(userID), TaskDirectoryTabelDao.CLASSID, classId, TaskDirectoryTabelDao.TASKID, taskId);
        deleteTabelInfo(db, BestSentenceTabelDao.TABLENAME, BestSentenceTabelDao.USERID, String.valueOf(userID), BestSentenceTabelDao.CLASSID, classId, BestSentenceTabelDao.TASKID, taskId);
        deleteTabelInfo(db, CurrentSentenceTabelDao.TABLENAME, CurrentSentenceTabelDao.USERID, String.valueOf(userID), CurrentSentenceTabelDao.CLASSID, classId, CurrentSentenceTabelDao.TASKID, taskId);
    }

    private void deleteTabelInfo(SQLiteDatabase db, String tablename, String field, String condition, String field1, String condition1, String field2, String condition2) {
//db.execSQL("delete from taskList where "+TaskListTabelDao.USERID+" in(12121212,-2147482649) and " +TaskListTabelDao.ISEVALUATE+"=\"true\";");
        db.execSQL("delete from " + tablename + " where " + field + " = " + condition + " and " +
                field1 + " in(" + condition1 + ") and " + field2 + " in(" + condition2 + ");");
    }

    /**
     * 根据给定的音频文件地址删除这个文件
     *
     * @param mediaFilePath 音频文件地址
     */
    private void deleteMediaFile(String mediaFilePath) {
        new File(mediaFilePath).delete();
    }
}
