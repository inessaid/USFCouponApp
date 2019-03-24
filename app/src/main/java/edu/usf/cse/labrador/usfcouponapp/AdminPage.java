package edu.usf.cse.labrador.usfcouponapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminPage extends AppCompatActivity {

    private ImageView boba,coffee,tea,clothing,services,icecream,pastries,food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        boba = (ImageView) findViewById(R.id.boba);
        coffee = (ImageView) findViewById(R.id.coffee);
        clothing = (ImageView) findViewById(R.id.clothing);
        icecream = (ImageView) findViewById(R.id.icecream);
        tea = (ImageView) findViewById(R.id.tea);
        services = (ImageView) findViewById(R.id.services);
        pastries = (ImageView) findViewById(R.id.pastries);
        food = (ImageView) findViewById(R.id.food);


        boba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","boba");
                startActivity(intent);
            }
        });

        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","coffee");
                startActivity(intent);
            }
        });

        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","clothing");
                startActivity(intent);
            }
        });

        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","tea");
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","food");
                startActivity(intent);
            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","services");
                startActivity(intent);
            }
        });

        icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","icecream");
                startActivity(intent);
            }
        });

        pastries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","pastries");
                startActivity(intent);
            }
        });

    }
}
