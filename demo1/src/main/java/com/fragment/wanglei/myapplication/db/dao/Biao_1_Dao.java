package com.fragment.wanglei.myapplication.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fragment.wanglei.myapplication.db.bean.Biao_1_bean;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * 创建者：wanglei
 * 时间：16/4/20  15:04
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Biao_1_Dao extends AbstractDao<Biao_1_bean, Long> {

    public static final String TABLENAME = "biao_1";//数据库的表命
    public static final String _id = "_id";//字段名字
    public static final String TEXT = "TEXT";
    public static final String COMMENT = "COMMENT";
    public static final String DATE = "DATE";
    public static final String DATE1 = "DATE1";


    public Biao_1_Dao(DaoConfig config, AbstractDaoSession daoSession) {//  构造
        super(config, daoSession);
    }

    /**
     * Properties of entity Note.<br/>
     * 实体注释的属性。
     * Can be used for QueryBuilder and for referencing column names.
     * 可用于querybuilder和引用列的名称。
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, _id);
        public final static Property Text = new Property(1, String.class, "text", false, TEXT);
        public final static Property Comment = new Property(2, String.class, "comment", false, COMMENT);
        public final static Property Date = new Property(3, java.util.Date.class, "date", false, DATE);
    };


    @Override
    protected Biao_1_bean readEntity(Cursor cursor, int offset) {
        Biao_1_bean entity = new Biao_1_bean( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // text
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // comment
                cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)) // date
        );
        return entity;
    }

    @Override
    protected void readEntity(Cursor cursor, Biao_1_bean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setText(cursor.getString(offset + 1));
        entity.setComment(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Biao_1_bean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());

        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(3, comment);
        }

        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(4, date.getTime());
        }
    }

    @Override
    protected Long updateKeyAfterInsert(Biao_1_bean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Biao_1_bean entity) {
        if (entity != null) {
            return entity.getId();
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
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";

        db.execSQL("CREATE TABLE " + constraint + TABLENAME + " (" +
                _id + " INTEGER PRIMARY KEY ," + // 0: id  唯一键
                TEXT + " TEXT," + // 1: text不能为null
                COMMENT + " TEXT," + // 2: comment 是text类型
                DATE + " INTEGER);"); // 3: date 是integer类型
    }
}
