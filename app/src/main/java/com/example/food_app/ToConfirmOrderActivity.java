package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class ToConfirmOrderActivity extends AppCompatActivity {
    ImageButton btn_back_menu_scroll;
    Button btn_orderconfirm;

    Button btn_specialOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_confirm_order);

        btn_back_menu_scroll= findViewById(R.id.btn_back_menu_scroll);
        btn_orderconfirm= findViewById(R.id.btn_orderconfirm);
        btn_specialOrder= findViewById(R.id.btn_specialOrder);

        btn_back_menu_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ToConfirmOrderActivity.this, MenuScroll.class);
                startActivity(intent);
            }
        });
        btn_orderconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(ToConfirmOrderActivity.this, OrderConfirmationActivity.class);
                startActivity(intent2);
            }
        });
        btn_specialOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ToConfirmOrderActivity.this, SpecialOrdersActivity.class);
                startActivity(intent);
            }
        });

    }
}

