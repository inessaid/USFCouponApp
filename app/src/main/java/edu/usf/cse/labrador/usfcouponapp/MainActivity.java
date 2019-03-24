package edu.usf.cse.labrador.usfcouponapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //userID = getIntent().getStringExtra("EXTRA_UID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new couponsFragment());
    }
// a method to switch the fragment in the layout
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    // this method will be called whenever we click on one of the bottom navigation icons
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_coupons:
                fragment = new couponsFragment();
                break;

            case R.id.navigation_favorites:
                fragment = new favoritesFragment();
                break;

            case R.id.navigation_map:
                fragment = new mapFragment();
                break;

            case R.id.navigation_settings:
                Log.d("BLAH","Going to settings");
                //Bundle bundle = new Bundle();
                //bundle.putString("FRAG_UID", userID);
                fragment = new settingsFragment();
                //fragment.setArguments(bundle);
                Log.d("BLAH", "After Switch statement of new settingsFragment");
                break;
        }

        return loadFragment(fragment);
    }
}
