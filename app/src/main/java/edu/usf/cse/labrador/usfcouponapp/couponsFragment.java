package edu.usf.cse.labrador.usfcouponapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.squareup.picasso.Picasso;

import edu.usf.cse.labrador.usfcouponapp.R;
import edu.usf.cse.labrador.usfcouponapp.ViewHolder.CouponViewHolder;

public class couponsFragment extends Fragment {

    private DatabaseReference CouponRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupons, container,false);
        CouponRef = FirebaseDatabase.getInstance().getReference().child("Coupon");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //Instantiates a layout XML file into its corresponding View objects
        return inflater.inflate(R.layout.fragment_coupons,null);

    }

    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseRecyclerOptions<Coupon> options =
                new FirebaseRecyclerOptions.Builder<Coupon>()
                .setQuery(CouponRef, Coupon.class)
                .build();
        FirebaseRecyclerAdapter<Coupon, CouponViewHolder> adapter =
                new FirebaseRecyclerAdapter<Coupon, CouponViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CouponViewHolder holder, int position, @NonNull Coupon model) {
                        holder.txtCouponName.setText(model.getCouponName());
                        holder.txtCouponDescription.setText(model.getCouponName());
                        holder.txtCouponDuedate.setText(model.getDueDate());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                    }

                    @NonNull
                    @Override
                    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup  parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupons_items_layout, parent, false);
                        CouponViewHolder holder = new CouponViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }
}
