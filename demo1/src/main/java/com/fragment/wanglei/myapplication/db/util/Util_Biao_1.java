package com.fragment.wanglei.myapplication.db.util;


import com.fragment.wanglei.myapplication.db.bean.Biao_1_bean;
import com.fragment.wanglei.myapplication.db.dao.Biao_1_Dao;
import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/22  14:06
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Util_Biao_1 extends DBUtilBase<Biao_1_Dao,Biao_1_bean>{

    public long addBean(Biao_1_Dao myDao, Biao_1_bean bean) {
        return myDao.insert(bean);
    }

    public void addList(Biao_1_Dao myDao, List<Biao_1_bean> list) {
        myDao.insertInTx(list);
    }

    public void deleteById(Biao_1_Dao myDao, Long l) {
        myDao.deleteByKey(l);
    }

    public void deleteAll(Biao_1_Dao myDao) {
        myDao.deleteAll();
    }

    public void delete(Biao_1_Dao myDao, Biao_1_bean bean) {
        myDao.delete(bean);
    }

    public void update(Biao_1_Dao myDao, Biao_1_bean bean) {
        myDao.update(bean);
    }

    public void updateList(Biao_1_Dao myDao, List<Biao_1_bean> list) {
        myDao.updateInTx(list);
    }

    public List<Biao_1_bean> getAll(Biao_1_Dao myDao) {
        return myDao.loadAll();
    }

    public Biao_1_bean getOneInfo(Biao_1_Dao myDao, Long l) {
        return myDao.load(l);
    }

    @Override
    public List<Biao_1_bean> getListInfo(Biao_1_Dao myDao, String field, String... condition) {
        return myDao.queryRaw(" WHERE " + field + " = ?", condition);
    }

    public List<Biao_1_bean> getListInfo1(Biao_1_Dao myDao, String field, String... condition) {

        return myDao.queryRaw(" WHERE " + field + " = ?", condition);
    }
}
