package com.example.playerfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button; // Import Button
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView profileImageView;
    private TextView textViewUsername, textViewEmail;
    private Button buttonEditProfile; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.profileImageView);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonEditProfile = findViewById(R.id.buttonEditProfile); // Initialize here

        // Set default values for the profile
        textViewUsername.setText("John Doe");
        textViewEmail.setText("johndoe@example.com");

        // Set click listener to change the profile picture
        profileImageView.setOnClickListener(view -> openGallery());

        // Set click listener for edit profile button
        buttonEditProfile.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });
    }

    // Method to open the gallery to pick an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }
}
