package edu.usf.cse.labrador.usfcouponapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.usf.cse.labrador.usfcouponapp.Interface.ItemClickListener;
import edu.usf.cse.labrador.usfcouponapp.R;

public class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtCouponName, txtCouponDescription, txtCouponDuedate;
    public ImageView imageView;
    public Button favoriteButt, directionButt;
    public ItemClickListener listener;


    public CouponViewHolder(View itemView){

        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.coupon_image);
        txtCouponName = (TextView) itemView.findViewById(R.id.coupon_name);
        txtCouponDescription = (TextView) itemView.findViewById(R.id.coupon_description);
        txtCouponDuedate = (TextView) itemView.findViewById(R.id.coupon_dueDate);
        favoriteButt =(Button)itemView.findViewById(R.id.add_to_favorites);
        directionButt = (Button)itemView.findViewById(R.id.get_directions);
    }

    public void setItemClickListener(ItemClickListener listener){

        this.listener = listener;

    }
    @Override
    public void onClick(View view)
    {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
