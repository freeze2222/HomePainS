package com.example.homepain.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homepain.MainActivity;
import com.example.homepain.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.example.homepain.R;

public class SignInActivity extends AppCompatActivity {
    Button goToCreateUserButton;
    Button signInButton;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();

        goToCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateUserActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. get values
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                //2. check values
                if (email.isEmpty()) {
                    emailEditText.setError("Email is empty");
                    showMessage("Email is empty");
                    return;
                }
                if (!email.contains("@") || email.length() < 3) {
                    emailEditText.setError("Password must be correct");
                    showMessage("Password must be correct");
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password is empty");
//                    showMessage("Password is empty");
                    return;
                }
                if (password.length() < 6) {
                    passwordEditText.setError("Password must be correct");
//                    showMessage("Password must be correct");
                    return;
                }
                //3. do with values

                System.out.println("Data:");
                System.out.println(email);
                System.out.println(password);

                ProgressDialog bar = createPD();
                bar.setTitle("Loading");
                bar.show();

                AuthService.signIn(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showMessage("Can't create user");
                                e.printStackTrace();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                bar.dismiss();
                            }
                        });
            }
        });
    }

    ProgressDialog createPD() {
        return new ProgressDialog(this);
    }

    void initViews() {
        goToCreateUserButton = findViewById(R.id.buttonGoToCreateUser);
        signInButton = findViewById(R.id.buttonCreateUser2);
        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
    }

    void showMessage(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}