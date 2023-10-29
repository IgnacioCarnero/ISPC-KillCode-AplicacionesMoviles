package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommentActivity extends AppCompatActivity {
    Button btn_sendcommnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        btn_sendcommnet= findViewById(R.id.btn_sendcommnet);
        btn_sendcommnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CommentActivity.this, greetingsActivity.class);
                startActivity(intent);
            }
        });
    }
}