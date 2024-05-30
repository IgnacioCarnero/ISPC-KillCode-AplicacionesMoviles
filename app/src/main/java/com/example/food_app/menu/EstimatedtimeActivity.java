package com.example.food_app.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.food_app.R;

public class EstimatedtimeActivity extends AppCompatActivity {
    ImageButton btn_backawaiter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimatedtime);

        btn_backawaiter2= findViewById(R.id.btn_backawaiter2);

        btn_backawaiter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EstimatedtimeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}