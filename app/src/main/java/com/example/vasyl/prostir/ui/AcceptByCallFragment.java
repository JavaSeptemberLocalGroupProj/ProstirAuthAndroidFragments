package com.example.vasyl.prostir.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;

public class AcceptByCallFragment extends Fragment {
    View rootView;
    FragmentActivity main;
    TextView dot1, dot2, dot3;
    TextView[] dots;
    TextView callAgain;
    static TextView callingTextView;
    public static boolean isAcceptByCallActive = false;
    public AcceptByCallFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_accept_by_call, container, false);
        dot1 = rootView.findViewById(R.id.dot1);
        dot2 = rootView.findViewById(R.id.dot2);
        dot3 = rootView.findViewById(R.id.dot3);
        dots = new TextView[] {dot1, dot2, dot3};
        callAgain = rootView.findViewById(R.id.callAgainTextView);
        callingTextView = rootView.findViewById(R.id.callAgainTextView);
        for (TextView item: dots) {
            item.setVisibility(View.INVISIBLE);
        }
        new CountDownTimer((long)Double.POSITIVE_INFINITY, 1000) {
            int counter;
            @Override
            public void onTick(long millisUntilFinished) {
                if(counter == 3) {
                    counter = 0;
                    for (TextView item: dots) {
                        item.setVisibility(View.INVISIBLE);
                    }
                }
                dots[counter].setVisibility(View.VISIBLE);
                counter++;
            }
            @Override
            public void onFinish() { }
        }.start();
        callAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(main.getApplicationContext(),
                        "Recalling...", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        callingTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goToNextPage();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        return rootView;
    }
    private void goToNextPage() {
        isAcceptByCallActive = false;
         ServiceReference.addCurrentToDeviceList();
        FrameLayout fl = rootView.findViewById(R.id.callLayout);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction().replace(R.id.callLayout, new AccountsListFragment())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fl.removeAllViewsInLayout();
        transaction.commit();
    }
    public static void setEnterText() {
        callingTextView.setText("Call Received!");
    }
}
