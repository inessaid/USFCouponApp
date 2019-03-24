package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private EditText AEmail, APassword;
    private TextView student;
   // LoginButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        AEmail = (EditText) findViewById(R.id.adminEmail);
        APassword = (EditText) findViewById(R.id.adminPassword);
        student = (TextView)  findViewById(R.id.student);
        Button loginbutton = (Button) findViewById(R.id.adminLogin);
        final String AE = AEmail.getText().toString();
        final String AP = APassword.getText().toString();


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, signIn.class);
                startActivity(intent);
                finish();
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (AE.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                /*if (AP.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                // if (AE== "admin"  && AP=="ADMIN")
                //{
                    Intent intent = new Intent(AdminLogin.this, AdminPage.class);
                    startActivity(intent);
                    finish();
               // }

            }
        });
    }
}
