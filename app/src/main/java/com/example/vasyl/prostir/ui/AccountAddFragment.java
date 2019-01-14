package com.example.vasyl.prostir.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.vasyl.prostir.R;

public class AccountAddFragment extends Fragment {
    View rootView;
    Button qr;
    Button manual;
    public AccountAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_account_add, container, false);
       qr = rootView.findViewById(R.id.buttonQRCode);
       manual = rootView.findViewById(R.id.buttonEnterCodeManually);
       qr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FrameLayout fl = rootView.findViewById(R.id.accAddLayout);
               FragmentManager fm = getChildFragmentManager();
               FragmentTransaction transaction = fm.beginTransaction().replace(R.id.accAddLayout, new QrCodeScanFragment())
                       .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
               fl.removeAllViewsInLayout();
               transaction.commit();
           }
       });
       manual.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FrameLayout fl = rootView.findViewById(R.id.accAddLayout);
               FragmentManager fm = getChildFragmentManager();
               FragmentTransaction transaction = fm.beginTransaction().replace(R.id.accAddLayout, new EnterCodeManuallyFragment())
                       .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
               fl.removeAllViewsInLayout();
               transaction.commit();
           }
       });
       return rootView;
    }

}
