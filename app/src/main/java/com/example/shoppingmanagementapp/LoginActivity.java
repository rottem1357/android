package com.example.shoppingmanagementapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Find the EditTexts and Button
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginSubmitButton = findViewById(R.id.loginSubmitButton);

        // Set onClickListener for the Login button
        loginSubmitButton.setOnClickListener(v -> {
            String enteredUsername = usernameEditText.getText().toString();
            String enteredPassword = passwordEditText.getText().toString();

            // Retrieve stored user data from SharedPreferences
            String storedUsername = sharedPreferences.getString("username", null);
            String storedPassword = sharedPreferences.getString("password", null);

            // Validate the login credentials
            if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                // Redirect to another screen after successful login, e.g., home screen
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
