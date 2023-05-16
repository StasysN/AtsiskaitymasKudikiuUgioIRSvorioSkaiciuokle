package com.example.happybaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setError(null);
                password.setError(null);
                if (Validation.isUsernameValid(username.getText().toString())) {
                    if (Validation.isPasswordValid(password.getText().toString())) {
                        Intent goToMainActivity = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(goToMainActivity);

                    } else {
                        password.setError(getResources().getString(R.string.login_wrong_input));
                        password.requestFocus();
                    }
                } else {
                    username.setError(getResources().getString(R.string.login_wrong_input));
                    username.requestFocus();
                }
            }

        });


    }

}