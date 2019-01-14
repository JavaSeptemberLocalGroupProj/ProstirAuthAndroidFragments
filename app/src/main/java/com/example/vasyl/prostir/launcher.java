package com.example.vasyl.prostir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.vasyl.prostir.data.ServiceReference;
import com.example.vasyl.prostir.ui.AuthorizationFragment;
import com.example.vasyl.prostir.ui.CountryPickerFragment;

public class launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceReference.initDataBase();
        if (true) {
            FrameLayout fl = findViewById(R.id.listLayout);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().replace(R.id.listLayout, new CountryPickerFragment())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fl.removeAllViewsInLayout();
            transaction.commit();
        } else {
            FrameLayout fl = findViewById(R.id.decryptLayout);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().replace(R.id.decryptLayout, new AuthorizationFragment())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fl.removeAllViewsInLayout();
            transaction.commit();
        }

    }
}

