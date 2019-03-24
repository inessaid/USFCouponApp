package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.usf.cse.labrador.usfcouponapp.R;

public class settingsFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    public void onStart() {
        Log.d("BLAH","Beginning of onStart");
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }
    Button LogOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_settings,null);
        Log.d("BLAH", "Beginning of onCreateView");
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        final String userID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("users").child(userID);

        Log.d("BLAH","After getting user = mAuth.getCurentUser():");


        Log.d("BLAH","After getting userUID");
        Log.d("BLAH",userID);

        final TextView firstname = (TextView) rootView.findViewById(R.id.display_firstname);
        final TextView lastname = (TextView) rootView.findViewById(R.id.display_lastname);
        final TextView DOB = (TextView) rootView.findViewById(R.id.display_DOB);
        final TextView phone_num = (TextView) rootView.findViewById(R.id.display_phonenum);

        Log.d("BLAH","After getview of textview");



        LogOut = (Button) rootView.findViewById(R.id.signout);
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    Intent intent = new Intent(getActivity(),signIn.class);
                    startActivity(intent);
                   // startActivity(new Intent(settingsFragment.this, signIn.class));
                }
            }
        };
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // code what to do with button goes here
                mAuth.signOut();
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("BLAH","Beginning of onDataChange");
                //UserInfo uInfo = new UserInfo();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Log.d("BLAH","Beginning of for loop");
                    //UserInfo uInfo = new UserInfo();
                    UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                    Log.d("BLAH",userID);
                    Log.d("BLAH", "After Retrieving info from database");
                    String fName = userInfo.getFirstname();
                    String lName = userInfo.getLastname();
                    String bday = userInfo.getDob();
                    String pnum = userInfo.getPhonenumber();
                    Log.d("BLAH","DATA:" + fName + " " + lName);
                    Log.d("BLAH","Before setting Text");
                    firstname.setText(fName);
                    lastname.setText(lName);
                    DOB.setText(bday);
                    phone_num.setText(pnum);
                    Log.d("BLAH","After setting Text");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       Log.d("BLAH","Before Return statement");
       return rootView;

    }

    /*
    private void showData(DataSnapshot dataSnapshot)
    {
        String userID;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        Log.d("BLAH","Beginning of showData");
        for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            Log.d("BLAH","Beginning of for loop");
            UserInfo uInfo = new UserInfo();
            uInfo.setFirstName(ds.child("users").child(userID).getValue(UserInfo.class).getFirstName());
            uInfo.setLastName(ds.child("users").child(userID).getValue(UserInfo.class).getLastName());
            uInfo.setDOB(ds.child("user").child(userID).getValue(UserInfo.class).getDOB());
            uInfo.setPhoneNumber(ds.child("users").child(userID).getValue(UserInfo.class).getPhoneNumber());
            //uInfo.setIsBusiness(ds.child("users").child(userID).getValue(UserInfo.class).getIsBusiness());
            Log.d("BLAH", "After Retrieving info from database");

            String fName = uInfo.getFirstName();
            String lName = uInfo.getLastName();
            String bday = uInfo.getDOB();
            String pnum = uInfo.getPhoneNumber();



            Log.d("BLAH","After adding it to array");
            //array.add(business);
            Log.d("BLAH","After creating ArrayAdapter OBJ");
            Log.d("BLAH","After setting Listview to adapter");

        }


    }
    */
}
