package com.example.shoppingmanagementapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
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

        // Initialize SharedPreferences and product set
        sharedPreferences = getSharedPreferences("ProductPrefs", MODE_PRIVATE);
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

        // Add product to list
        addProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            String productQuantity = productQuantityEditText.getText().toString();
            String productEntry = productName + " (x" + productQuantity + ")";

            if (!productName.isEmpty() && !productQuantity.isEmpty()) {
                productList.add(productEntry);
                adapter.notifyDataSetChanged();

                // Save products to SharedPreferences
                productSet.add(productEntry);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("products", productSet);
                editor.apply();

                Toast.makeText(ProductActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProductActivity.this, "Please enter both name and quantity", Toast.LENGTH_SHORT).show();
            }
        });

        // Remove product from list
        removeProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            String productQuantity = productQuantityEditText.getText().toString();
            String productEntry = productName + " (x" + productQuantity + ")";

            if (productList.contains(productEntry)) {
                productList.remove(productEntry);
                adapter.notifyDataSetChanged();

                // Save changes to SharedPreferences
                productSet.remove(productEntry);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("products", productSet);
                editor.apply();

                Toast.makeText(ProductActivity.this, "Product Removed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProductActivity.this, "Product not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
