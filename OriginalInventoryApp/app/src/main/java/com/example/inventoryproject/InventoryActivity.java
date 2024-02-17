package com.example.inventoryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private FloatingActionButton addInventory;
    private DBHandlerInventory dbHandler;
    private ArrayList<ItemModal> itemModalArrayList;
    private ItemRVAdapter itemRVAdapter;
    private RecyclerView itemRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        addInventory = findViewById(R.id.AddButton);

        dbHandler = new DBHandlerInventory(InventoryActivity.this);


        // Initializing variables.
        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandlerInventory(InventoryActivity.this);

        // Getting item array list from db handler class.
        itemModalArrayList = dbHandler.getItems();


        itemRVAdapter = new ItemRVAdapter(itemModalArrayList, InventoryActivity.this);
        itemRV = findViewById(R.id.rvItemList);

        // Setting layout manager for recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InventoryActivity.this, RecyclerView.VERTICAL, false);
        itemRV.setLayoutManager(linearLayoutManager);

        // Setting adapter to recycler view.
        itemRV.setAdapter(itemRVAdapter);


        // Load add inventory activity
        addInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

    }
}