package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class waiterActivity extends AppCompatActivity {
ImageButton btn_waiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);


        btn_waiter= findViewById(R.id.btn_backwaiter);
        btn_waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(waiterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}