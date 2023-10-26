package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PaymentMethodActivity extends AppCompatActivity {
    ImageButton btn_waiter_paymentmethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        btn_waiter_paymentmethod = findViewById(R.id.btn_waiter_paymentmethod);
        btn_waiter_paymentmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentMethodActivity.this,waiterActivity.class);
                startActivity(intent);
            }
        });
    }
}