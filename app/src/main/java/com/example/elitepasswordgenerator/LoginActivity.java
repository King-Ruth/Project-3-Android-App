package com.example.elitepasswordgenerator;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    EditText lusername, lpassword;
    Button lsignin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lusername = (EditText) findViewById(R.id.usernameL);
        lpassword = (EditText) findViewById(R.id.passwordL);
        lsignin = (Button) findViewById(R.id.btnsigninL);
        DB = new DBHelper(this);

        lsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String luser = lusername.getText().toString();
                String lpass = lpassword.getText().toString();

                if(luser.equals("") || lpass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Fields incomplete",
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    Boolean hereGoes = DB.checkUsername(luser);
                    if(hereGoes)
                    {
                        Boolean chUsPass = DB.checkUsernamePassword(luser, lpass);
                        if (chUsPass)
                        {
                            Toast.makeText(LoginActivity.this, "Signed in successfully!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), PasswordGeneratorActivity.class);
                            startActivity(intent);
                        } else
                        {
                            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();

                        }

                    } else
                    {
                        Toast.makeText(LoginActivity.this, "You don't exist yet!", Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });
    }
}