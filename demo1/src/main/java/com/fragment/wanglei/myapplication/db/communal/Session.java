package com.fragment.wanglei.myapplication.db.communal;

import android.database.sqlite.SQLiteDatabase;

import com.fragment.wanglei.myapplication.db.bean.Biao_1_bean;
import com.fragment.wanglei.myapplication.db.bean.Biao_2_bean;
import com.fragment.wanglei.myapplication.db.dao.Biao_1_Dao;
import com.fragment.wanglei.myapplication.db.dao.Biao_2_Dao;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * 创建者：wanglei
 * 时间：16/4/20  15:19
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Session extends AbstractDaoSession {

    private final DaoConfig biao_1_Config;
    private final DaoConfig biao_2_Config;

    private final Biao_1_Dao biao_1_dao;
    private final Biao_2_Dao biao_2_dao;

    public Session(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        biao_1_Config = daoConfigMap.get(Biao_1_Dao.class).clone();
        biao_1_dao = new Biao_1_Dao(biao_1_Config, this);
        biao_1_Config.initIdentityScope(type);
        registerDao(Biao_1_bean.class, biao_1_dao);

        biao_2_Config = daoConfigMap.get(Biao_2_Dao.class).clone();
        biao_2_dao = new Biao_2_Dao(biao_2_Config, this);
        biao_2_Config.initIdentityScope(type);
        registerDao(Biao_2_bean.class, biao_2_dao);
    }

    public void clear() {
        biao_1_Config.getIdentityScope().clear();
        biao_2_Config.getIdentityScope().clear();
    }

    public Biao_1_Dao getBiao_1_Dao() {
        return biao_1_dao;
    }



    public Biao_2_Dao getBiao_2_dao() {
        return biao_2_dao;
    }

}
