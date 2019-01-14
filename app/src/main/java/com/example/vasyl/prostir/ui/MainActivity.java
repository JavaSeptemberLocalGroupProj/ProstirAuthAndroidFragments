package com.example.vasyl.prostir.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.FrameLayout;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceReference.connectToServer();
        ServiceReference.setUserPhoneNumber(requireUserPhoneNumber());

        if (ServiceReference.isRegisteredDevice()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().replace(R.id.mainLayout, new CountryPickerFragment())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.commit();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().replace(R.id.mainLayout, new AuthorizationFragment())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.commit();
        }
    }


    private String requireUserPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String userPhoneNumber = "";
        try {
            userPhoneNumber = telephonyManager.getLine1Number();
        } catch (SecurityException e) {}
        return userPhoneNumber;
    }
}
