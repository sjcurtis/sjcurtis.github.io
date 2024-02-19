package com.example.inventoryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private Button buttonBack, buttonSubmit;
    private EditText name, quantity;

    private DBHandlerInventory dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        buttonBack = findViewById(R.id.buttonBack);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        name = findViewById(R.id.textItemName);
        quantity = findViewById(R.id.textQuantity);


        dbHandler = new DBHandlerInventory(AddItemActivity.this);

        // Navigate back to inventory
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(intent);
            }
        });

        // Navigate back to inventory
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString();
                String q = quantity.getText().toString();

                // Add new item to database
                dbHandler.addItem(n, q);

                Toast.makeText(AddItemActivity.this, "Item successfully added.", Toast.LENGTH_SHORT).show();

                // Navigate back to inventory
                Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                startActivity(intent);
            }
        });
    }
}