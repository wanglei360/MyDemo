package db.greendao.communal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import db.Constants;
import db.greendao.dao.BestSentenceTabelDao;
import db.greendao.dao.ClassTabelDao;
import db.greendao.dao.CurrentSentenceTabelDao;
import db.greendao.dao.TaskDirectoryTabelDao;
import db.greendao.dao.TaskListTabelDao;
import db.greendao.utils.DBUpdate;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * 创建者：wanglei
 * <p>时间：16/6/22  09:58
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class DaoMaster  extends AbstractDaoMaster {

    public DaoMaster(SQLiteDatabase db) {//TODO  表这里加东西
        super(db, Constants.SCHEMA_VERSION);
        registerDaoClass(TaskListTabelDao.class);
        registerDaoClass(TaskDirectoryTabelDao.class);
        registerDaoClass(BestSentenceTabelDao.class);
        registerDaoClass(CurrentSentenceTabelDao.class);
        registerDaoClass(ClassTabelDao.class);
    }


    /**
     * Creates underlying database table using DAOs.
     * 使用DAO创建基础数据库表。
     */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {//TODO  表这里加东西
        TaskListTabelDao.createTable(db, ifNotExists);
        TaskDirectoryTabelDao.createTable(db, ifNotExists);
        BestSentenceTabelDao.createTable(db, ifNotExists);
        CurrentSentenceTabelDao.createTable(db, ifNotExists);
        ClassTabelDao.createTable(db, ifNotExists);
    }

    /**
     * Drops underlying database table using DAOs.
     * 底层数据库表使用DAO滴。
     */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {//TODO   表这里加东西
        TaskListTabelDao.dropTable(db, ifExists);
        TaskDirectoryTabelDao.dropTable(db, ifExists);
        BestSentenceTabelDao.dropTable(db, ifExists);
        CurrentSentenceTabelDao.dropTable(db, ifExists);
        ClassTabelDao.dropTable(db, ifExists);
    }


    @Override
    public Session newSession() {
        return new Session(db, IdentityScopeType.Session, daoConfigMap);
    }

    @Override
    public AbstractDaoSession newSession(IdentityScopeType type) {
        return null;
    }

    public static abstract class OpenHelper extends SQLiteOpenHelper {

        /**
         * 创建一个辅助对象来创建、打开和/或管理数据库。
         *
         * @param context 上下文
         * @param name    数据库名字
         * @param factory 工厂用于创建游标对象，或默认为空值
         */
        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, Constants.SCHEMA_VERSION);
        }

        /**
         * 称当数据库为第一次创建时。这是创建表和表的初始人口的地方。
         *
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            createAllTables(db, false);
        }

    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * 警告：    删除所有升级表！仅在开发过程中使用
     */
    public static class DevOpenHelper extends OpenHelper {

        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        /**
         * 更新数据库用的
         * @param db         数据库。
         * @param oldVersion 旧的数据库版本。
         * @param newVersion 新的数据库版本。
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "版本升级模式 " + oldVersion + " 和 " + newVersion + " 把所有的桌子都扔了");
            new DBUpdate(db,oldVersion,newVersion);
        }
    }
}
