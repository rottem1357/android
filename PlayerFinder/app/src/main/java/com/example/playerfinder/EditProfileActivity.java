package com.example.playerfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView editProfileImageView;
    private TextView editUsername, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfileImageView = findViewById(R.id.editProfileImageView);
        editUsername = findViewById(R.id.editTextUsername);
        editEmail = findViewById(R.id.editTextEmail);

        editProfileImageView.setOnClickListener(view -> openGallery());

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(view -> saveProfile());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            editProfileImageView.setImageURI(imageUri);
        }
    }

    private void saveProfile() {
        // Logic to save profile changes
        finish();
    }
}
