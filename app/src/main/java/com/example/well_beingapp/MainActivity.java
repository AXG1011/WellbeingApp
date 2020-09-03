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

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'MainActivity' class sets up the 'sign up' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_main)' method. This class also includes
 * the logic required to register users for an account and log this account-related data to the
 * Google Firestore database.
 */

public class MainActivity extends AppCompatActivity {

    // Declaring edit text fields:
    EditText emailId;
    EditText password;
    EditText fullName;

    // Declaring sign up button:
    Button buttonSignUp;

    // Declaring sign in text view:
    TextView textViewSignIn;

    // Declaring firebase authentication and firestore variables:
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sets the page's layout using activity_main.xml file

        fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication
        fStore = FirebaseFirestore.getInstance(); // Getting an instance of Firebase Firestore

        // Linking variables to corresponding fields, button and text outlined in the activity_main.xml file:
        fullName = findViewById(R.id.editTextName);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonSignUp = findViewById(R.id.button);
        textViewSignIn = findViewById(R.id.textView);

        if (fAuth.getCurrentUser() != null) { // If the user has already signed in, take them to the landing page
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Upon clicking the sign up button, the following code will execute:

                final String email = emailId.getText().toString();
                final String name = fullName.getText().toString();
                final String pword = password.getText().toString();

                if (name.isEmpty()) {
                    fullName.setError("Please enter your name"); // Error message shown if user does not enter their name
                    fullName.requestFocus(); // Highlights the name edit text field

                } else if (email.isEmpty()) {
                    emailId.setError("Please enter your email address"); // Error message shown if user does not enter their email address
                    emailId.requestFocus(); // Highlights the email edit text field

                } else if (pword.isEmpty()) {
                    password.setError("Please enter your password"); // Error message shown if user does not enter their password
                    password.requestFocus(); // Highlights the password edit text field

                } else if (email.isEmpty() && pword.isEmpty() && name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty!",
                            Toast.LENGTH_SHORT).show(); // Error message shown if all fields are empty
                } else {
                    fAuth.createUserWithEmailAndPassword(email, pword).addOnCompleteListener // Firebase authentication creates a new user with email and password
                            (MainActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) { // If the account is created successfully, show the following message:
                                        Toast.makeText(MainActivity.this, "Account created", Toast.LENGTH_SHORT).show();

                                        // Creating a hash map with the user's name, email and password and declaring document fields:
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("fullName", name);
                                        user.put("email", email);
                                        user.put("password", pword);

                                        fStore.collection("users") // Adds data to the 'users' collection
                                                .add(user) // Add new user object to users collection
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) { // If the task is successful:
                                                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) { // If the task fails:
                                                        Log.w("TAG", "Error adding document", e);
                                                    }
                                                });

                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class)); // Takes the user to the landing page

                                    } else { // If an error has occurred, the following pop-up message will be displayed:
                                        Toast.makeText(MainActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Takes users with an existing account to the sign in page
                Intent mainToLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(mainToLogin);
            }
        });
    }

}