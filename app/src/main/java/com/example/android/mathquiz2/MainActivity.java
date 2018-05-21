package com.example.android.mathquiz2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean easy = false;
    int num1, num2, correctAns = 0, ans[] = new int[11];
    Random ran = new Random();
    Random ran2 = new Random();
    TextView tvResult;
    RadioButton rbEasy;
    ViewGroup.LayoutParams paramsRg;
    LinearLayout.LayoutParams paramsRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.result);
        rbEasy = findViewById(R.id.easy);

        //reference to linear layout, where everything is to be added dynamically
        final LinearLayout lm = findViewById(R.id.linearMain);
        //layout parameters for Radio buttons
        paramsRb = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsRb.weight = 1;
        //layout parameters for Radio group
        paramsRg = findViewById(R.id.linearFirst).getLayoutParams();
        //dynamically adding TextView (1 no), RadioGroup (1 no), RadioButton (4 nos)
        for (int i = 1; i <= 10; i++) {
            lm.addView(addTv(i * 10));
            lm.addView(addRg(i * 10 + 5));
            addRb(i * 10 + 1, i * 10 + 5);
            addRb(i * 10 + 2, i * 10 + 5);
            addRb(i * 10 + 3, i * 10 + 5);
            addRb(i * 10 + 4, i * 10 + 5);
        }
        resetAll();
    }

    public void reset(View view) {
        resetAll();
    }

    public void submitAnswers(View view) {
        RadioButton rb;

        for (int i = 1; i <= 10; i++) {
            rb = findViewById(i * 10 + ans[i]);
            rb.setTextColor(Color.RED);
            if (rb.isChecked()) correctAns++;
        }

        tvResult.setText("Scored " + correctAns + " out of 10!");
    }

    public void resetAll() {

        int x, y, z, j, p, q;
        String s;
        TextView tv;
        RadioButton rb;
        RadioGroup rg;

        easy = rbEasy.isChecked();
        if (easy) s = "EASY";
        else s = "HARD";

        tvResult.setText(s + " MODE !");

        for (int i = 1; i <= 10; i++) {
            tv = findViewById(i * 10);
            x = nxtNum();
            y = nxtNum();
            z = x + y;
            p = nxtAns();
            tv.setText("Q" + i + ".  " + x + " + " + y + " = ?");
            for (j = 1; j <= 4; j++) {
                rb = findViewById(i * 10 + j);
                rb.setTextColor(Color.DKGRAY);
                rb.setChecked(false);
                q = z + (p + j) % 4 - 2;
                rb.setText("" + q);
                if (q == z) ans[i] = j;
            }
            rg = findViewById(i * 10 + 5);
            rg.clearCheck();
        }
        correctAns = 0;
    }

    public TextView addTv(int id) {
        // Create TextView
        TextView temp = new TextView(this);
        temp.setId(id);
        temp.setTextSize(16);
        temp.setTextColor(Color.BLACK);
        temp.setText("Q" + id / 10);
        return temp;
    }

    public RadioGroup addRg(int id) {
        // Create Radio Group
        RadioGroup temp = new RadioGroup(this);
        temp.setId(id);
        temp.setLayoutParams(paramsRg);
        temp.setOrientation(LinearLayout.HORIZONTAL);
        return temp;
    }

    public void addRb(int id, int radioGroupId) {
        // Create TextView
        RadioButton temp = new RadioButton(this);
        temp.setId(id);
        temp.setLayoutParams(paramsRb);
        temp.setTextSize(16);
        temp.setTextColor(Color.BLACK);
        temp.setText("A" + id);
        RadioGroup radioGroup = findViewById(radioGroupId);
        radioGroup.addView(temp);
    }

    public int nxtNum() {
        return ran.nextInt(9) + 1;
    }

    public int nxtAns() {
        return ran2.nextInt(4);
    }
}
