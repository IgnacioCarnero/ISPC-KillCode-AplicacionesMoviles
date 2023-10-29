package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OrderConfirmationActivity extends AppCompatActivity {

    Button btn_demora;
    ImageButton btn_backmenuselection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        btn_demora= findViewById(R.id.btn_demora);
        btn_backmenuselection=findViewById(R.id.btn_backmenuselection);



        btn_demora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmationActivity.this, EstimatedtimeActivity.class);
                startActivity(intent);
            }
        });
        btn_backmenuselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderConfirmationActivity.this, MenuSelectionActivity.class);
                startActivity(intent);
            }
        });


    }


}