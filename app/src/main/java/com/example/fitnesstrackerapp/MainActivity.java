package com.example.fitnesstrackerapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private TextView stepCountText, caloriesText;
    private int stepCount = 0, previousTotalSteps = 0;
    private boolean isSensorPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepCountText = findViewById(R.id.step_count);
        caloriesText = findViewById(R.id.calories_text);
        Button resetButton = findViewById(R.id.reset_button);

        resetButton.setOnClickListener(v -> {
            previousTotalSteps = stepCount;
            stepCountText.setText("0");
            caloriesText.setText("0");
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        }

        BottomNavigationView nav = findViewById(R.id.bottom_nav);

        nav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) return true;

            if (item.getItemId() == R.id.nav_yoga) {
                startActivity(new Intent(this, YogaActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.nav_breath) {
                startActivity(new Intent(this, BreathingActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }

            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorPresent) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isSensorPresent) sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stepCount = (int) event.values[0];
        int currentSteps = stepCount - previousTotalSteps;
        double calories = currentSteps * 0.04;

        stepCountText.setText(String.valueOf(currentSteps));
        caloriesText.setText(String.format("%.2f", calories));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}