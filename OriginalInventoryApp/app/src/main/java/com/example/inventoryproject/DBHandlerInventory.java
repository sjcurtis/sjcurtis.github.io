package com.example.inventoryproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandlerInventory extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventory";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "items";
    private static final String ID_COL = "id";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";

    // creating a constructor for database handler.
    public DBHandlerInventory(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Run SQL query to create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + QUANTITY + " TEXT)";

        db.execSQL(query);
    }

    // Add new item to database
    public void addItem(String name, String quantity) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(QUANTITY, quantity);

        db.insert(TABLE_NAME, null, values);

        // close database connection
        db.close();
    }

    // Returns a list of items
    public ArrayList<ItemModal> getItems() {

        SQLiteDatabase db = this.getReadableDatabase();

        // Query to select all items from table
        Cursor cursorItems = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


        ArrayList<ItemModal> itemModalArrayList = new ArrayList<>();

        if (cursorItems.moveToFirst()) {
            do {
                // Add the data from cursor to array list
                itemModalArrayList.add(new ItemModal(
                        cursorItems.getString(0),
                        cursorItems.getString(1),
                        cursorItems.getString(2)));
            } while (cursorItems.moveToNext());
        }

        cursorItems.close();
        return itemModalArrayList;
    }

    // Delete an item
    public ArrayList<ItemModal> deleteItem(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Delete item from database
        Cursor cursorItems = db.rawQuery("DELETE FROM " + TABLE_NAME + " WHERE ID = " + id, null);

        ArrayList<ItemModal> itemModalArrayList = new ArrayList<>();

        if (cursorItems.moveToFirst()) {
            do {
                // Add the data from cursor to array list
                itemModalArrayList.add(new ItemModal(
                        cursorItems.getString(0),
                        cursorItems.getString(1),
                        cursorItems.getString(2)));
            } while (cursorItems.moveToNext());
        }

        cursorItems.close();
        return itemModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<ItemModal> updateItem(String id, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create statement to update quantity in database
        Cursor cursorItems = db.rawQuery("UPDATE " + TABLE_NAME + " SET quantity = " + quantity + " WHERE ID = " + id, null);

        ArrayList<ItemModal> itemModalArrayList = new ArrayList<>();

        if (cursorItems.moveToFirst()) {
            do {
                // Add the data from cursor to array list
                itemModalArrayList.add(new ItemModal(
                        cursorItems.getString(0),
                        cursorItems.getString(1),
                        cursorItems.getString(2)));
            } while (cursorItems.moveToNext());
        }

        cursorItems.close();
        return itemModalArrayList;
    }
}