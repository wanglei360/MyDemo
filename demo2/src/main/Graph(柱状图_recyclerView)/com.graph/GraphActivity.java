package com.graph;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.graph.graph.My_Graph;
import com.graph.graph.bean.GraphList;
import com.graph.graph.imp.MyOnclik;
import com.myplayer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  13:46
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class GraphActivity extends Activity implements MyOnclik {

    private List<GraphList> graphListList;
    private My_Graph graph;
    private int graphHeight;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    graphListList = initData();
                    if (isGraphInit())
                        initGraph();
                }
            });
        }
    };
    private int width;
    private boolean b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        graph = (My_Graph) findViewById(R.id.graph);
        graph.setMyOnclik(this);

        //模拟网络请求数据，这个方法调用可能在onWindowFocusChanged之前或者之后
        int iii = (int) (Math.random() * 1000);
        timer.schedule(task, 100);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        graphHeight = graph.getHeight();

        width = this.getWindowManager().getDefaultDisplay().getWidth();
        if (isGraphInit())
            initGraph();
    }

    /**
     * 判断初始化图表的两个参数是否已经具备，
     *
     * @return 满足graph舒适化的条件返回true，否则返回false
     */
    public boolean isGraphInit() {
        return !(graphHeight == 0 || null == graphListList || graphListList.size() == 0);
    }

    /**
     * todo
     * 这个方法的调用必须保证graphHeight有值，graphListList里面有数据
     */
    private void initGraph() {
        b = graph.setInit(this.getApplicationContext(), graphHeight, graphListList, width);
        graph.setShowProgressBar(false);
    }

    /**
     * 封装graph所需要的数据源
     *
     * @return 返回数据源
     */
    private List<GraphList> initData() {
        graphListList = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            int iii = (int) (Math.random() * 100);
            GraphList graphList = new GraphList();
            graphList.setBottomNum(String.valueOf(x));
            graphList.setTopNum(String.valueOf(iii));
            graphList.setColumn(String.valueOf(iii));
            graphListList.add(graphList);
        }
        return graphListList;
    }

    @Override
    public void myOnclik(String topNum, int position, boolean isHaveSelected) {
        Toast.makeText(this, topNum + "   " + String.valueOf(position) + "   isMySelected = " + isHaveSelected+ "   b = " + b, Toast.LENGTH_SHORT).show();
    }
}
