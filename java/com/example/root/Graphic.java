package com.example.root;

import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Graphic extends AppCompatActivity {

    public static final String a = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        //получение данных a, b и c
        Bundle extras = getIntent().getExtras();
        String str = extras.getString(a);
        String[] z = str.split(",");
        try {
            int a = Integer.parseInt(z[0]);
            int b = Integer.parseInt(z[1]);
            int c = Integer.parseInt(z[2]);

            GraphView graph = (GraphView) findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            int y;
            for (int i = -20; i <= 20; i++) {
                y = a * (i * i) + b * i + c;
                series.appendData(new DataPoint(i, y), true, 80);
            }

            graph.addSeries(series);
            graph.setCursorMode(true);
            graph.setTitle("Теряю корни");
        }catch(Exception e){
            Toast.makeText(Graphic.this, R.string.not_a_number, Toast.LENGTH_LONG).show();
        }

    }
}