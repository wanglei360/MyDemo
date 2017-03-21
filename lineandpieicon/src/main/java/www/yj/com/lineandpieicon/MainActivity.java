package www.yj.com.lineandpieicon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import www.yj.com.lineandpieicon.util.LineChartUtil;
import www.yj.com.lineandpieicon.util.PieChartUtil;

public class MainActivity extends Activity {String lineStr = "{\"month\":[{\"x\":\"2017-01月\",\"y\":\"33f\"},{\"x\":\"2017-02月\",\"y\":\"44f\"},{\"x\":\"2017-03月\",\"y\":\"2f\"},{\"x\":\"2017-04月\",\"y\":\"77f\"},{\"x\":\"2017-05月\",\"y\":\"66f\"},{\"x\":\"2017-06月\",\"y\":\"65f\"},{\"x\":\"2017-07月\",\"y\":\"65f\"},{\"x\":\"2017-08月\",\"y\":\"99f\"},{\"x\":\"2017-09月\",\"y\":\"222f\"},{\"x\":\"2017-10月\",\"y\":\"30f\"},{\"x\":\"2017-11月\",\"y\":\"77f\"},{\"x\":\"2017-12月\",\"y\":\"9f\"}],\"" +
        "week\":[{\"x\":\"1周\",\"y\":8.9},{\"x\":\"2周\",\"y\":9.9},{\"x\":\"3周\",\"y\":1.6},{\"x\":\"4周\",\"y\":4.0},{\"x\":\"5周\",\"y\":170.0}],\"" +
        "day\":[{\"x\":\"2017-03-12\",\"y\":77.7},{\"x\":\"2017-03-13\",\"y\":4.0},{\"x\":\"2017-03-14\",\"y\":11.0},{\"x\":\"2017-03-15\",\"y\":55.7},{\"x\":\"2017-03-16\",\"y\":77.0},{\"x\":\"2017-03-17\",\"y\":13.0},{\"x\":\"2017-03-18\",\"y\":88.0}]}";

    String pieStr = "{\"mySum\":\"7.09\",\"myInvestsInterest\":\"50.57\",\"balcance\":\"21.14\",\"frozen\":\"10.2\",\"dsje\":\"12\"}";
    protected String[] mParties = new String[]{"可用金额", "待收本息", "冻结金额", "冻结金额", "冻结金额"};

    private LineChartUtil lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = new LineChartUtil(this, (LineChart) findViewById(R.id.chart_asset), lineStr);
    }

    public void showPieChart(View view) {
        //放置这初始化是有动画的,初始化跟设置数据分开就没有动画了
        PieChartUtil pieChartUtil = new PieChartUtil((PieChart) findViewById(R.id.chart2_asset), this, mParties, pieStr);
        pieChartUtil.setPieData(mParties.length);
    }

    public void goJianjie(View view) {
        startActivity(new Intent(this, jianjie.class));
    }

    public void month(View view) {
        lineChart.month();
    }

    public void week(View view) {
        lineChart.week();
    }

    public void day(View view) {
        lineChart.day();
    }
}
