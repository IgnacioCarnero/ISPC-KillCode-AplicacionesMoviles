package com.example.food_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Asegúrate de que el archivo layout correspondiente se llame "activity_register.xml"

        // Vincula los elementos de la interfaz de usuario con las variables Java
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);

        // Configura un OnClickListener para el botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén los valores de los campos
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Realiza la validación y lógica de registro aquí
                if (isValidInput(username, email, password, confirmPassword)) {
                    // Puedes implementar la lógica de registro aquí, por ejemplo, enviar los datos a un servidor.
                    // Para mostrar un mensaje de éxito, puedes usar Toast o cualquier otro método.
                }
            }
        });
    }

    // Agrega una función para validar la entrada del usuario
    private boolean isValidInput(String username, String email, String password, String confirmPassword) {
        // Aquí puedes implementar tus propias reglas de validación.
        // Por ejemplo, asegurarte de que las contraseñas coincidan.
        return password.equals(confirmPassword);
    }
}