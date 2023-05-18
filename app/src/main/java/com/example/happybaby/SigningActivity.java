package com.example.happybaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigningActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper  DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        username = (EditText)  findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button)  findViewById(R.id.btsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SigningActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser==false){
                            Boolean insert =DB.insertData(user, pass);
                            if (insert==true){
                                Toast.makeText(SigningActivity.this, "Registered succesfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else {Toast.makeText(SigningActivity.this, "Registered failed", Toast.LENGTH_LONG).show();}
                        }else {Toast.makeText(SigningActivity.this, "User already exist! Please sign in", Toast.LENGTH_LONG).show();}
                    }else {Toast.makeText(SigningActivity.this, "Password not matching!", Toast.LENGTH_LONG).show();}
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}