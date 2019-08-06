package com.example.marta.mg_makeup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.brushes)
    ImageView brushes;

    @BindView(R.id.eyeshadow)
    ImageView eyeshadow;

    @BindView(R.id.single)
    ImageView single;

    @BindView(R.id.nail)
    ImageView nail;

    @BindView(R.id.makeup_textview)
    TextView makeup_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finishscreen();
            }
        };

        timerRun(task1, 5000);
    }

    private void timerRun(TimerTask task, int delayed ) {
        Timer t = new Timer();
        t.schedule(task, delayed);
    }

    private void finishscreen() {
        this.finish();
    }
}