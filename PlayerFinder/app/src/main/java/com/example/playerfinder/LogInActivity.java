package com.example.playerfinder;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    private EditText UserName;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_log_in_activity);

        // Bind views to XML components
        UserName = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Set click listener for the login button
        loginButton.setOnClickListener(v -> {
            String email = UserName.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(email)) {
                UserName.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Password is required");
                return;
            }

            // Handle login logic (this can be API call or local validation)
            // For demonstration, we just show a Toast
            Toast.makeText(LogInActivity.this, "Logged in with Email: " + email, Toast.LENGTH_SHORT).show();
        });
    }
}