package com.example.inventoryproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandlerLogin extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "inventory";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USERS_TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String USER = "userName";

    // below variable id for our course duration column.
    private static final String PASSWORD = "password";


    // creating a constructor for our database handler.
    public DBHandlerLogin(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Run SQL query to create database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + USERS_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER + " TEXT,"
                + PASSWORD + " TEXT)";

        db.execSQL(query);
    }

    // Add new user to database
    public void addNewUser(String user, String password) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER, user);
        values.put(PASSWORD, password);

        db.insert(USERS_TABLE_NAME, null, values);

        db.close();
    }

    // Check if user is in database
    // Returns count of records matching username and password
    public int lookupUser(String user, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        int numUsersFound = 0;

        String query = "SELECT count(*) FROM " + USERS_TABLE_NAME + " WHERE userName = '" + user + "' AND password = '" + password + "'";
        Cursor userCount = db.rawQuery(query, null);


        if (userCount.moveToFirst()) {
            numUsersFound = userCount.getInt(0);
        }

        db.close();
        return numUsersFound;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);
    }
}