package com.example.playerfinder;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "language_prefs";
    private static final String LANGUAGE_KEY = "language_key";
    private TextView languageLabel; // Label to display current language

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the language based on stored preferences
        loadLanguage();

        setContentView(R.layout.activity_main);

        // Get a reference to the TextView
        languageLabel = findViewById(R.id.language_label);

        // Display the current language at the top
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String currentLanguage = prefs.getString(LANGUAGE_KEY, "en");
        updateLanguageLabel(currentLanguage); // Method to update label text

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find buttons by ID
        Button navButton = findViewById(R.id.nav_button);
        Button searchButton = findViewById(R.id.search_button);
        Button settingsButton = findViewById(R.id.settings_button);

        // Set onClickListeners to show a Toast message
        navButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Navigation button pressed", Toast.LENGTH_SHORT).show()
        );

        searchButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Search button pressed", Toast.LENGTH_SHORT).show()
        );

        // Show language switcher dialog when settings button is pressed
        settingsButton.setOnClickListener(v -> showLanguageDialog());
    }

    // Show a dialog to switch language
    private void showLanguageDialog() {
        String[] languages = {getString(R.string.english), getString(R.string.hebrew)};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.choose_language)
                .setItems(languages, (dialog, which) -> {
                    if (which == 0) {
                        // English selected
                        setLocale("en");
                    } else if (which == 1) {
                        // Hebrew selected (use "iw" for Hebrew)
                        setLocale("iw");
                    }
                })
                .show();
    }

    // Method to set the selected locale and update the app's language
    private void setLocale(String langCode) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String currentLanguage = prefs.getString(LANGUAGE_KEY, "en"); // Default to English if no preference

        // Only proceed if the selected language is different from the current language
        if (!currentLanguage.equals(langCode)) {
            Locale locale = new Locale(langCode);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.setLocale(locale); // For API level 24 and above

            // Update the configuration with the new locale
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

            // Save language to preferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(LANGUAGE_KEY, langCode);
            editor.apply();

            // Update the label to reflect the new language setting
            updateLanguageLabel(langCode);

            // Recreate the activity to apply the language change
            recreate();
        }
    }

    // Load the saved language from shared preferences
    private void loadLanguage() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String language = prefs.getString(LANGUAGE_KEY, "en"); // Default to English if no preference
        setLocale(language);
    }

    private void updateLanguageLabel(String langCode) {
        if (langCode.equals("en")) {
            languageLabel.setText(getString(R.string.current_language_english));
        } else if (langCode.equals("iw")) {
            languageLabel.setText(getString(R.string.current_language_hebrew)); // Now for "iw"
        }
    }
}
