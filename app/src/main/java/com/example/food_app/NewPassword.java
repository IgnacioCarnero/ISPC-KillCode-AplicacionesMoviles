package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class NewPassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // Obtener referencia al botón de restablecimiento de contraseña
        Button resetPasswordButton = findViewById(R.id.buttonResetPassword);

        // Configurar un OnClickListener para el botón
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se hace clic en el botón, crear un Intent para ir a LoginActivity
                Intent intent = new Intent(NewPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
