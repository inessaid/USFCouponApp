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
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }
    Button LogOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        final String userID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("users").child(userID);


        final TextView name = (TextView) rootView.findViewById(R.id.display_name);
        final TextView DOB = (TextView) rootView.findViewById(R.id.display_DOB);
        final TextView phone_num = (TextView) rootView.findViewById(R.id.display_phonenum);
        final TextView email = (TextView) rootView.findViewById(R.id.display_email);

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
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                    String fName = userInfo.getFirstname();
                    String lName = userInfo.getLastname();
                    String bday = userInfo.getDob();
                    String pnum = userInfo.getPhonenumber();
                    String theemail = userInfo.getEmail();
                    String fullname = fName + " " + lName;
                    name.setText(fullname);
                    DOB.setText(bday);
                    phone_num.setText(pnum);
                    email.setText(theemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       return rootView;

    }
}
