package com.example.inventoryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;

    private Button register;
    private Button login;
    private DBHandlerLogin dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing variables
        register = findViewById(R.id.buttonRegister);
        login = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        dbHandler = new DBHandlerLogin(LoginActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                // validating if the text fields are empty or not.
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if password meets minimums and notify user if they do not.
                if (!validPassword(pass)){
                    Toast.makeText(LoginActivity.this, "Password did not met required strength.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add new user to database
                dbHandler.addNewUser(user, pass);

                Toast.makeText(LoginActivity.this, "User has been added.", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                int userFound = 0;

                // Validating if the text fields are empty or not.
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                userFound = dbHandler.lookupUser(user, pass);

                if (userFound > 0)
                    try {
                        Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception ex){
                        Log.e("intent", ex.toString());
                    }

                else
                    Toast.makeText(LoginActivity.this, "User not found.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Function checks if password meets requirements by looping through
    // each char in string.
    //
    // Requirements:
    //  - length >= 8
    //  - Uppercase >= 1
    //  - Lowercase >= 1
    //  - Digits >= 1
    //  - Special >= 1
    private boolean validPassword(String password) {

        boolean isValid = false;
        short passwordLength = 0;
        short uppercase = 0;
        short lowercase = 0;
        short digit = 0;
        short special = 0;
        String validSpecial = "!@#$%^&*()-";

        // Loop through each character in the password string
        for (char ch: password.toCharArray()) {
            passwordLength += 1;

            // Check and count uppercase characters
            if (Character.isUpperCase(ch))
                uppercase += 1;

            // Check and count lowercase characters
            if (Character.isLowerCase(ch))
                lowercase += 1;

            // Check and count digit characters
            if (Character.isDigit(ch))
                digit += 1;

            // Check and count special characters
            // indexOf returns -1 if no occurrence
            // of character is found
            if (validSpecial.indexOf(ch) > -1)
                special += 1;

        }

        // Check to make sure password meets required minimums.
        if (passwordLength >= 8 &&
                uppercase >= 1 &&
                lowercase >= 1 &&
                digit >= 1 &&
                special >= 1 )
        {
            isValid = true;
        }

        return isValid;
    }
}
