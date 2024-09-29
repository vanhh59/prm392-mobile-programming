package com.example.demologin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize EditText fields
        edtEmail = findViewById(R.id.log_username);
        edtPassword = findViewById(R.id.log_password);

        // Initialize login button
        btnLogin = findViewById(R.id.login);

        // Initialize register button
        btnRegister = findViewById(R.id.signin); // Make sure the ID matches your register button in XML

        // Set OnClickListener for register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    login(email, password);
                }
            }
        });

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dangnhap_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success
                            Log.d("LOGIN_SUCCESS", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            // Navigate to WelcomeActivity
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();  // Close login activity to prevent returning to it
                        } else {
                            // Login failure
                            Log.w("LOGIN_FAILURE", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    // Optional: Create a new user method
    private void createNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Account creation success
                            Log.d("CREATE_USER", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Account creation failure
                            Log.w("CREATE_USER", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Account creation failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Reset password method (optional)
    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("RESET_PASSWORD", "Password reset email sent.");
                            Toast.makeText(LoginActivity.this, "Reset email sent.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w("RESET_PASSWORD", "Failed to send reset email", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed to send reset email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Sign out method (optional)
    private void signOut() {
        mAuth.signOut();
        Log.d("SIGN_OUT", "User signed out");
        updateUI(null);
    }

    // Update UI based on user login state
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.d("UPDATE_UI", "User logged in: " + user.getEmail());
            // Handle UI updates for logged-in user here
        } else {
            Log.d("UPDATE_UI", "No user logged in.");
            // Handle UI updates for logged-out state here
        }
    }
}
