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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup_activity extends AppCompatActivity {
    private EditText first_name, last_name, dob, phone_num, email_id, passwordcheck;
    private FirebaseAuth mAuth;
    private static final String TAG = "";
    private ProgressBar progressBar;

    // variable to store sign-up data from user to FireBase
    private FirebaseFirestore FirebaseData;
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
        FirebaseData = FirebaseFirestore.getInstance(); // reference to initialize variable
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
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();
                                    String fn = first_name.getText().toString();
                                    String ln = last_name.getText().toString();
                                    String db = dob.getText().toString();
                                    String ph = phone_num.getText().toString();

                                    // map to collect user sign-up data
                                    Map<String, String> userMap = new HashMap<>();

                                    userMap.put("UID", uid);
                                    userMap.put("first name", fn);
                                    userMap.put("last name", ln);
                                    userMap.put("DOB", db);
                                    userMap.put("phone number", ph);

                                    // store sign-up data to firebase collection location based on UID
                                    FirebaseData.collection("users").add(userMap);

                                    Intent intent = new Intent(signup_activity.this, MainActivity.class);
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