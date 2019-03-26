package edu.usf.cse.labrador.usfcouponapp;

import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Coupon> couponList;
    private Context context;

    public MyAdapter(List<Coupon> couponList, Context context) {
        this.couponList = couponList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Coupon coupon = couponList.get(position);
        holder.businessName.setText(coupon.getBusinessname());
        holder.couponName.setText(coupon.getCouponname());
        holder.address.setText(coupon.getBusinessaddress());
        holder.descript.setText(coupon.getDescription());
        holder.expireDate.setText(coupon.getDuedate());
        holder.admissiondate.setText(coupon.getDate());

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView businessName;
        public TextView expireDate;
        public TextView admissiondate;
        public TextView descript;
        public TextView address;
        public TextView couponName;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            businessName = (TextView) itemView.findViewById(R.id.businessName);
            expireDate = (TextView) itemView.findViewById(R.id.expireDate);
            admissiondate = (TextView) itemView.findViewById(R.id.date);
            descript = (TextView) itemView.findViewById(R.id.description);
            address = (TextView) itemView.findViewById(R.id.address);
            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String theAddress;
                    String theAddress = address.getText().toString();
                    Uri direction = Uri.parse("google.navigation:q=" + theAddress);
                    Intent intent = new Intent(Intent.ACTION_VIEW, direction);
                    intent.setPackage("com.google.android.apps.maps");
                    context.startActivity(intent);
                    //Log.d("BLAH","The Address ONCLick:" + theAddress);
                }
            });
            couponName = (TextView) itemView.findViewById(R.id.couponName);
        }


    }

}
