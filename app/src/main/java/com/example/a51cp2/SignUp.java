package com.example.a51cp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText fullName, userName, password, confirmPass;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.full_name);
        userName = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        confirmPass = findViewById(R.id.Confirm_Pass);

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full = fullName.getText().toString();
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmPass.getText().toString();
                if (!full.isEmpty() && !user.isEmpty() && !pass.isEmpty() && !confirm.isEmpty()){
                    if (pass.equals(confirm)){
                        MyDatabase myDB = new MyDatabase(SignUp.this);
                        myDB.addUser(userName.getText().toString(), fullName.getText().toString(), password.getText().toString());
                    }
                    else{
                        Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignUp.this, "All fields must be completed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}