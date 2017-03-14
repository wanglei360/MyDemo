package db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import db.greendao.communal.DaoMaster;
import db.greendao.communal.Session;

/**
 * 创建者：wanglei
 * <p>时间：16/6/21  16:22
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：todo 作业目录还么有UserId，要添加上
 */
public class MyApplication extends Application {

    public static Session daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        getDaoSession();
    }

    /**
     * 初始化数据库
     */
    public void getDaoSession() {
        SQLiteDatabase db = new DaoMaster.DevOpenHelper(this.getApplicationContext(), Constants.DBNAME, null).getWritableDatabase();//获取可写的数据库
        DaoMaster daoMaster = new DaoMaster(db);//在数据库中建表
        if(daoSession == null)
            daoSession = daoMaster.newSession();
    }
}
