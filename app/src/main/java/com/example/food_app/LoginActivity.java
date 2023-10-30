package com.example.food_app;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.content.Intent;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.util.Log;



public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    Button registerButton;

    CheckBox forgotPasswordCheckBox;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgotPasswordCheckBox = findViewById(R.id.forgotPasswordCheckBox);

        forgotPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loginButton.setEnabled(!isChecked); // Habilita o deshabilita el botón de inicio de sesión
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(LoginActivity.this, "Login exitoso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Usuario incorrecto o no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void goToRegister(View view) {
        // Agrega una impresión de registro para verificar si se llama a esta función
        Log.d("LoginActivity", "Botón Register presionado");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}

