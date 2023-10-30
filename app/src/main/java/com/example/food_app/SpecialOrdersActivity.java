package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SpecialOrdersActivity extends AppCompatActivity {
    Button btn_orderwithchanges;
    ImageButton btn_back_toconfirmorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_orders);

        btn_orderwithchanges= findViewById(R.id.btn_orderwithchanges);
        btn_back_toconfirmorder= findViewById(R.id.btn_back_toconfirmorder);
        btn_orderwithchanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SpecialOrdersActivity.this, OrderConfirmationActivity.class);
                startActivity(intent);
            }
        });
        btn_back_toconfirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SpecialOrdersActivity.this, ToConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}