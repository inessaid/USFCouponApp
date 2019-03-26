package edu.usf.cse.labrador.usfcouponapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.usf.cse.labrador.usfcouponapp.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class couponsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Coupon> couponList;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    FirebaseAuth.AuthStateListener mAuthListner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
       // Log.d("BLAH","Beginning of onCreateView");
        final View rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //****************************************************************************
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        //final String userID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Coupons");
        couponList = new ArrayList<>();
       // Log.d("BLAH","Before addValueEventListener");

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //Log.d("BLAH","Beginning of onDataChange");
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                        Coupon coupon = ds.getValue(Coupon.class);
                        couponList.add(coupon);
                        Log.d("BLAH", "BusinessName:" + coupon.getBusinessname());
                        Log.d("BLAH", "BusinessAddress:" + coupon.getBusinessaddress());
                        Log.d("BLAH", "CouponName:" + coupon.getCouponname());
                        Log.d("BLAH", "BusinessName:" + coupon.getDuedate());
                        Log.d("BLAH", "DateAdmin:" + coupon.getDate());
                        Log.d("BLAH", "Description:" + coupon.getDescription());
                }
                //Log.d("BLAH","After for loop in onDataChange");
                //adapter = new MyAdapter(couponList, this.getContext());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("BLAH", "AFTER addevent");
        //*******************************************************************
        /*
        couponList = new ArrayList<>();
        String hi = "Hello";
        for(int i = 0; i <= 10; i++)
        {
            Coupon coupon = new Coupon("The address", hi, "The Description","The Category","The duedate","The date Administered","The image url","The pid?", "The time", "The coupon name");
            //Coupon coupon = new Coupon("The address", "The business", "The Description","The Category","The duedate","The date Administered","The image url","The pid?", "The time", "The coupon name");
            couponList.add(coupon);
        }
        */
        //Instantiates a layout XML file into its corresponding View objects
        adapter = new MyAdapter(couponList, this.getContext());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
            }
        }, 3000);

        return rootView;

    }
    @Override
    public void onStart()
    {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListner);
    }

}


/*
        couponRef.addValueEventListener(new ValueEventListener() {

            //final List<Coupon> couponList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    Coupon coupon = ds.getValue(Coupon.class);
                    //couponList.add(coupon);
                    Log.d("BLAH", coupon.getBusinessname());
                    Log.d("BLAH", coupon.getAddress());
                }
                myCouponList = (RecyclerView) CouponFragmentView.findViewById(R.id.couponList);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                myCouponList.setLayoutManager(llm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }


        });
 */