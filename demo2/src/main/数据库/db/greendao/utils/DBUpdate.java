package db.greendao.utils;

import android.database.sqlite.SQLiteDatabase;

/**
 * 创建者：wanglei
 * <p>时间：16/6/22  10:06
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class DBUpdate {

    private boolean isUpdateOk;

    /**
     * TODO
     * 每升级一次就把上一个的版本号在这里加一个，方便版本跳跃升级使用，当前的样子是版本到5了
     */
    private final int DBVERSION_TWO = 2;
    private final int DBVERSION_THREE = 3;
    private final int DBVERSION_SI = 4;


    public DBUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        nowUpdate(db, oldVersion, newVersion);
    }

    private void nowUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion - oldVersion == 1) {//这样的是普通的版本连续升级
            update(db);
        } else {
            /**
             * 多版本升级，当前更新到的版本号
             */
            int nowVersion = oldVersion;
            while (!isUpdateOk) {
                if (nowVersion + 1 != newVersion) {
                    /** TODO
                     * 每次升级这里都加一个当前版本的case块，版本跳跃升级使用
                     */
                    switch (nowVersion + 1) {
                        case DBVERSION_TWO://版本号是2的时候
//                            myUpgrade_2(db);
                            break;
                        case DBVERSION_THREE://版本号是2的时候
//                            myUpgrade_3(db);
                            break;
                        case DBVERSION_SI://版本号是2的时候
                            myUpgrade_4(db);
                            break;
                    }
                    nowVersion += 1;
                } else {
                    isUpdateOk = true;
                    nowUpdate(db, nowVersion, newVersion);
                }
            }
        }
    }

    /**
     * TODO
     * 这个方法永远调用最新的升级的方法
     */
    private void update(SQLiteDatabase db) {
        myUpgrade_4(db);
    }

    private void myUpgrade_2(SQLiteDatabase db) {}
    private void myUpgrade_3(SQLiteDatabase db) {}

    private void myUpgrade_4(SQLiteDatabase db) {
//        db.execSQL("ALTER TABLE " + TaskDirectoryTabelDao.TABLENAME + " ADD " +
//          TaskDirectoryTabelDao.REPEATTIME_1 + " INTEGER");
    }
}
