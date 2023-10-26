package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class ToConfirmOrderActivity extends AppCompatActivity {
    ImageButton btn_back_menu_scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_confirm_order);

        btn_back_menu_scroll= findViewById(R.id.btn_back_menu_scroll);
        btn_back_menu_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ToConfirmOrderActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}

