package com.javad.quizapplang;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.javad.quizapplang.model.dbFlow.TimeChart;
import com.javad.quizapplang.model.dbFlow.TimeChart_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class SpendingTime extends AppCompatActivity
{
    LineChart chart;
    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

    private void setData(List<TimeChart> timeCharts) {

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();


        // increment by 1 hour
        for (int i = 0; i < timeCharts.size(); i++) {

            Log.e("count = ", timeCharts.get(i).getTime() + "");
            Log.e("day = ", timeCharts.get(i).getDay_week() + "");
            values.add(new Entry(i, (float) timeCharts.get(i).getTime() / (60))); // add one entry per hour
            list.add(timeCharts.get(i).getDay_week());
        }

        setChart(chart, values, list, "مقدار زمان صرف شده شما در برنامه(بر حسب دقیقه)");
    }

    private void setChart(LineChart lineChart, final ArrayList<Entry> money, final ArrayList<String> name, String nameLabel) {
        LineDataSet dataSet = new LineDataSet(money, nameLabel);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String s = "";
                if (name.size() == money.size()) {
                    s = name.get((int) value);
                }

                return s;
            }
        });
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_time);
        chart = findViewById(R.id.chart);
        List<TimeChart> timeCharts0 = SQLite.select().from(TimeChart.class).queryList();

        int a;
        try {

            a = timeCharts0.get(timeCharts0.size() - 1).getMonth();
            Log.e("month", a + "");

            List<TimeChart> timeCharts = SQLite.select().from(TimeChart.class).
                    where(TimeChart_Table.month.eq(a))
                    .queryList();


            setData(timeCharts);

            circles();
//        cube();
//        stoped();
            fillToggle();
            heightToggle();
//        toggleValues();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toggleValues() {

        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setDrawValues(!set.isDrawValuesEnabled());
        }

        chart.invalidate();

    }

    private void heightToggle() {
//        if (chart.getData() != null) {
        chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
        chart.invalidate();
//        }

    }

    private void fillToggle() {

        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
//            if (set.isDrawFilledEnabled())
//                set.setDrawFilled(false);
//            else
            set.setDrawFilled(true);
        }
        chart.invalidate();

    }

    private void circles() {

        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
//            if (set.isDrawCirclesEnabled())
//                set.setDrawCircles(false);
//            else
            set.setDrawCircles(true);
            set.setCircleColor(Color.GREEN);
            set.setCircleHoleRadius(7f);
            set.setDrawCircleHole(true);
        }
        chart.invalidate();
    }

    private void stoped() {

        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            if (set.getMode() == LineDataSet.Mode.STEPPED)
                set.setMode(LineDataSet.Mode.LINEAR);
            else
                set.setMode(LineDataSet.Mode.STEPPED);
        }
    }

    private void cube() {
        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            if (set.getMode() == LineDataSet.Mode.CUBIC_BEZIER)
                set.setMode(LineDataSet.Mode.LINEAR);
            else
                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        }
        chart.invalidate();
    }
}
