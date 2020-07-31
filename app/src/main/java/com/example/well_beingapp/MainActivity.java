package com.example.well_beingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password, fullName;
    Button buttonSignUp;
    TextView textViewSignIn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Triad - Sign Up");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fullName = findViewById(R.id.editTextName);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonSignUp = findViewById(R.id.button);
        textViewSignIn = findViewById(R.id.textView);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                final String name = fullName.getText().toString();
                final String pword = password.getText().toString();

                if (name.isEmpty()) {
                    fullName.setError("Please enter your name");
                    fullName.requestFocus();

                } else if (email.isEmpty()) {
                    emailId.setError("Please enter your email address");
                    emailId.requestFocus();

                } else if (pword.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();

                } else if (email.isEmpty() && pword.isEmpty() && name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    fAuth.createUserWithEmailAndPassword(email, pword).addOnCompleteListener
                            (MainActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Account created", Toast.LENGTH_SHORT).show();

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("fullName",name);
                                        user.put("email",email);
                                        user.put("password",pword);

                                        fStore.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("TAG", "Error adding document", e);
                                                    }
                                                });

                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                                    } else {
                                        Toast.makeText(MainActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
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