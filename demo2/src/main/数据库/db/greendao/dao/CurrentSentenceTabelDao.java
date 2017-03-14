package db.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import db.greendao.bean.CurrentSentenceBean;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * 创建者：wanglei
 * <p>时间：16/6/29  11:15
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class CurrentSentenceTabelDao extends AbstractDao<CurrentSentenceBean, Long> {
    public static final String TABLENAME = "currentSentence";//数据库的表命
    public static final String _id = "_id";//ID
    public static final String USERID = "userId";//
    public static final String CLASSID = "classID";//
    public static final String TASKID = "taskID";//作业ID
    public static final String PARTID = "partID";//题ID
    public static final String SENTENCEID = "sentenceID";//句子ID
    public static final String TYPE = "type";//句子类型1：跟读！2：朗读！3：背诵
    public static final String TEXT = "text";//文本
    public static final String TEXTCOLOR = "textColor";//变色文本
    public static final String NETADDRESS = "netAddress";//泛音地址
    public static final String MYADDRESS = "myAddress";//我的声音本地地址
    public static final String CHISHENGNETADDRESS = "chishengNetAddress";//弛声的网络声音地址
    public static final String MYVOICETIME = "myVoiceTime";//我的声音时长
    public static final String MYNUM = "myNum";//我的分数
    public static final String STATE = "state";//否超过上次的分数

    public CurrentSentenceTabelDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Properties of entity Note.<br/>
     * 实体注释的属性。
     * Can be used for QueryBuilder and for referencing column names.
     * 可用于querybuilder和引用列的名称。
     * <p/>
     * private boolean ;//否超过上次的分数
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "id", true, _id);
        public final static Property userId = new Property(1, Long.class, "userId", true, USERID);
        public final static Property classID = new Property(2, Long.class, "classID", true, CLASSID);
        public final static Property taskID = new Property(3, Long.class, "taskID", true, TASKID);
        public final static Property partID = new Property(4, Long.class, "partID", true, PARTID);
        public final static Property sentenceID = new Property(5, Long.class, "sentenceID", true, SENTENCEID);
        public final static Property type = new Property(6, Long.class, "type", true, TYPE);
        public final static Property text = new Property(7, String.class, "text", true, TEXT);
        public final static Property textColor = new Property(8, String.class, "textColor", true, TEXTCOLOR);
        public final static Property netAddress = new Property(9, String.class, "netAddress", true, NETADDRESS);
        public final static Property myAddress = new Property(10, String.class, "myAddress", true, MYADDRESS);
        public final static Property chishengNetAddress = new Property(11, String.class, "chishengNetAddress", true, CHISHENGNETADDRESS);
        public final static Property myVoiceTime = new Property(12, Long.class, "myVoiceTime", true, MYVOICETIME);
        public final static Property myNum = new Property(13, Long.class, "myNum", true, MYNUM);
        public final static Property state = new Property(14, String.class, "state", true, STATE);

    }

    @Override
    protected CurrentSentenceBean readEntity(Cursor cursor, int offset) {
        int i = 1;
        return new CurrentSentenceBean(
                cursor.isNull(offset) ? null : cursor.getLong(offset),
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getString(offset + i++),//
                cursor.getString(offset + i++),//
                cursor.getString(offset + i++),//
                cursor.getString(offset + i++),//
                cursor.getString(offset + i++),//
                cursor.getLong(offset + i++),//
                cursor.getLong(offset + i++),//
                Boolean.valueOf(cursor.getString(offset + i++)));
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getLong(offset);
    }

    @Override
    protected void readEntity(Cursor cursor, CurrentSentenceBean entity, int offset) {
        int i = 1;
        entity.setID(cursor.getLong(offset));//0
        entity.setUserId(cursor.getLong(offset + i++));//
        entity.setClassID(cursor.getLong(offset + i++));//
        entity.setTaskID(cursor.getLong(offset + i++));
        entity.setPartID(cursor.getLong(offset + i++));//
        entity.setSentenceID(cursor.getLong(offset + i++));//
        entity.setType(cursor.getLong(offset + i++));//
        entity.setText(cursor.getString(offset + i++));//
        entity.setTextColor(cursor.getString(offset + i++));//
        entity.setNetAddress(cursor.getString(offset + i++));//
        entity.setMyAddress(cursor.getString(offset + i++));//
        entity.setChishengNetAddress(cursor.getString(offset + i++));//
        entity.setMyVoiceTime(cursor.getLong(offset));//
        entity.setMyNum(cursor.getLong(offset));//
        entity.setState(Boolean.valueOf(cursor.getString(offset + i)));//
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, CurrentSentenceBean entity) {
        stmt.clearBindings();
        int i = 0;
        Long id = entity.getID();
        i++;
        if (id  != null)
            stmt.bindLong(i, id);

        long userId = entity.getUserId();
        i++;
        if (userId  != 0)
            stmt.bindLong(i, userId);

        long classID = entity.getClassID();
        i++;
        if (classID  != 0)
            stmt.bindLong(i, classID);

        long taskID = entity.getTaskID();
        i++;
        if (taskID  != 0)
            stmt.bindLong(i, taskID);

        long partID = entity.getPartID();
        i++;
        if (partID  != 0)
            stmt.bindLong(i, partID);

        long sentenceID = entity.getSentenceID();
        i++;
        if (sentenceID  != 0)
            stmt.bindLong(i, sentenceID);

        long type = entity.getType();
        i++;
        if (type  != 0)
            stmt.bindLong(i, type);

        String text = entity.getText();
        i++;
        if (text  != null)
            stmt.bindString(i, text);

        String textColor = entity.getTextColor();
        i++;
        if (textColor  != null)
            stmt.bindString(i, textColor);

        String netAddress = entity.getNetAddress();
        i++;
        if (netAddress  != null)
            stmt.bindString(i, netAddress);

        String myAddress = entity.getMyAddress();
        i++;
        if (myAddress  != null)
            stmt.bindString(i, myAddress);

        String chishengNetAddress = entity.getChishengNetAddress();
        i++;
        if (chishengNetAddress  != null)
            stmt.bindString(i, chishengNetAddress);

        long myVoiceTime = entity.getMyVoiceTime();
        i++;
        if (myVoiceTime  != 0)
            stmt.bindLong(i, myVoiceTime);

        long myNum = entity.getMyNum();
        i++;
        if (myNum  != 0)
            stmt.bindLong(i, myNum);

        i++;
        stmt.bindString(i, String.valueOf(entity.isState()));

    }

    @Override
    protected Long updateKeyAfterInsert(CurrentSentenceBean entity, long rowId) {
        entity.setID(rowId);
        return rowId;    }

    @Override
    protected Long getKey(CurrentSentenceBean entity) {
        if (entity != null) {
            return entity.getID();
        } else {
            return null;
        }    }

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
     private long myNum;//我的分数
     private boolean state;//否超过上次的分数
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + TABLENAME + " (" +
                _id + " INTEGER PRIMARY KEY ," + // 0: id  唯一键
                USERID + " INTEGER," + //
                CLASSID + " INTEGER," + //
                TASKID + " INTEGER," + //
                PARTID + " INTEGER," + //
                SENTENCEID + " INTEGER," + //
                TYPE + " INTEGER," + //
                TEXT + " TEXT," + //
                TEXTCOLOR + " TEXT," + //
                NETADDRESS + " TEXT," + //
                MYADDRESS + " TEXT," + //
                CHISHENGNETADDRESS + " TEXT," + //
                MYVOICETIME + " INTEGER," + //
                MYNUM + " INTEGER," + //
                STATE + " TEXT);"); //
    }
}

