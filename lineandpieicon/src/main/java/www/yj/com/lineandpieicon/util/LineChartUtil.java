package www.yj.com.lineandpieicon.util;

import android.content.Context;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import www.yj.com.lineandpieicon.R;
import www.yj.com.lineandpieicon.bean.LineBean;

/**
 * 创建者：admin
 * <p>时间：2017/3/13 11:03
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class LineChartUtil implements OnChartValueSelectedListener {

    private Context context;
    private com.github.mikephil.charting.charts.LineChart mChart;
    private int whiteColor;
    private String str;

    public LineChartUtil(Context context, com.github.mikephil.charting.charts.LineChart mChart, String str) {
        this.context = context;
        this.mChart = mChart;
        this.str = str;
        initChart();
    }



    private void initChart() {

        whiteColor = context.getResources().getColor(R.color.white);
        int whitre = context.getResources().getColor(R.color.whitre);

        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.setDescription("");

        mChart.setNoDataText("暂无数据");

        // enable value highlighting
        mChart.setHighlightEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(false);

        // enable scaling and dragging
        mChart.setDragEnabled(false);

        mChart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // set an alternative background color
        mChart.setBackgroundColor(context.getResources().getColor(R.color.red));

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        // MyMarkerView mv = new MyMarkerView(this,
        // R.layout.custom_marker_view);

        // set the marker to the chart
        // mChart.setMarkerView(mv);

        // enable/disable highlight indicators (the lines that indicate the
        // highlighted Entry)
        mChart.setHighlightIndicatorEnabled(false);

        XAxis xl = mChart.getXAxis();

        xl.setAvoidFirstLastClipping(false);

        xl.setTextColor(context.getResources().getColor(R.color.white));

        xl.setGridColor(whitre);

        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setInverted(false);

        leftAxis.setGridColor(whitre);

        leftAxis.setTextColor(whiteColor);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        // add data

        // // restrain the maximum scale-out factor
        // mChart.setScaleMinima(3f, 3f);
        //
        // // center the view to a specific position inside the chart
        // mChart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setEnabled(false);

        // l.setForm(LegendForm.CIRCLE);// 样式
        // l.setFormSize(1f);// 字体
        // l.setTextColor(Color.WHITE);// 颜色
        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        // l.setForm(LegendForm.SQUARE);
        // l.setTextColor(whiteColor);

        // setData(13, 100);
        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    public void month() {
        LineBean json2Bean = json2Bean(str, LineBean.class);
        List<LineBean.Month> month = json2Bean.month;
        setData(month.size() + 1,json2Bean, "month");
    }

    public void week() {
        LineBean json2Bean = json2Bean(str, LineBean.class);
        List<LineBean.Week> week = json2Bean.week;
        setData(week.size() + 1, json2Bean, "week");
    }

    public void day() {
        LineBean json2Bean = json2Bean(str, LineBean.class);
        List<LineBean.Day> day = json2Bean.day;
        setData(day.size() + 1, json2Bean, "day");
    }

    private void setData(int count, LineBean bean, String string) {
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add(0 + "");
        switch (string){
            case "month":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Month> month = bean.month;
                    String x = month.get(i).x;

                    String substring = x.substring(x.length() - 3, x.length());
                    xVals.add(substring);
                }
                break;
            case "week":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Week> week = bean.week;
                    xVals.add(week.get(i).x);
                }
                break;
            case "day":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Day> day = bean.day;
                    xVals.add(day.get(i).x);
                }
                break;
        }
        ArrayList<Entry> yVals = new ArrayList<>();

        // for (int i = 0; i < count; i++) {
        // float mult = (range + 1);
        // float val = (float) (Math.random() * mult) + 3;// + (float)
        // // ((mult *
        // // 0.1) / 10);
        // yVals.add(new Entry(val, i));
        // }

        yVals.add(new Entry(0, 0));

        switch (string) {
            case "month":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Month> month = bean.month;

                    float y = month.get(i).y;

                    yVals.add(new Entry(y, i + 1));
                }
                break;
            case "week":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Week> year = bean.week;

                    String y = year.get(i).y;
                    float frozenF = Float.parseFloat(y);

                    yVals.add(new Entry(frozenF, i + 1));
                }
                break;
            case "day":
                for (int i = 0; i < count - 1; i++) {
                    List<LineBean.Day> day = bean.day;

                    String y = day.get(i).y;
                    float frozenF = Float.parseFloat(y);

                    yVals.add(new Entry(frozenF, i + 1));
                }

                break;
        }
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setLineWidth(1.5f);
        set1.setCircleSize(4f);

        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);

        // set data
        mChart.setData(data);

        List<LineDataSet> dataSets = mChart.getData().getDataSets();

        for (LineDataSet set : dataSets) {

            set.setCircleColor(context.getResources().getColor(R.color.white));

            set.setCircleSize(3);

            // set.setDrawCubic(true);

            // 是否在原点内设置洞
            set.setDrawCircleHole(false);

            set.setValueTextColor(context.getResources().getColor(R.color.white));

            set.setDrawValues(true);

            set.setColor(whiteColor);

            set.setLineWidth(1);

        }
        mChart.animateXY(2000, 1000);

        mChart.invalidate();
    }

    private static <T> T json2Bean(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
    private static <T> String bean2Json(T t) {
        return new Gson().toJson(t);
    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }
}
