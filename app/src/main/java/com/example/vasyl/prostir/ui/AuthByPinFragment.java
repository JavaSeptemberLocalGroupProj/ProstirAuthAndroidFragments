package com.example.vasyl.prostir.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vasyl.prostir.R;
import com.goodiebag.pinview.Pinview;


public class AuthByPinFragment extends Fragment {
    View rootView;
    private final static String PASSWORD = "0123";
    static TextView tw;
    static Pinview pw;

    public AuthByPinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_auth_by_pin, container, false);
        pw = rootView.findViewById(R.id.pinViewForAuth);
        tw = rootView.findViewById(R.id.errorMessageText);
        pw.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                if (isRightPassword(pinview.getValue())){
                    FrameLayout fl = rootView.findViewById(R.id.pinLayout);
                    FragmentManager fm = getChildFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction().replace(R.id.pinLayout, new AccountsListFragment())
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    fl.removeAllViewsInLayout();
                    transaction.commit();
                }
            }
        });
        return rootView;
    }
    public boolean isRightPassword(String enteredPassword){
        if(enteredPassword.length() == 4){
            if (enteredPassword.equals(PASSWORD)) {
                tw.setText("You're enter");
                return true;
            }
            else {
                tw.setText("The code is incorrect!");
                pw.setInputType(Pinview.InputType.TEXT);
                pw.setValue("");
                pw.setInputType(Pinview.InputType.NUMBER);
                return false;
            }
        }
        return false;
    }
}
