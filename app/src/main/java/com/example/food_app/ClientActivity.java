package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.OnBackPressedCallback;

import com.example.food_app.menu.MenuScroll;

public class ClientActivity extends AppCompatActivity {

    ImageButton btn_waiter;
    Button btn_menu;
    OnBackPressedCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        btn_waiter= findViewById(R.id.btn_waiter);
        btn_menu= findViewById(R.id.btn_menu);

        btn_waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ClientActivity.this, waiterActivity.class);
                startActivity(intent);
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ClientActivity.this, MenuScroll.class);
                startActivity(intent);
            }
        });

        // Configuración del OnBackPressedCallback
        callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Aquí personalizamos el comportamiento del botón "atrás"
                // Verificamos si estamos en ClientActivity
                // Si estamos en ClientActivity, no hacemos nada
                // Si no estamos en ClientActivity, permitimos el comportamiento predeterminado
                if (!estasEnClientActivity()) {
                    ClientActivity.super.onBackPressed();
                }
            }
        };

        // Asociamos el callback al dispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    private boolean estasEnClientActivity() {
        // Verifica si la actividad actual es ClientActivity
        return getClass().getSimpleName().equals(ClientActivity.class.getSimpleName());
    }
}