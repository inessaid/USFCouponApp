package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import edu.usf.cse.labrador.usfcouponapp.R;

public class settingsFragment extends Fragment {
    private FirebaseAuth mAuth;
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
       // return inflater.inflate(R.layout.fragment_settings,null);

        final View rootView = inflater.inflate(R.layout.fragment_settings, container,
                false);

        LogOut = (Button) rootView.findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();
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
        }


        );
       return rootView;

    }



}
