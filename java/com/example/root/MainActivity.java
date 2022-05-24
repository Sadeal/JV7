package com.example.root;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText aNum, bNum, cNum;
    private Button button, button1;
    private TextView resultDef, resultViet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aNum = findViewById(R.id.aNum);
        bNum = findViewById(R.id.bNum);
        cNum = findViewById(R.id.cNum);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        resultDef = findViewById(R.id.resultDef);
        resultViet = findViewById(R.id.resultViet);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aNum.getText().toString().trim().equals("") || bNum.getText().toString().trim().equals("") || cNum.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                }
                else{
                    String a = aNum.getText().toString().trim();
                    String b = bNum.getText().toString().trim();
                    String c = cNum.getText().toString().trim();
                    new GetDefRoots().execute(a,b,c);
                    new GetVietRoots().execute(a,b,c);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aNum.getText().toString().trim().equals("") || bNum.getText().toString().trim().equals("") || cNum.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Graphic.class);
                    String a = aNum.getText().toString().trim();
                    String b = bNum.getText().toString().trim();
                    String c = cNum.getText().toString().trim();
                    intent.putExtra(Graphic.a, a + "," + b + "," + c);
                    startActivity(intent);
                }
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private class GetDefRoots extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            resultDef.setText("Расчёт...");
        }

        @Override
        protected String doInBackground(String... strings){
            try {
                String[] z = strings[0].split(",");
                double a = Double.parseDouble(strings[0]);
                double b = Double.parseDouble(strings[1]);
                double c = Double.parseDouble(strings[2]);
                double D = b * b - 4 * a * c;
                String answer;
                if (D > 0) {
                    double x1;
                    double x2;
                    x1 = (-b - Math.sqrt(D)) / (2 * a);
                    x2 = (-b + Math.sqrt(D)) / (2 * a);
                    answer = "x1 = " + x1 + ", x2 = " + x2;
                } else if (D == 0) {
                    double x;
                    x = -b / (2 * a);
                    answer = String.valueOf(x);
                } else {
                    answer = "Нет решения";
                }
                return answer;
            }
            catch(Exception e){
                return "Введите числа";
            }
        }

        @Override
        protected void onPostExecute(String answer){
            super.onPostExecute(answer);
            resultDef.setText(answer);
        }

    }

    private class GetVietRoots extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            resultDef.setText("Расчёт...");
        }

        @Override
        protected String doInBackground(String... strings){
            String ans = "Ответа не нашлось";
            try {
                for (int i = -1000; i <= 1000; i++)
                    for (int j = -1000; j <= 1000; j++) {
                        if (i + j == -Integer.parseInt(strings[1]) && i * j == Integer.parseInt(strings[2]))
                            ans = "x1 = " + i + ", x2 = " + j;
                    }
                return ans;
            }
            catch(Exception e){
                ans = "Введите числа";
                return ans;
            }
        }

        @Override
        protected void onPostExecute(String ans){
            super.onPostExecute(ans);
            resultViet.setText(ans);
        }

    }
}