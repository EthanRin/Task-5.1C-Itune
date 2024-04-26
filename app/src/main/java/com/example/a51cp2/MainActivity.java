package com.example.a51cp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kotlin.annotation.MustBeDocumented;

public class MainActivity extends AppCompatActivity {

    EditText userName, passWord;
    Button login, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.mainUsername);
        passWord = findViewById(R.id.mainPassword);

        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pass = passWord.getText().toString();
                if (!name.isEmpty() && !pass.isEmpty()){
                    MyDatabase myDB = new MyDatabase(MainActivity.this);
                    if (myDB.loginValidation(name, pass)){
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        intent.putExtra("Username", userName.getText().toString());
                        intent.putExtra("Password", passWord.getText().toString());
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Username or Password incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Username and Password cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp = findViewById(R.id.signup_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}