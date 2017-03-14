package com.fragment.wanglei.myapplication.db.util;

import android.database.sqlite.SQLiteDatabase;

import com.fragment.wanglei.myapplication.db.dao.Biao_1_Dao;

/**
 * 创建者：wanglei
 * 时间：16/4/22  17:57
 * 类描述：升级数据库   每次升级时 TODO 的地方都是要增加东西的地方
 * 修改人：
 * 修改时间：
 * 修改备注：当前样式是已经升级到版本号为5的时候
 */
public class DBUpdate {
    private boolean isUpdateOk;
    /**TODO
     * 每升级一次就把上一个的版本号在这里加一个，方便版本跳跃升级使用，当前的样子是版本到5了
     */
    private final int DBVERSIONTWO = 2;
    private final int DBVERSIONTHREE = 3;
    private final int DBVERSIONFOUR = 4;

    public DBUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        nowUpdate(db, oldVersion, newVersion);
    }

    //oldVersion = 3  newVersion = 5
    private void nowUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion - oldVersion == 1) {//这样的是普通的版本连续升级
            update(db);
        } else {//这样的是版本跳跃升级
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
                        case DBVERSIONTWO://版本号是2的时候
                            myUpgrade_2(db);
                            break;
                        case DBVERSIONTHREE:
                            myUpgrade_3(db);
                            break;
                        case DBVERSIONFOUR:
                            myUpgrade_4(db);
                            break;
                    }
                    nowVersion += 1;
                }else{
                    isUpdateOk = true;
                    nowUpdate(db, nowVersion, newVersion);
                }
            }
        }
    }

    /**TODO
     * 这个方法永远调用最新的升级的方法
     */
    private void update(SQLiteDatabase db) {
        myUpgrade_5(db);
    }

    /**
     * 版本号是 2 的时候升级的数据库
     */
    private void myUpgrade_2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + Biao_1_Dao.TABLENAME + " ADD " + Biao_1_Dao.DATE1 + " TEXT");
    }

    /**
     * 版本号是 3 的时候升级的数据库
     */
    private void myUpgrade_3(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + Biao_1_Dao.TABLENAME + " ADD " + Biao_1_Dao.DATE1 + " TEXT");
    }

    /**
     * 版本号是 4 的时候升级的数据库
     */
    private void myUpgrade_4(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + Biao_1_Dao.TABLENAME + " ADD " + Biao_1_Dao.DATE1 + " TEXT");
    }

    /**
     * 版本号是 5 的时候升级的数据库
     */
    private void myUpgrade_5(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + Biao_1_Dao.TABLENAME + " ADD " + Biao_1_Dao.DATE1 + " TEXT");
    }


}
