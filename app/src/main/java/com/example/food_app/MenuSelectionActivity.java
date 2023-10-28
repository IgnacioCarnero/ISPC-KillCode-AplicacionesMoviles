package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuSelectionActivity extends AppCompatActivity {

    Button btn_menuselection;
    ImageButton btn_waitermenuselection;
    Button btn_paymentmethod;
    Button btn_estimatedtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);

        btn_menuselection= findViewById(R.id.btn_menuselection);
        btn_waitermenuselection= findViewById(R.id.btn_waitermenuselection);
        btn_paymentmethod= findViewById(R.id.btn_paymentmethod);
        btn_estimatedtime= findViewById(R.id.btn_estimatedtime);
        btn_menuselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuSelectionActivity.this, MenuScroll.class);
                startActivity(intent);
            }
        });
        btn_waitermenuselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuSelectionActivity.this, waiterActivity.class);
                startActivity(intent);
            }
        });
        btn_paymentmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuSelectionActivity.this, PaymentMethodActivity.class);
                //acá va la PaymentMethodActivity en vez de MainActivity
                startActivity(intent);
            }
        });
        btn_estimatedtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuSelectionActivity.this, EstimatedtimeActivity.class);
                startActivity(intent);
            }
        });
    }
}