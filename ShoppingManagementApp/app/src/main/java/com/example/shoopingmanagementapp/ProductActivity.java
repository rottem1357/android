package com.example.shoopingmanagementapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Set<String> productSet;
    private ArrayList<String> productList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Retrieve user info from SharedPreferences
        String username = sharedPreferences.getString("username", "User");
        String phoneNumber = sharedPreferences.getString("phone", "N/A");

        // Set the user information on the TextView
        TextView userInfoTextView = findViewById(R.id.userInfoTextView);
        // Set the user information on the TextView using a string resource with placeholders
        String userInfoText = getString(R.string.user_welcome_message, username, phoneNumber);
        userInfoTextView.setText(userInfoText);

        // Initialize product set and list
        productSet = sharedPreferences.getStringSet("products", new HashSet<>());
        productList = new ArrayList<>(productSet);

        // Initialize UI elements
        EditText productNameEditText = findViewById(R.id.productNameEditText);
        EditText productQuantityEditText = findViewById(R.id.productQuantityEditText);
        Button addProductButton = findViewById(R.id.addProductButton);
        Button removeProductButton = findViewById(R.id.removeProductButton);
        ListView productListView = findViewById(R.id.productListView);

        // Initialize the ListView with current products
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

        // Add product to list (same as before)
        addProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            String productQuantityStr = productQuantityEditText.getText().toString();

            if (productName.isEmpty()) {
                Toast.makeText(ProductActivity.this, "Product name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (productQuantityStr.isEmpty() || Integer.parseInt(productQuantityStr) <= 0) {
                Toast.makeText(ProductActivity.this, "Quantity must be a positive number", Toast.LENGTH_SHORT).show();
                return;
            }

            String productEntry = productName + " (x" + productQuantityStr + ")";
            productList.add(productEntry);
            adapter.notifyDataSetChanged();

            // Save products to SharedPreferences
            productSet.add(productEntry);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("products", productSet);
            editor.apply();

            Toast.makeText(ProductActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
        });

        // Remove product from list (same as before)
        removeProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            String productQuantityStr = productQuantityEditText.getText().toString();
            String productEntry = productName + " (x" + productQuantityStr + ")";

            if (productList.contains(productEntry)) {
                new AlertDialog.Builder(ProductActivity.this)
                        .setTitle("Remove Product")
                        .setMessage("Are you sure you want to remove " + productEntry + "?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            productList.remove(productEntry);
                            adapter.notifyDataSetChanged();

                            productSet.remove(productEntry);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putStringSet("products", productSet);
                            editor.apply();

                            Toast.makeText(ProductActivity.this, "Product Removed", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                Toast.makeText(ProductActivity.this, "Product not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
