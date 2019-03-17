package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;


public class signup_activity extends AppCompatActivity {
    private EditText first_name, last_name, dob, phone_num, email_id, passwordcheck;
    private FirebaseAuth mAuth;
    private static final String TAG = "";
    private ProgressBar progressBar;
    //FirebaseDatabase databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        TextView btnSignUp = (TextView) findViewById(R.id.login_page);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_activity.this, signIn.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        first_name = (EditText) findViewById(R.id.input_firstName);
        last_name = (EditText) findViewById(R.id.input_lastName);
        dob = (EditText) findViewById(R.id.input_dateOfBirth);
        phone_num = (EditText) findViewById(R.id.input_phone);
        email_id = (EditText) findViewById(R.id.input_email);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        passwordcheck = (EditText) findViewById(R.id.input_password);
        Button ahsignup = (Button) findViewById(R.id.btn_signup);
        ahsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_id.getText().toString();
                String password = passwordcheck.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signup_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign up success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    //final FirebaseDatabase databaseUsers = FirebaseDatabase.getInstance("https://coupon-app-46f51.firebaseio.com/"); //Realtime database
                                    DatabaseReference ref;
                                    ref = FirebaseDatabase.getInstance().getReference();

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    //Get user information and sets it to a string.
                                    String uid = user.getUid();
                                    String fname = first_name.getText().toString();
                                    String lname = last_name.getText().toString();
                                    String birthdate = dob.getText().toString();
                                    String pnum = phone_num.getText().toString();

                                    String id = ref.push().getKey();
                                   // String id = databaseUsers.push().getKey();

                                    UserInfo theuser = new UserInfo(fname, lname, birthdate, pnum, 0);

                                    //Sends data to realtime database with uid linked to it.
                                    ref.child("users").child(uid).child("firstname").setValue(fname);
                                    ref.child("users").child(uid).child("lastname").setValue(lname);
                                    ref.child("users").child(uid).child("Date of Birth").setValue(birthdate);
                                    ref.child("users").child(uid).child("Phone Number").setValue(pnum);
                                    ref.child("users").child(uid).child("isBuis").setValue(0);  //Change setValue once you add a button for isBusiness in signup.

                                    Intent intent = new Intent(signup_activity.this, MainActivity.class); //Starts main activity.
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign up fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(signup_activity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}



