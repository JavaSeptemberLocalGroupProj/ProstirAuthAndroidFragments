package com.example.vasyl.prostir.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;
import com.goodiebag.pinview.Pinview;

import org.w3c.dom.Text;

public class AcceptBySmsFragment extends Fragment {
    FragmentActivity main;
    View rootView;
    private final static String PASSWORD = "0123";
    private final static String PHONENUMBER = "6505551212"; // Change number !!!
    static TextView tw;
    static Pinview pw;
    TextView sendAgain;
    public static boolean isAcceptBySmsActive = false;
    public AcceptBySmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_accept_by_sms, container, false);
        sendAgain = rootView.findViewById(R.id.sendAgainTextView);
        pw = rootView.findViewById(R.id.pinView);
        tw = rootView.findViewById(R.id.errorMsgTextView);
        pw.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                isRightPassword(pinview.getValue());
            }
        });
        sendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main.getApplicationContext(),
                        "Sending message...",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return rootView;
    }
    public  void isRightPassword (String enteredPassword){
        if (enteredPassword.equals(PASSWORD))  {
            FrameLayout fl = rootView.findViewById(R.id.smsLayout);
            FragmentManager fm = getChildFragmentManager();
            isAcceptBySmsActive = false;
            ServiceReference.addCurrentToDeviceList();
            FragmentTransaction transaction = fm.beginTransaction().replace(R.id.smsLayout, new AccountsListFragment())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fl.removeAllViewsInLayout();
            transaction.commit();
        }
        else {
            tw.setText("The code is incorrect!");
            pw.setInputType(Pinview.InputType.TEXT);
            pw.setValue("");
            pw.setInputType(Pinview.InputType.NUMBER);
        }
    }
    public static boolean isRightPhone (String PhoneNumber) {
        boolean result = false;
        if (PhoneNumber.equals(PHONENUMBER)) result = true;
        return result;
    }
    public static void setPw (String code) {
        pw.setValue(code);
    }
}
