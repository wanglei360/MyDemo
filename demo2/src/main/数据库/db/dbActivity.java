package db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.myplayer.R;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.greendao.bean.BestSentenceBean;
import db.greendao.bean.ClassTabelBean;
import db.greendao.bean.CurrentSentenceBean;
import db.greendao.bean.TaskDirectoryBean;
import db.greendao.bean.TaskListBean;
import db.greendao.dao.BestSentenceTabelDao;
import db.greendao.dao.ClassTabelDao;
import db.greendao.dao.CurrentSentenceTabelDao;
import db.greendao.dao.TaskDirectoryTabelDao;
import db.greendao.dao.TaskListTabelDao;
import db.greendao.utils.UtilsDB;
import de.greenrobot.dao.AbstractDao;

/**
 * 创建者：wanglei
 * <p>时间：16/6/21  16:21
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class dbActivity extends Activity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private MyAdapter adapter;
    private TaskListTabelDao taskListTabelDao;
    private List<TaskListBean> taskListBeen;
    private TaskDirectoryTabelDao taskDirectoryTabelDao;
    private BestSentenceTabelDao bestSentenceConfigDao;
    private CurrentSentenceTabelDao currentSentenceTabelDao;
    private ClassTabelDao classTabelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        editText = (EditText) findViewById(R.id.editTextNote);
        ListView listView = (ListView) findViewById(R.id.list);

        taskListTabelDao = MyApplication.daoSession.getTaskListTabelDao();
        taskDirectoryTabelDao = MyApplication.daoSession.getTaskDirectoryTabelDao();
        bestSentenceConfigDao = MyApplication.daoSession.getBestSentenceTabelDao();
        currentSentenceTabelDao = MyApplication.daoSession.getCurrentSentenceTabelDao();
        classTabelDao = MyApplication.daoSession.getClassTabelDao();
        taskListBeen = taskListTabelDao.loadAll();

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        int i = 1;
        int y = 1;
        int x = 1;
        int w = 1;
        Log.i("iii", " i = " + (i++));
        Log.i("iii", " y = " + (++y));
        Log.i("iii", " x = " + ++x);
        Log.i("iii", " w = " + w++);
    }

    //    boolean ee = false;
    private String newFileName(String str) {
        String folder;
        if (Environment.getExternalStorageState().equals(// 如果sd卡挂载了
                android.os.Environment.MEDIA_MOUNTED)) {
            folder = Environment.getExternalStorageDirectory()// 存SD卡上
                    + File.separator;
        } else {
            folder = Environment.getRootDirectory() + File.separator;// 否则存机器上
        }
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
//		return folder + "gequwww" + ".mp3";
//		return folder + "gequqq" + ".mp3";
//		return folder + "pppp" + ".mp3";
//		return folder + "zz" + ".wav";//456
//		return folder + "xx" + ".wav";//123
        return folder + str + ".log";//123
    }


    public TaskListBean getTaskListBeen(int l) {
        return new TaskListBean(Long.valueOf(1111), 22222, l, 444444, 555555, 666666, true, 88888, 9999, "", 11111, 22222, 33333, true, 11111, "", 111);
    }

    public TaskListBean getTaskListBeen1() {
        return new TaskListBean(Long.valueOf(1111), 9999999, 333333, 444444, 555555, 666666, true, 88888, 9999, "", 11111, 22222, 33333, true, 11111, "asdfasdfsadfasdf", 111);
    }

    public void but_but(View v) {

        note_1 = new TaskListBean(Long.valueOf(1), qqq, qqq, www, Long.MAX_VALUE, (long) Integer.MAX_VALUE + 111, false
                , 66666666, Long.valueOf(777777777), "操蛋的玩意，不知道这是啥", Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的taskRequirement", 12121212);
        taskListTabelDao.update(note_1);


//        ArrayList<TaskListBean> taskListBeen = new ArrayList<>();
//        for (int x = 0; x < 10; x++) {
//            taskListBeen.add(getTaskListBeen(x));
//        }
//
//        ArrayList<TaskListBean> taskListBeen1 = new ArrayList<>();
//        for (int x = 0; x < 10; x++) {
//            if (x < 5)
//                taskListBeen1.add(getTaskListBeen1());
//            else {
//                taskListBeen1.add(getTaskListBeen(x));
//            }
//        }
//        boolean b = taskListBeen.removeAll(taskListBeen1);
//        Log.i("","");

//        String smart_jin = newFileName("com.baidu.appsearch");
//        File file = new File(smart_jin);
//        boolean delete = file.delete();
//        Log.i("","");


//        SQLiteDatabase database = taskListTabelDao.getDatabase();
//        database.execSQL("delete from taskList where "+TaskListTabelDao.USERID+" = 12121212;");
//        database.execSQL("delete from taskList where "+TaskListTabelDao.USERID+" = -2147482649;");
//        database.execSQL("delete from taskList where "+TaskListTabelDao.ISEVALUATE+"=\"true\";");


//        long l = 13;
//        long ll = 16;
//        taskListTabelDao.deleteByKey(l);
//        UtilsDB.delete(taskListTabelDao,note_1);
//        UtilsDB.deleteAll(taskListTabelDao);
//        List<Long> longs = new ArrayList<>();
//        longs.add(ll);
//        longs.add(l);
//        UtilsDB.deleteByKeys(taskListTabelDao,longs);
//        UtilsDB.deleteByKeys(taskListTabelDao,l,ll);
//        taskListTabelDao.update(note);
//        taskListTabelDao.updateInTx();
//        taskListTabelDao.updateInTx();

//        List<TaskListBean> listInfo = getListInfo(taskListTabelDao, TaskListTabelDao.ISEVALUATE, new String[]{"true"});
//        List<TaskListBean> listInfo = getListInfo(taskListTabelDao, TaskListBean.class, TaskListTabelDao.ISEVALUATE, new String[]{"true"});
//        Log.i("22222", listInfo.size() + "");
//        for (TaskListBean tlb : listInfo) {
//            boolean evaluate = tlb.isEvaluate();
//            Toast.makeText(dbActivity.this, evaluate + "", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * 按照单一字段查询，查询符合field的condition值。getListInfo(taskListTabelDao, TaskListBean.class, TaskListTabelDao.ISEVALUATE, new String[]{"true"});
     *
     * @param dao       要查询哪个表的dao对象
     * @param field     要查询这个表中的哪个字段
     * @param condition 符合field字段的值
     * @param cls       返回来的集合应该用哪个bean装
     * @return 返回查询到的集合
     */
    public <T> List<T> getListInfo(AbstractDao dao, Class<T> cls, String field, String... condition) {
        return dao.queryRaw(" WHERE " + field + " = ?", condition);
    }

    public void onMyButtonClick(View view) {
        addTaskListInfo();
        addTaskDirectoryInfo();
    }

    TaskDirectoryBean bean;
    BestSentenceBean bean_1;
    CurrentSentenceBean bean_2;

    private void addTaskDirectoryInfo() {
        final String noteText = editText.getText().toString();
        editText.setText("");
        new Thread() {
            @Override
            public void run() {
// long userId, long taskID, long partID, long sentenceID, long type, String text,
// String , String , String , String , long myVoiceTime, long myNum, boolean state
                for (int x = 0; x < 100; x++) {
//                    bean = new TaskDirectoryBean(null, 11111 + x, 222222 + x, 333333 + x, 444444 + x, 555555 + x, false, "partName", 66666 + x, 77777 + x, 88888 + x);
//                    UtilsDB.addAInfo(taskDirectoryTabelDao, bean);
                    bean_1 = new BestSentenceBean(null, 1111111, 1111111 + x, 22222222 + x, 3333333 + x, 44444444 + x, 5555555 + x, "text" + x, "textColor" + x, "netAddress" + x, "myAddress" + x, "chishengNetAddress" + x, 666666 + x, 77777777 + x, true);
                    UtilsDB.addAInfo(bestSentenceConfigDao, bean_1);
                    bean_2 = new CurrentSentenceBean(null, 1111111, 1111111 + x, 22222222 + x, 3333333 + x, 44444444 + x, 5555555 + x, "text" + x, "textColor" + x, "netAddress" + x, "myAddress" + x, "chishengNetAddress" + x, 666666 + x, 77777777 + x, true);
                    UtilsDB.addAInfo(currentSentenceTabelDao, bean_2);
                    Log.i("add", x + "");
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (long x = 0; x < 100; x++) {
                    List<TaskListBean> listInfo = UtilsDB.getListInfo(taskListTabelDao, TaskListBean.class, TaskListTabelDao._id, new String[]{"" + x});
                    List<TaskDirectoryBean> listInfo1 = UtilsDB.getListInfo(taskDirectoryTabelDao, TaskDirectoryBean.class, TaskDirectoryTabelDao._id, new String[]{"" + x});
                    List<BestSentenceBean> listInfo2 = UtilsDB.getListInfo(bestSentenceConfigDao, BestSentenceBean.class, BestSentenceTabelDao._id, new String[]{"" + x});
                    List<CurrentSentenceBean> listInfo3 = UtilsDB.getListInfo(currentSentenceTabelDao, CurrentSentenceBean.class, CurrentSentenceTabelDao._id, new String[]{"" + x});
                }
            }
        }.start();
    }

    int ee = 1;
    long qqq = 11111;
    long www = 22222;
    TaskListBean note;
    TaskListBean note_1;

    //  String isEvaluate, Long week, String taskRequirement
    private void addTaskListInfo() {
        final String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);//获取一个时间
        String comment = "Added on " + df.format(new Date());
        final int ll = Integer.MAX_VALUE;

//        new Thread() {//1721400
//            @Override
//            public void run() {
        note_1 = new TaskListBean(null, qqq, qqq, www, Long.MAX_VALUE, (long) Integer.MAX_VALUE + 111, false
                , 66666666, Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的taskRequirement", 12121212);
        taskListTabelDao.insert(note_1);
        long l1 = Debug.threadCpuTimeNanos();//只获取当前线程的执行时间，所以更准确
        ArrayList<TaskListBean> taskListBeen = new ArrayList<>();
        boolean b = false;
        for (int l = 0; l < 100; l++) {
            if (ee == 1) {
                if (!b) {
                    note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, (long) Integer.MAX_VALUE + 111, false
                            , 66666666, Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement", 12121212);
                    b = !b;
                } else {

                    note = new TaskListBean(null, 1111111, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, (long) Integer.MAX_VALUE + 111, false
                            , 66666666, Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement", 12121212);
                    b = !b;
                }
                ee = 2;
            } else if (ee == 2) {
                note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, Long.valueOf(Integer.MAX_VALUE), true
                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的", 0);
//                        note = new TaskListBean(null, Long.valueOf(Integer.MAX_VALUE-1), Long.valueOf(2222222), Long.valueOf(333333333), Long.valueOf(444444444), Long.valueOf(55555555)
//                                , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement");
                ee = 3;
            } else {
                note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, Long.valueOf(Integer.MAX_VALUE), true
                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的", Integer.MAX_VALUE + 1000);
                ee = 1;
            }
            taskListBeen.add(note);
//            taskListTabelDao.insert(note);
            Log.i("shuzi", "l = " + l);
        }
        ArrayList<TaskListBean> taskListBeen1 = new ArrayList<>();
        ArrayList<ClassTabelBean> classTabelBean = new ArrayList<>();
        for (int l = 0; l < 100; l++) {
            if (ee == 1) {
                note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, (long) Integer.MAX_VALUE + 111, false
                        , 66666666, Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement", 12121212);
                ee = 2;
            } else if (ee == 2) {
                note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, Long.valueOf(Integer.MAX_VALUE), true
                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的", 0);
//                        note = new TaskListBean(null, Long.valueOf(Integer.MAX_VALUE-1), Long.valueOf(2222222), Long.valueOf(333333333), Long.valueOf(444444444), Long.valueOf(55555555)
//                                , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement");
                ee = 3;
            } else {
                note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE + l, Long.MAX_VALUE - l, Long.valueOf(Integer.MAX_VALUE), true
                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的", Integer.MAX_VALUE + 1000);
                ee = 1;
            }
            classTabelBean.add(new ClassTabelBean(null, 11111, 22222, 33333, false, 444444, ""));
            taskListBeen1.add(note);
//            taskListTabelDao.insert(note);
            Log.i("shuzi", "l = " + l);
        }
        taskListTabelDao.insertInTx(taskListBeen);
        taskListBeen.clear();
        note = new TaskListBean(null, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.valueOf(Integer.MAX_VALUE), true
                , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的", Integer.MAX_VALUE + 1000);
        taskListBeen.add(note);
        taskListTabelDao.insertInTx(taskListBeen);
//        classTabelDao.insertInTx(classTabelBean);
//        DBUtils.getDbUtils().leaveHome_1(12121212,1111111,classTabelBean,taskListBeen1);
        long l2 = Debug.threadCpuTimeNanos();//只获取当前线程的执行时间，所以更准确
        Log.i("time", "     <<<<<<<<<<<<<<<<<<      l2-l1 = " + (l2 - l1) + "  l1=" + l1 + "  l2 = " + l2);
        Log.i("time", "     <<<<<<<<<<<<<<<<<<      l2-l1 = " + (l2 - l1) + "  l1=" + l1 + "  l2 = " + l2);
        Log.i("time", "     <<<<<<<<<<<<<<<<<<      l2-l1 = " + (l2 - l1) + "  l1-l2=" + (l1 - l2));
        this.taskListBeen = taskListTabelDao.loadAll();
        handler.sendEmptyMessage(0);
//            }
//        }.start();
//        for(long l=0;l<ll;l++){
//            if (ee) {
//                note = new TaskListBean(null, Long.valueOf(111111), Long.valueOf(2222222), Long.valueOf(333333333), Long.valueOf(444444444), Long.valueOf(55555555)
//                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), true, Long.valueOf(12121212), "我自己写的taskRequirement");
//                ee = !ee;
//            } else {
//                note = new TaskListBean(null, Long.valueOf(111111), Long.valueOf(2222222), Long.valueOf(333333333), Long.valueOf(444444444), Long.valueOf(55555555)
//                        , Long.valueOf(66666666), Long.valueOf(777777777), noteText, Long.valueOf(99999999), Long.valueOf(10101010), Long.valueOf(11111111), false, Long.valueOf(12121212), "我自己写的taskRequirement");
//                ee = !ee;
//            }
//        }
//        taskListTabelDao.insert(note);
//        taskListBeen = taskListTabelDao.loadAll();
//        adapter.notifyDataSetChanged();
//        handler.sendEmptyMessage(0);
//        TaskListBean biao_2_bean = new TaskListBean(null, noteText, comment, new Date(), new Date());
//        myDao_1.insert(biao_2_bean);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.i("db>>>>>", "position = " + position + "   id = " + id);
//        taskListTabelDao.deleteByKey(id);
//        Log.d("DaoExample", "Deleted note, ID: " + id);

//        QueryBuilder<TaskListBean> tbqb = taskListTabelDao.queryBuilder();
//        tbqb.where(TaskListTabelDao.Properties.userId.eq(12121212));
////        tbqb.where(TaskListTabelDao.Properties.userId.eq(-2147482649));
//        List<Long> integers = new ArrayList<>();
//        List<TaskListBean> list = tbqb.list();
//        for(TaskListBean tlb:list){
//            integers.add(tlb.getID());
//        }
//        taskListTabelDao.deleteByKeyInTx(integers);
        SQLiteDatabase database = taskListTabelDao.getDatabase();
        database.execSQL("delete from taskList where " + TaskListTabelDao.USERID + " in(12121212,-2147482649) and " + TaskListTabelDao.ISEVALUATE + "=\"true\";");


        taskListBeen = taskListTabelDao.loadAll();
        adapter.notifyDataSetChanged();
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int size = taskListBeen.size();
            if (taskListBeen.size() > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return taskListBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return taskListBeen.get(position).getID();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(dbActivity.this, R.layout.item_db, null);
            TextView text1 = (TextView) view.findViewById(R.id.text1);
            TextView text2 = (TextView) view.findViewById(R.id.text2);
            TaskListBean myBean = taskListBeen.get(position);
            text1.setText(String.valueOf(myBean.isEvaluate()));
            text2.setText(String.valueOf(myBean.getUserId()));
//            long ID = myBean.getID();
//            if (ID == 2) {
//                Log.i("myBean","getID = "+myBean.getID());
//                Log.i("myBean","getUserId = "+myBean.getUserId());
//                Log.i("myBean","getClassID = "+myBean.getClassID());
//                Log.i("myBean","getTaskID = "+myBean.getTaskID());
//                Log.i("myBean","getTaskSubmitTime = "+myBean.getTaskSubmitTime());
//                Log.i("myBean","getTaskEndTime = "+myBean.getTaskEndTime());
//                Log.i("myBean","getTaskTime = "+myBean.getTaskTime());
//                Log.i("myBean","getTaskTimeCurrent = "+myBean.getTaskTimeCurrent());
//                Log.i("myBean","getTaskLastSubmitTime = "+myBean.getTaskLastSubmitTime());
//                Log.i("myBean","getTaskName = "+myBean.getTaskName());
//                Log.i("myBean","getTaskNum = "+myBean.getTaskNum());
//                Log.i("myBean","getTaskState = "+myBean.getTaskState());
//                Log.i("myBean","getTaskDegree = "+myBean.getTaskDegree());
//                Log.i("myBean","isEvaluate = "+myBean.isEvaluate());
//                Log.i("myBean","getWeek = "+myBean.getWeek());
//                Log.i("myBean","getTaskRequirement = "+myBean.getTaskRequirement());
//            }

            return view;
        }
    }


}
