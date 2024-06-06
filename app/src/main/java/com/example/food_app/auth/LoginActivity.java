package com.example.food_app.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.menu.MenuActivity;
import com.example.food_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etUserEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRecoveryPass;
    private CheckBox cbRemember;
    private TextView tvNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserEmail = findViewById(R.id.etUserEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvNewUser = findViewById(R.id.etNewUser);

        mAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = etUserEmail.getText().toString().trim();
                String passwordUser = etPassword.getText().toString().trim();

                if(emailUser.isEmpty() && passwordUser.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Debes ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUser,passwordUser);
                }
            }

            private void loginUser(String emailUser, String passwordUser) {
                mAuth.signInWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();
                                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                            }
                        }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Usuario incorrecto.", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });

        String registerUser = "¿No tienes cuenta? Regístrate aquí.";
        SpannableString spannableString = new SpannableString(registerUser);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan, registerUser.indexOf("Regístrate"), registerUser.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new android.text.style.ForegroundColorSpan(getResources().getColor(R.color.blue)), registerUser.indexOf("Regístrate"), registerUser.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvNewUser.setText(spannableString);
        tvNewUser.setMovementMethod(LinkMovementMethod.getInstance());
        tvNewUser.setLinkTextColor(getResources().getColor(R.color.blue));
        tvNewUser.setAutoLinkMask(0);


    }
}


