package com.android.eatproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class salade_activity extends AppCompatActivity implements View.OnClickListener {
    private Button btncommander;
    private Button btncommander2;
    private Button btncommander3;
    private Button btncommander4;
    private Button btncommander5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salade);
        btncommander = findViewById(R.id.button_);
        btncommander2 = findViewById(R.id.button2_);
        btncommander3 = findViewById(R.id.button3_);
        btncommander4 = findViewById(R.id.button4_);
        btncommander5 = findViewById(R.id.button5_);


        btncommander.setOnClickListener(this);
        btncommander2.setOnClickListener(this);
        btncommander3.setOnClickListener(this);
        btncommander4.setOnClickListener(this);
        btncommander5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_:
                Intent otherActivity = new Intent(getApplicationContext(), com.android.eatproject.please_wait.class);
                startActivity(otherActivity);
                break;
            case R.id.button2_:
                Intent otherActivity1 = new Intent(getApplicationContext(), com.android.eatproject.please_wait.class);
                startActivity(otherActivity1);
                break;
            case R.id.button3_:
                Intent otherActivity2 = new Intent(getApplicationContext(), com.android.eatproject.please_wait.class);
                startActivity(otherActivity2);
                break;
            case R.id.button4_:
                Intent otherActivity3 = new Intent(getApplicationContext(), com.android.eatproject.please_wait.class);
                startActivity(otherActivity3);
                break;
            case R.id.button5_:
                Intent otherActivity4 = new Intent(getApplicationContext(), com.android.eatproject.please_wait.class);
                startActivity(otherActivity4);
                break;
        }

    }
}