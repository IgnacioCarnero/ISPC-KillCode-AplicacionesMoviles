package com.example.food_app.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.food_app.R;
import com.example.food_app.adapter.OrderConfirmationAdapter;
import com.example.food_app.database.entity.comidaBebida;

import java.util.ArrayList;

public class OrderConfirmationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderConfirmationAdapter adapter;


    private static final String TAG = "OrderConfirmationActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_confirm_order);

        recyclerView = findViewById(R.id.to_confirm_order);

        // Recibe la lista de productos desde el intent
        ArrayList<comidaBebida> productos = getIntent().getParcelableArrayListExtra("productos");
        if (productos == null) {
            productos = new ArrayList<>();
        }

        Log.d(TAG, "onCreate: received products list with size " + productos.size());


        adapter = new OrderConfirmationAdapter(productos, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
