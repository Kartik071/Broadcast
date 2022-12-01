package com.example.broadcastrec;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.broadcastrec.databinding.ActivityMainBinding;
import com.example.broadcastrec.databinding.FragmentOneBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements onConnectivityChange, onAirplaneMode, NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    Reciever reciever;
    airplaneRec planerec;
    FragmentOneBinding fragmentOneBinding;
    private DrawerLayout draw;
    String TAG = "ANDROID:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        reciever = new Reciever(this);
        //  planerec= new airplaneRec(this);
        //registerRec();

        draw = binding.drawerLayout;
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw, toolbar, R.string.navigation_open_drawer, R.string.navigation_close_drawer);
        draw.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        registerAir();
        binding.Btn.setOnClickListener(v1 -> {
            registerRec();


        });
        Fragment fragment;
        fragment = new FragmentOne();
        loadfragment(fragment);
    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id, fragment).addToBackStack(null).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                loadfragment(new Message_Fragment());
                break;
            case R.id.nav_chat:
                //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id, new Chat_Fragment()).commit();
                loadfragment(new Chat_Fragment());
                break;
            case R.id.nav_profile:
                //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id, new Profile_fragment()).commit();
                loadfragment(new Profile_fragment());
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();


        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (draw.isDrawerOpen(GravityCompat.START)) {
            draw.isDrawerOpen(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // registerRec();
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregister();
//
//    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregister();
    }

    public void registerRec() {
        registerReceiver(reciever, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    public void registerAir() {
        Log.d(TAG, "registerAir: ");
        registerReceiver(new airplaneRec(this), new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

    }

    public void unregister() {
        unregisterReceiver(reciever);
        unregisterReceiver(planerec);
    }

    @Override
    public void onConnectionChange(boolean onChange) {
        if (onChange)
            Snackbar.make(binding.layout, "Connected", Snackbar.LENGTH_SHORT).show();
        else
            Snackbar.make(binding.layout, "DisConnected", Snackbar.LENGTH_SHORT).show();

    }


    @Override
    public void onAirplaneMode(boolean onChangeAir) {
        if (onChangeAir) {
            Snackbar.make(binding.layout, "Airplane Mode On", Snackbar.LENGTH_INDEFINITE).show();
            Log.d(TAG, "onAirplaneMode: ");
        } else {

            Log.d(TAG, "onAirplaneMode: off");
            Snackbar.make(binding.layout, "Airplane Mode OF", Snackbar.LENGTH_INDEFINITE).show();
        }
    }


}