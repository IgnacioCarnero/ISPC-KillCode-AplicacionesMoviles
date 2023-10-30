package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class passwordResetActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendResetEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        // Enlazar las vistas
        emailEditText = findViewById(R.id.editTextEmail);
        sendResetEmailButton = findViewById(R.id.buttonReset);

        // Configurar un OnClickListener para el botón de enviar correo de restablecimiento
        sendResetEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el correo electrónico ingresado por el usuario
                String userEmail = emailEditText.getText().toString();

                // Aquí debes agregar la lógica para enviar un correo de restablecimiento a "userEmail".
                // Asegúrate de validar el correo electrónico antes de enviarlo.
                if (isValidEmail(userEmail)) {
                    // Lógica para enviar el correo
                    sendResetEmail(userEmail);
                } else {
                    // Mostrar un mensaje de error al usuario
                    emailEditText.setError("Correo electrónico no válido");
                }
            }
        });
    }

    // Función para validar un correo electrónico
    private boolean isValidEmail(String email) {
        // Puedes agregar tu lógica de validación aquí
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Función para enviar un email de restablecimiento de la contraseña
    private void sendResetEmail(String email) {
        // Aquí debes agregar la lógica para enviar el correo de restablecimiento.
        // Esto dependerá de tu sistema de envío de correos electrónicos.
    }
}
