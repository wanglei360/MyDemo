package www.yj.com.lineandpieicon.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.animation.AnimationEasing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.google.gson.Gson;

import java.util.ArrayList;

import www.yj.com.lineandpieicon.bean.PieBean;


/**
 * 创建者：admin
 * <p>时间：2017/3/13 14:22
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class PieChartUtil implements OnChartValueSelectedListener {
    private PieChart pieChart;
    private Typeface typeFace;
    private Context context;
    private String[] mParties;
    private final PieBean pieBean;

    public PieChartUtil(PieChart pieChart, Context context, String[] mParties, String pieStr) {
        this.pieChart = pieChart;
        this.context = context;
        this.mParties = mParties;
        pieBean = jsonToBean(pieStr, PieBean.class);
        initPie();
    }

    private void initPie() {
        pieChart.setUsePercentValues(true);
        pieChart.setHoleColorTransparent(true);

        typeFace = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        pieChart.setCenterTextTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));

        pieChart.setHoleRadius(130f);// 半径

        pieChart.setDescription("饼状图下的文字");// 饼状图下的文字 此处没有

        pieChart.setDrawCenterText(true);// 饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setHoleRadius(0);//中间可以加一个小圆,这个参数是半径

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(false);

        pieChart.setTransparentCircleRadius(0f);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        //TODO 是否显示mParties中的字
        pieChart.setDrawSliceText(true);

        // add a selection listener
        pieChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

        // pieChart.setCenterText("MPAndroidChart\nby Philipp Jahoda");

        pieChart.animateXY(1500, 1500, AnimationEasing.EasingOption.EaseOutBack);
        // mChart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();

        l.setEnabled(false);
//            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//            l.setXEntrySpace(7f);
//            l.setYEntrySpace(5f);
    }


    public void setPieData(int count) {

        ArrayList<Entry> yVals1 = new ArrayList<>();

        String balcance = pieBean.getBalcance();
        float balcanceF = Float.parseFloat(balcance);

        String mySum = pieBean.getMySum();
        float mySumF = Float.parseFloat(mySum);

        String dsje = pieBean.getDsje();
        float dsjeF = Float.parseFloat(dsje);

        String frozen = pieBean.getFrozen();
        float frozenF = Float.parseFloat(frozen);

        String myInvestsInterest = pieBean.getMyInvestsInterest();
        float myInvestsInterestF = Float.parseFloat(myInvestsInterest);

        yVals1.add(new Entry(balcanceF, 0));
        yVals1.add(new Entry(dsjeF, 1));
        yVals1.add(new Entry(frozenF, 2));
        yVals1.add(new Entry(myInvestsInterestF, 3));
        yVals1.add(new Entry(mySumF, 4));

        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < count; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(0.0f);//饼状图块与块之间的间隔是多大

//---------------------------------饼图底色的三种设置方法--------------------------------------------
        ArrayList<Integer> colors = new ArrayList<>();
//        for (int c : ColorTemplate.PASTEL_COLORS) // todo
//            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());// todo

//         160 212 104 3A0D468 green   // todo
//        colors.add(Color.rgb(160, 212, 104));
//         255 206 84 #EFCE54 yellow
//        colors.add(Color.rgb(255, 206, 84));
//         252 110 81 #DC6E51 red
//        colors.add(Color.rgb(252, 110, 81));
        dataSet.setColors(colors);
//---------------------------------饼图底色的三种设置方法--------------------------------------------

        PieData data = new PieData(xVals, dataSet);
//        data.setValueFormatter(new PercentFormatter());
        data.setValueFormatter(new ValueFormatter() {
            //TODO 是否显示数字
            @Override
            public String getFormattedValue(float arg0) {
//                return String.valueOf(arg0);//显示数字,显示的数字都是float保留小数后面7位,的,太长了
                return "";//不显示数字
            }
        });
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(typeFace);
        pieChart.setData(data);
        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {}

    @Override
    public void onNothingSelected() {}

    private static <T> T jsonToBean(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
}
