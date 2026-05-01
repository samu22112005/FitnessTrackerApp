package com.example.fitnesstrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class YogaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        ImageView yoga1 = findViewById(R.id.yoga1_img);
        ImageView yoga2 = findViewById(R.id.yoga2_img);

        yoga1.setOnClickListener(v -> {
            animateClick(v);
            Intent i = new Intent(this, YogaDetailActivity.class);
            i.putExtra("pose", "Mountain Pose");
            i.putExtra("image", R.drawable.yoga1);
            startActivity(i);
        });

        yoga2.setOnClickListener(v -> {
            animateClick(v);
            Intent i = new Intent(this, YogaDetailActivity.class);
            i.putExtra("pose", "Tree Pose");
            i.putExtra("image", R.drawable.yoga2);
            startActivity(i);
        });
    }

    private void animateClick(View v) {
        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
                .withEndAction(() ->
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100));
    }
}