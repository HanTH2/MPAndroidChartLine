package com.example.zero.mpchartline;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LineChart mChart;
    private RadioButton mBtnRadio1, mBtnRadio2, mBtnRadio3;
    private Button mBtnButton1, mBtnButton2, mBtnButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        //mChart.setScaleXEnabled(false);
        //mChart.setScaleYEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setBorderColor(Color.BLUE);
        mChart.setGridBackgroundColor(Color.WHITE);
        //mChart.setVisibleXRange(0, 10);

        // set an alternative background color
        mChart.setBackgroundColor(getResources().getColor(R.color.pastel));

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        // x-axis limit line
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(3f);
        llXAxis.enableDashedLine(0f, 0f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(0f, 0f, 0f);
        xAxis.setAxisMaximum(44f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(12, true);
        //xAxis.setGridColor(R.color.white);
        //xAxis.setAxisLineColor(R.color.white);
        //xAxis.setValueFormatter(new MyXAxisValueFormatter());
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaximum(62.0f);
        leftAxis.setAxisMinimum(44.0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(0f, 0f, 0f);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setLabelCount(10, true);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(44, 4);

        mChart.animateX(2500);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // // dont forget to refresh the drawing
        // mChart.invalidate();
    }

    private void initView(){
        mBtnRadio1 = (RadioButton)findViewById(R.id.btn_radio1);
        mBtnRadio2 = (RadioButton)findViewById(R.id.btn_radio2);
        mBtnRadio3 = (RadioButton)findViewById(R.id.btn_radio3);

        mBtnButton1 = (Button)findViewById(R.id.btn_button1);
        mBtnButton2 = (Button)findViewById(R.id.btn_button2);
        mBtnButton3 = (Button)findViewById(R.id.btn_button3);

        mBtnRadio1.setChecked(true);
        mBtnButton1.setOnClickListener(this);
        mBtnButton2.setOnClickListener(this);
        mBtnButton3.setOnClickListener(this);
        mBtnButton3.setSelected(true);
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 2; i < count; i = i + 4) {

            float val = (float) (Math.random() * range) + 46;
            values.add(new Entry(i, val));
        }

        ArrayList<Entry> values1 = new ArrayList<Entry>();

        for (int i = 2; i < count; i = i + 6) {

            float val = (float) (Math.random() * range) + 44;
            values1.add(new Entry(i, val));
        }

        ArrayList<Entry> values2 = new ArrayList<Entry>();

        for (int i = 2; i < count; i = i + 8) {

            float val = (float) (Math.random() * range) + 50;
            values2.add(new Entry(i, val));
        }

        ArrayList<Entry> colorValues1 = new ArrayList<Entry>();
        colorValues1.add(new Entry(4, 62));
        colorValues1.add(new Entry(8, 62));

        ArrayList<Entry> colorValues2 = new ArrayList<Entry>();
        colorValues2.add(new Entry(12, 62));
        colorValues2.add(new Entry(16, 62));

        ArrayList<Entry> colorValues3 = new ArrayList<Entry>();
        colorValues3.add(new Entry(20, 62));
        colorValues3.add(new Entry(24, 62));

        ArrayList<Entry> colorValues4 = new ArrayList<Entry>();
        colorValues4.add(new Entry(28, 62));
        colorValues4.add(new Entry(32, 62));

        ArrayList<Entry> colorValues5 = new ArrayList<Entry>();
        colorValues5.add(new Entry(36, 62));
        colorValues5.add(new Entry(40, 62));

        LineDataSet set1, set2, set3, setColor1, setColor2, setColor3, setColor4, setColor5;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            setColor1 = (LineDataSet) mChart.getData().getDataSetByIndex(3);
            setColor2 = (LineDataSet) mChart.getData().getDataSetByIndex(4);
            setColor3 = (LineDataSet) mChart.getData().getDataSetByIndex(5);
            setColor4 = (LineDataSet) mChart.getData().getDataSetByIndex(6);
            setColor5 = (LineDataSet) mChart.getData().getDataSetByIndex(7);
            set1.setValues(values);
            set2.setValues(values1);
            set3.setValues(values2);
            setColor1.setValues(colorValues1);
            setColor2.setValues(colorValues2);
            setColor3.setValues(colorValues3);
            setColor4.setValues(colorValues4);
            setColor5.setValues(colorValues5);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, null);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(0f, 0f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.BLUE);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            //set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(0.f);
//        if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_brown);
//                set1.setFillDrawable(drawable);
//            }
//            else {
//                set1.setFillColor(R.color.white);
//            }

            // create a dataset and give it a type
            set2 = new LineDataSet(values1, null);

            // set the line to be drawn like this "- - - - - -"
            set2.enableDashedLine(0f, 0f, 0f);
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(1f);
            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            //set1.setDrawFilled(true);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(0.f);

            // create a dataset and give it a type
            set3 = new LineDataSet(values2, null);

            // set the line to be drawn like this "- - - - - -"
            set3.enableDashedLine(0f, 0f, 0f);
            set3.enableDashedHighlightLine(10f, 5f, 0f);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.YELLOW);
            set3.setLineWidth(1f);
            set3.setCircleRadius(3f);
            set3.setDrawCircleHole(false);
            set3.setValueTextSize(9f);
            //set1.setDrawFilled(true);
            set3.setFormLineWidth(1f);
            set3.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set3.setFormSize(0.f);

            // create a dataset and give it a type
            setColor1 = new LineDataSet(colorValues1, null);
            setColor2 = new LineDataSet(colorValues2, null);
            setColor3 = new LineDataSet(colorValues3, null);
            setColor4 = new LineDataSet(colorValues4, null);
            setColor5 = new LineDataSet(colorValues5, null);

            setColor1.setDrawFilled(true);
            setColor1.setFillColor(R.color.brown40);
            setColor1.setDrawCircles(false);
            setColor1.setDrawValues(false);
            setColor1.setFormSize(0.f);

            setColor2.setDrawFilled(true);
            setColor2.setFillColor(R.color.brown40);
            setColor2.setDrawCircles(false);
            setColor2.setDrawValues(false);
            setColor2.setFormSize(0.f);

            setColor3.setDrawFilled(true);
            setColor3.setFillColor(R.color.brown40);
            setColor3.setDrawCircles(false);
            setColor3.setDrawValues(false);
            setColor3.setFormSize(0.f);

            setColor4.setDrawFilled(true);
            setColor4.setFillColor(R.color.brown40);
            setColor4.setDrawCircles(false);
            setColor4.setDrawValues(false);
            setColor4.setFormSize(0.f);

            setColor5.setDrawFilled(true);
            setColor5.setFillColor(R.color.brown40);
            setColor5.setDrawCircles(false);
            setColor5.setDrawValues(false);
            setColor5.setFormSize(0.f);

            // set the line to be drawn like this "- - - - - -";
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
            dataSets.add(set3);
            dataSets.add(setColor1);
            dataSets.add(setColor2);
            dataSets.add(setColor3);
            dataSets.add(setColor4);
            dataSets.add(setColor5);

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_button1:
                mBtnButton1.setSelected(true);
                mBtnButton2.setSelected(false);
                mBtnButton3.setSelected(false);
                break;
            case R.id.btn_button2:
                mBtnButton1.setSelected(false);
                mBtnButton2.setSelected(true);
                mBtnButton3.setSelected(false);
                break;
            case R.id.btn_button3:
                mBtnButton1.setSelected(false);
                mBtnButton2.setSelected(false);
                mBtnButton3.setSelected(true);
                break;
            default:
                break;
        }
    }
}
