package com.example.a5046assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.a5046assessment.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = binding.emailEditText.getText().toString();
                String password_text = binding.passwordEditText.getText().toString();
                String name_text = binding.nameEditText.getText().toString();
                String address_text = binding.addressEditText.getText().toString();

                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)) {
                    String msg = "Empty Email or Password";
                    Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password_text.length() < 6) {
                        String msg = "Password is too short";
                        Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else if (password_text.matches("^[a-zA-Z0-9]*$")) {
                        String msg = "Password should contain at least ONE special character";
                        Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (TextUtils.isEmpty(name_text) || TextUtils.isEmpty(address_text)) {
                            String msg = "Empty Name or Address";
                            Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).
                                    show();
                        }
                        else {
                            registerUser(email_text, password_text);
                            storeUserInfo(name_text, address_text);
                        }
                        /*
                        else {
                            if (!address_text.matches("")) {
                                String msg = "Invalid Address";
                                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).
                                        show();
                            }
                            else {
                                registerUser(email_text, password_text);
                                storeUserInfo(name_text, address_text);
                            }
                        } */
                    }
                }
            }
        });
    }

    private void registerUser(String email_text, String password_text) {
        auth.createUserWithEmailAndPassword(email_text, password_text).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String msg = "Registration Successful";
                            Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).
                                    show();
                            Intent intent = new Intent(SignupActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            String msg = "Registration Unsuccessful";
                            Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).
                                    show();
                        }
                    }
                });
    }

    private void storeUserInfo(String name_text, String address_text) {

    }
}