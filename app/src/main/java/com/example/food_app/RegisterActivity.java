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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUserEmailRegister;
    private EditText etPasswordRegister;
    private Button btnRegister;
    private TextView tvSignUp;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        etUserEmailRegister = findViewById(R.id.etUserEmailRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        tvSignUp = findViewById(R.id.tvSignUp);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmialRegister = etUserEmailRegister.getText().toString().trim();
                String userPasswordRegister = etPasswordRegister.getText().toString().trim();

                if(userEmialRegister.isEmpty() && userPasswordRegister.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Debes ingresar los datos indicados.", Toast.LENGTH_SHORT).show();

                }else {
                    registerUser(userEmialRegister, userPasswordRegister);
                }

            }
        });






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

    private void registerUser(String userEmialRegister, String userPasswordRegister) {
        mAuth.createUserWithEmailAndPassword(userEmialRegister, userPasswordRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", userEmialRegister);
                map.put("pass", userPasswordRegister);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error al guardar el usuario.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error al crear el usuario.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}