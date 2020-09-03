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
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'LoginActivity' class sets up the 'sign in' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_login)' method. This class also includes
 * the logic required to sign existing users into their account by authenticating their email
 * address and password.
 */

public class LoginActivity extends AppCompatActivity {

    // Declaring edit text fields:
    EditText emailId;
    EditText password;

    // Declaring sign in button:
    Button buttonSignIn;

    // Declaring sign up text view:
    TextView textViewSignUp;

    // Declaring firebase authentication variable:
    FirebaseAuth firebaseAuthorisation;

    private FirebaseAuth.AuthStateListener AuthStateListener; // Listens for a change in the authentication state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Sets the page's layout using activity_login.xml file

        firebaseAuthorisation = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication

        // Linking variables to corresponding fields, button and text outlined in the activity_login.xml file:
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonSignIn = findViewById(R.id.button);
        textViewSignUp = findViewById(R.id.textView);

        AuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuthorisation.getCurrentUser();
                if(firebaseUser != null){
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i); // Takes the user to the landing page if they are already authenticated
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please log in", Toast.LENGTH_SHORT).show();

                }
            }
        };

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on the sign in button will execute the following code:

                String email = emailId.getText().toString();
                String pword = password.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter your email address"); // Error message shown if user does not enter their email address
                    emailId.requestFocus(); // Highlights the email edit text field
                }
                else if(pword.isEmpty()){
                    password.setError("Please enter your password"); // Error message shown if user does not enter their password
                    password.requestFocus(); // Highlights the password edit text field
                }
                else if(email.isEmpty() && pword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fields are empty!",
                            Toast.LENGTH_SHORT).show(); // Error message shown if both fields are empty
                }
                else if(!(email.isEmpty() && pword.isEmpty())) { // If the user has entered an email and password

                    firebaseAuthorisation.signInWithEmailAndPassword(email, pword).addOnCompleteListener
                            (LoginActivity.this, new OnCompleteListener<AuthResult>() { // Authenticates the user using Firebase Authentication
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) { // If user is successfully authenticated, they are taken to the landing page
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }
                            else { // If user was not authenticated, the following error message is displayed:
                                Toast.makeText(LoginActivity.this, "Login Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{ // If any other error occurred, the following message is displayed:
                    Toast.makeText(LoginActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Takes non-registered users to the sign up page
                Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuthorisation.addAuthStateListener(AuthStateListener); // Starts listening for changes to authentication state
    }
}