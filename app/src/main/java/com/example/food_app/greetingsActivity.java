package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class greetingsActivity extends AppCompatActivity {
    ImageButton bnt_back_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);

        bnt_back_main=findViewById(R.id.bnt_back_main);
        bnt_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(greetingsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}