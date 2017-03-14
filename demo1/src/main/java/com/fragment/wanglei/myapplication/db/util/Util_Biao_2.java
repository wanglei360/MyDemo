package com.fragment.wanglei.myapplication.db.util;

import com.fragment.wanglei.myapplication.db.bean.Biao_2_bean;
import com.fragment.wanglei.myapplication.db.dao.Biao_2_Dao;
import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/22  14:40
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Util_Biao_2 extends DBUtilBase<Biao_2_Dao,Biao_2_bean> {

    @Override
    public long addBean(Biao_2_Dao myDao, Biao_2_bean bean) {
        return myDao.insert(bean);
    }

    @Override
    public void addList(Biao_2_Dao myDao, List<Biao_2_bean> list) {
        myDao.insertInTx(list);
    }

    @Override
    public void deleteById(Biao_2_Dao myDao, Long l) {
        myDao.deleteByKey(l);
    }

    @Override
    public void deleteAll(Biao_2_Dao myDao) {
        myDao.deleteAll();
    }

    @Override
    public void delete(Biao_2_Dao myDao, Biao_2_bean bean) {
        myDao.delete(bean);
    }

    @Override
    public void update(Biao_2_Dao myDao, Biao_2_bean bean) {
        myDao.update(bean);
    }

    @Override
    public void updateList(Biao_2_Dao myDao, List<Biao_2_bean> list) {
        myDao.updateInTx(list);
    }

    @Override
    public List<Biao_2_bean> getAll(Biao_2_Dao myDao) {
        return myDao.loadAll();
    }

    @Override
    public Biao_2_bean getOneInfo(Biao_2_Dao myDao, Long l) {
        return myDao.load(l);
    }

    @Override
    public List<Biao_2_bean> getListInfo(Biao_2_Dao myDao, String field, String... condition) {
        return myDao.queryRaw(" WHERE " + field + " = ?", condition);
    }
}
