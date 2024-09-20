package com.example.shoopingmanagementapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Find the EditTexts and Button
        EditText usernameEditText = findViewById(R.id.usernameRegisterEditText);
        EditText passwordEditText = findViewById(R.id.passwordRegisterEditText);
        EditText phoneEditText = findViewById(R.id.phoneRegisterEditText);
        Button registerSubmitButton = findViewById(R.id.registerSubmitButton);

        // Set onClickListener for the Register button
        registerSubmitButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            // Save user data to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("phone", phone);
            editor.apply();  // Save changes asynchronously

            // Show a success message
            Toast.makeText(RegisterActivity.this, "User Registered!", Toast.LENGTH_SHORT).show();

            // Optionally, redirect the user to the login screen or finish this activity
            finish();  // Closes the RegisterActivity and goes back to MainActivity
        });
    }
}
