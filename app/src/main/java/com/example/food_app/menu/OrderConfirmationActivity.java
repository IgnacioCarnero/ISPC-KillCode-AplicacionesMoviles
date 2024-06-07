package com.example.food_app.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.food_app.R;

public class OrderConfirmationActivity extends AppCompatActivity {


    ImageButton btn_backmenuselection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        btn_backmenuselection=findViewById(R.id.btn_backmenuselection);




        btn_backmenuselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderConfirmationActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }


}