package edu.usf.cse.labrador.usfcouponapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.usf.cse.labrador.usfcouponapp.R;

public class couponsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Instantiates a layout XML file into its corresponding View objects
        return inflater.inflate(R.layout.fragment_coupons,null);
    }
}
