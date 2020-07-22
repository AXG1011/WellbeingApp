package com.example.well_beingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password;
    Button buttonSignUp;
    TextView textViewSignIn;
    FirebaseAuth firebaseAuthorisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Triad - Sign Up");

        firebaseAuthorisation = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonSignUp = findViewById(R.id.button);
        textViewSignIn = findViewById(R.id.textView);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pword = password.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter your email address");
                    emailId.requestFocus();
                }
                else if(pword.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pword.isEmpty())){
                    firebaseAuthorisation.createUserWithEmailAndPassword(email, pword).addOnCompleteListener
                            (MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Sign up unsuccessful, " +
                                                        "please try again", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(MainActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();

                }

                }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }

}