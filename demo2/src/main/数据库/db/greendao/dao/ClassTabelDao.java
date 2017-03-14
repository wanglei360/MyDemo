package db.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import db.greendao.bean.ClassTabelBean;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * 创建者：wanglei
 * <p>时间：16/7/5  10:56
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ClassTabelDao extends AbstractDao<ClassTabelBean, Long> {
    public static final String TABLENAME = "classTabel";//数据库的表命
    public static final String _id = "_id";//ID
    public static final String USERID = "userId";//
    public static final String CLASSID = "classID";//作业ID
    public static final String TASKLATESTTIME = "taskLatestTime";//最近一次留作业的时间
    public static final String ISSHOWREDDOT = "isShowReddot";//是否显示小红点
    public static final String CLASSSTATE = "classState";//1,已加入！2，待审核
    public static final String CLASSNAME = "className";//班级名字

    public ClassTabelDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Properties of entity Note.<br/>
     * 实体注释的属性。
     * Can be used for QueryBuilder and for referencing column names.
     * 可用于querybuilder和引用列的名称。
     * <p/>
     * private boolean ;//否超过上次的分数
     * <p/>
     * private Long ID;//数据库用的唯一键
     * private long userID;//
     * private long classID;//
     * private long taskLatestTime;//最近一次留作业的时间
     * private boolean isShowReddot;//是否显示小红点
     * private long classState;//1,已加入！2，待审核
     * private String className;//班级名字
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "id", true, _id);
        public final static Property userId = new Property(1, Long.class, "userId", true, USERID);
        public final static Property classID = new Property(2, Long.class, "classID", true, CLASSID);
        public final static Property taskLatestTime = new Property(3, Long.class, "taskLatestTime", true, TASKLATESTTIME);
        public final static Property isShowReddot = new Property(4, String.class, "isShowReddot", true, ISSHOWREDDOT);
        public final static Property classState = new Property(5, Long.class, "classState", true, CLASSSTATE);
        public final static Property className = new Property(6, String.class, "className", true, CLASSNAME);
    }

    @Override
    protected ClassTabelBean readEntity(Cursor cursor, int offset) {
        int i = 1;
        return new ClassTabelBean(
                cursor.isNull(offset) ? null : cursor.getLong(offset),
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                Boolean.valueOf(cursor.getString(offset + i++)),
                cursor.getLong(offset + i++),//
                cursor.getString(offset + i++));
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getLong(offset);
    }

    @Override
    protected void readEntity(Cursor cursor, ClassTabelBean entity, int offset) {
        int i = 1;
        entity.setID(cursor.getLong(offset));//0
        entity.setUserID(cursor.getLong(offset + i++));
        entity.setClassID(cursor.getLong(offset + i++));//
        entity.setTaskLatestTime(cursor.getLong(offset + i++));
        entity.setShowReddot(Boolean.valueOf(cursor.getString(offset + i)));//
        entity.setClassState(cursor.getLong(offset + i++));//
        entity.setClassName(cursor.getString(offset + i++));//
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, ClassTabelBean entity) {
        stmt.clearBindings();
        int i = 0;
        Long id = entity.getID();
        i++;
        if (id != null)
            stmt.bindLong(i, id);

        long userId = entity.getUserID();
        i++;
        if (userId != 0)
            stmt.bindLong(i, userId);

        long classID = entity.getClassID();
        i++;
        if (classID != 0)
            stmt.bindLong(i, classID);

        long taskLatestTime = entity.getTaskLatestTime();
        i++;
        if (taskLatestTime != 0)
            stmt.bindLong(i, taskLatestTime);

        i++;
        stmt.bindString(i, String.valueOf(entity.isShowReddot()));


        long classState = entity.getClassState();
        i++;
        if (classState != 0)
            stmt.bindLong(i, classState);

        String className = entity.getClassName();
        i++;
        if (className != null)
            stmt.bindString(i, className);
    }

    @Override
    protected Long updateKeyAfterInsert(ClassTabelBean entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(ClassTabelBean entity) {
        if (entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }


    /**
     * Drops the underlying database table.
     * 降下基础数据库表。
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + TABLENAME;
        db.execSQL(sql);
    }

    /**
     * Creates the underlying database table.
     * 创建基础数据库表
     * private long myNum;//我的分数
     * private boolean state;//否超过上次的分数
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + TABLENAME + " (" +
                _id + " INTEGER PRIMARY KEY ," + // 0: id  唯一键
                USERID + " INTEGER," + //
                CLASSID + " INTEGER," + //
                TASKLATESTTIME + " INTEGER," + //
                ISSHOWREDDOT + " TEXT," + //
                CLASSSTATE + " INTEGER," + //
                CLASSNAME + " TEXT);"); //
    }
}
