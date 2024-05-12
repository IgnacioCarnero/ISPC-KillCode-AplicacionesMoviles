package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private Button btnRegister;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        tvSignUp = findViewById(R.id.tvSignUp);


        String userExist = "Ya tengo cuenta. Iniciar sesión";
        SpannableString spannableString = new SpannableString(userExist);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan, userExist.indexOf("Iniciar sesión"), userExist.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new android.text.style.ForegroundColorSpan(getResources().getColor(R.color.blue)), userExist.indexOf("Iniciar sesión"), userExist.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignUp.setText(spannableString);
        tvSignUp.setMovementMethod(LinkMovementMethod.getInstance());
    }
}