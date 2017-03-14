package db.greendao.utils;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 创建者：wanglei
 * <p>时间：16/6/22  10:07
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class UtilsDB {


    /**
     * 添加（单条）
     * @param dao 要往哪个表中添加数据的dao对象
     * @param cls 添加的数据对象
     */
    public static <T> void addAInfo(AbstractDao dao, T cls) {
        dao.insert(cls);
    }

    /**添加(批量)
     * @param dao   要往哪个表中添加数据的dao对象
     * @param list  添加的数据源
     */
    public static void addInfo(AbstractDao dao, List<?> list) {
        dao.insertInTx(list);
    }

//-----------------------------------todo--------------------------------------------

    /** 删除（清空）
     * 清空表
     * @param dao 要往哪个表中添加数据的dao对象
     */
    public static void deleteAll(AbstractDao dao) {
        dao.deleteAll();
    }

    /**删除（删除一条数据）
     * 删除这个bean的这一条数据
     * @param dao 要往哪个表中添加数据的dao对象
     * @param bean 要删除的这个bean
     */
    public static <T> void delete(AbstractDao dao ,T bean) {
        dao.delete(bean);
    }

    /**删除（删除一条数据）
     * 删除这个指定的id的这一条数据
     * @param dao 要往哪个表中添加数据的dao对象
     * @param key 指定的ID（是ID这个字段下的数字）
     */
    public static void deleteByKey(AbstractDao dao ,long key) {
        dao.deleteByKey(key);
    }

    /**删除（删除几条数据）
     * 删除这个指定的id的这几条数据
     * @param dao 要往哪个表中添加数据的dao对象
     * @param keys 指定的一堆ID（是ID这个字段下的数字）
     */
    public static void deleteByKeys(AbstractDao dao ,Long... keys) {
//        dao.deleteByKeyInTx(keys);
    }

    /**删除（删除几条数据）
     * 删除这个指定的id的这几条数据
     * @param dao 要往哪个表中添加数据的dao对象
     * @param keys 指定的一堆ID（是ID这个字段下的数字）
     */
    public static void deleteByKeys(AbstractDao dao ,Iterable<Long> keys) {
        dao.deleteByKeyInTx(keys);
    }
//-----------------------------------todo--------------------------------------------
    /**
     * 查询（单字段）
     * 按照单一字段查询，查询符合field的condition值。
     * getListInfo(taskListTabelDao, TaskListBean.class, TaskListTabelDao.ISEVALUATE, new String[]{"true"});
     *
     * @param dao       要查询哪个表的dao对象
     * @param cls       返回来的集合应该用哪个bean装
     * @param field     要查询这个表中的哪个字段
     * @param condition 符合field字段的值
     * @return 返回查询到的集合
     */
    public static <T> List<T> getListInfo(AbstractDao dao, Class<T> cls, String field, String... condition) {
        return dao.queryRaw(" WHERE " + field + " = ?", condition);
    }

    /**
     * 查询（多字段）
     * <p>getListInfo(taskListTabelDao, TaskListBean.class, new String[]{"true,false"},TaskListTabelDao.Properties.ID,TaskListTabelDao.Properties.classId);
     *
     * @param dao       要查询哪个表的dao对象
     * @param t         返回来的集合应该用哪个bean装
     * @param field     dao层中的那个Property的静态对象中的字段
     * @param condition 查询的条件
     * @return 返回多条件查询到的结果
     */
    public static List<?> getListInfo(AbstractDao dao, Class<?> t, String[] condition, Property... field) {
        QueryBuilder<?> jbqb = dao.queryBuilder();
        int length = condition.length;
        for (int x = 0; x < length; x++) {
//            jbqb.where(Job_Dao.Properties.Cdate.eq("2016-06-12-13-50-57"));//字符串是条件，不是代码中的
            jbqb.where(field[x].eq(condition[x]));//字符串是条件，不是代码中的
        }
        return jbqb.list();
    }
//-----------------------------------todo--------------------------------------------

    /**
     * 更新bean的这条数据
     * @param dao   要查询哪个表的dao对象
     * @param bean  新的bean
     */
    public static <T> void updateBean(AbstractDao dao, T bean){
        dao.update(bean);
    }

    /**
     * 更新bean的多条数据
     * @param dao   要查询哪个表的dao对象
     * @param beans  新的bean们
     */
    public static <T> void updateBeans(AbstractDao dao, T... beans){
        dao.updateInTx(beans);
    }

    /**
     * 更新bean的多条数据
     * @param dao   要查询哪个表的dao对象
     * @param beanList  新的bean们
     */
    public static <T> void updateList(AbstractDao dao, Iterable<T> beanList){
        dao.updateInTx(beanList);
    }
}
