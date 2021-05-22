package com.example.elitepasswordgenerator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText username, password, repassword;
    Button signin, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signin = (Button) findViewById(R.id.btnsignin);
        signup = (Button) findViewById(R.id.btnsignup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fields incomplete", Toast.LENGTH_SHORT).show();
                } else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkUser = DB.checkUsername(user);
                        if(!checkUser)
                        {
                            Boolean insert = DB.insertData(user, pass);
                            if(insert)
                            {
                                Toast.makeText(MainActivity.this, "Registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PasswordGeneratorActivity.class);
                                startActivity(intent);
                            } else
                            {
                                Toast.makeText(MainActivity.this, "Registration failed, sorry",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else
                        {
                            Toast.makeText(MainActivity.this, "User already exists! Sign in instead",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else
                    {
                        Toast.makeText(MainActivity.this, "Password mismatch!",
                                Toast.LENGTH_SHORT).show();
                    }
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