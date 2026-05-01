package com.example.fitnesstrackerapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class YogaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_detail);

        ImageView img = findViewById(R.id.pose_image);
        TextView title = findViewById(R.id.pose_title);
        TextView guide = findViewById(R.id.pose_guide);

        String pose = getIntent().getStringExtra("pose");
        int image = getIntent().getIntExtra("image", 0);

        img.setImageResource(image);
        title.setText(pose);

        if (pose.equals("Mountain Pose")) {
            guide.setText("Stand straight\nRelax body\nBreathe deeply");
        } else {
            guide.setText("Balance on one leg\nFocus\nStay calm");
        }
    }
}