package com.example.fitnesstrackerapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreathingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        View circle = findViewById(R.id.breath_circle);
        TextView text = findViewById(R.id.breath_text);

        Handler handler = new Handler();

        Runnable breathe = new Runnable() {
            boolean inhale = true;

            @Override
            public void run() {
                if (inhale) {
                    text.setText("Inhale 🌬️");
                    circle.animate().scaleX(1.6f).scaleY(1.6f).setDuration(4000);
                } else {
                    text.setText("Exhale 😌");
                    circle.animate().scaleX(1f).scaleY(1f).setDuration(4000);
                }
                inhale = !inhale;
                handler.postDelayed(this, 4000);
            }
        };

        handler.post(breathe);
    }
}