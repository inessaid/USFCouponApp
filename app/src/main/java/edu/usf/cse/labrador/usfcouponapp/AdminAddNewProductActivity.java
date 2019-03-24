package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String CategoryName,Description,dueDate,Name, saveCurrentDate, saveCurrentTime;
    private Button AddNewCouponButton;
    private ImageView InputCouponImage;
    private EditText InputCouponName,InputCouponDescription,InputCouponDueDate;
private static final int GalleryPick =1;
private Uri ImageUri;
private String couponRandomKey,downloadImageUrl;
private StorageReference CouponImagesRef;
private DatabaseReference CouponRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        CategoryName=getIntent().getExtras().get("category").toString();
        CouponImagesRef= FirebaseStorage.getInstance().getReference().child("Product Images");
        CouponRef=FirebaseDatabase.getInstance().getReference().child("Coupons");

        AddNewCouponButton =(Button)findViewById(R.id.add_new_coupon);
        InputCouponImage=(ImageView)findViewById(R.id.select_coupon_image);
        InputCouponDescription =(EditText) findViewById(R.id.coupon_description);
        InputCouponName =(EditText) findViewById(R.id.coupon_name);
        InputCouponDueDate=(EditText) findViewById(R.id.coupon_dueDate);
InputCouponImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        OpenGallery();
    }
});

AddNewCouponButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        ValidateProductData();
    }
});

    }

    private void OpenGallery()
    {
        /*Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);*/

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick&&resultCode==RESULT_OK&&data!=null);
        {
            ImageUri=data.getData();
           InputCouponImage.setImageURI(ImageUri);

        }
    }
    private void ValidateProductData()
    {
        Description=InputCouponDescription.getText().toString();
        Name=InputCouponName.getText().toString();
        dueDate=InputCouponDueDate.getText().toString();

        if(ImageUri==null)
        {
            Toast.makeText(this,"Display Image (Company Logo/Item Picture/Discount Ammount) is required",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this,"Coupon Description is required",Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(Name))
        {
            Toast.makeText(this,"Coupon Name is required",Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(dueDate))
        {
            Toast.makeText(this,"Coupon Expiration Date is required",Toast.LENGTH_SHORT).show();

        }
        else
        {
            StoreCouponInformation();
        }
    }

    private void StoreCouponInformation()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());

        couponRandomKey = saveCurrentDate+saveCurrentTime;


        final StorageReference filePath = CouponImagesRef.child(ImageUri.getLastPathSegment()+couponRandomKey+"JPG");
        final UploadTask uploadTask=filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddNewProductActivity.this,"error"+ message,Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddNewProductActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri>urlTask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                            downloadImageUrl=filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                       if(task.isSuccessful())
                       {
                           downloadImageUrl=task.getResult().toString();
                           Toast.makeText(AdminAddNewProductActivity.this, "getting Coupon image URL save to database sucessfully", Toast.LENGTH_SHORT).show(); 
                       SaveProductInfoToDataBase();
                       }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDataBase()
    {
        HashMap<String,Object> CouponMap = new HashMap<>();
        CouponMap.put("pid",couponRandomKey);
        CouponMap.put("date",saveCurrentDate);
        CouponMap.put("time",saveCurrentTime);
        CouponMap.put("description",Description);
        CouponMap.put("image",downloadImageUrl);
        CouponMap.put("category",CategoryName);
        CouponMap.put("duedate",dueDate);
        CouponMap.put("Name",Name);

        CouponRef.child(couponRandomKey).updateChildren(CouponMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(AdminAddNewProductActivity.this,AdminPage.class);

                            Toast.makeText(AdminAddNewProductActivity.this, "Coupon is Added Succesfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String message =task.getException().toString();
                            Toast.makeText(AdminAddNewProductActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
