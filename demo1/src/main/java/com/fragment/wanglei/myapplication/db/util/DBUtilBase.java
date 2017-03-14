package com.fragment.wanglei.myapplication.db.util;

import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/22  14:15
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class DBUtilBase<Dao,Bean> {

    /** TODO
     * 添加单个bean为数据进入到数据库
     * @param myDao
     * @param bean  要添加的bean
     */
    public abstract long addBean(Dao myDao, Bean bean);

    /**TODO
     * 添加一个集合为数据进入到数据库
     * @param list  要添加的数据
     */
    public abstract void addList(Dao myDao,List<Bean> list);

    /**TODO
     * 按照指定的id删除一条数据
     * @param l 指定的ID
     */
    public abstract void deleteById(Dao myDao,Long l);

    /**TODO
     * 删除这个表中所有的数据
     */
    public abstract void deleteAll(Dao myDao);

    /**TODO
     * 指定删除表中的这一条数据
     * @param bean  要删除的这一条的bean
     */
    public abstract void delete(Dao myDao,Bean bean);

    /**TODO
     * 更改数据库的一条数据
     * 例如：new Biao_1_bean(Long.valueOf(2),"单个更改的数据", "单个更改的数据", new Date())
     */
    public abstract void update(Dao myDao,Bean bean);

    /**TODO
     * 更改数据库的多条数据
     * @param list  要更改的数据
     */
    public abstract void updateList(Dao myDao,List<Bean> list);

    /**TODO
     * 获取数据库中的全部信息
     */
    public abstract List<Bean> getAll(Dao myDao);

    /**TODO
     * 按照指定获取数据库中的一条信息
     */
    public abstract Bean getOneInfo(Dao myDao, Long l);

    /**TODO
     * 查找数据库中指定的列明field符合condition的值的数据集合,
     * Util_Biao_1 biao_1 = new Util_Biao_1();\n
     * biao_1.getListInfo(myDao,Biao_1_Dao.TEXT,new String[]{"批量更改的数据"});
     * @param field 指定的数据库的列明
     * @param condition 指定的条件
     */
    public abstract List<Bean> getListInfo(Dao myDao,String field, String... condition);
}
