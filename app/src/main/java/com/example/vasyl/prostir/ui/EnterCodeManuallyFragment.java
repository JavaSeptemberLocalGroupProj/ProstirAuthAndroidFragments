package com.example.vasyl.prostir.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;

public class EnterCodeManuallyFragment extends Fragment {
    View rootView;
    private String TOKEN = ServiceReference.getToken();
    private Button okayBtn_ecm;
    private EditText enterSoftTokenEditText;
    private EditText description_EditText;
    private TextView errorSoftTokenTextView;
    public EnterCodeManuallyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_code_manually, container, false);
        okayBtn_ecm = rootView.findViewById(R.id.okayBtn_ecm);
        enterSoftTokenEditText = rootView.findViewById(R.id.enterSoftTokenEditText);
        errorSoftTokenTextView = rootView.findViewById(R.id.errorSoftTokenTextView);
        description_EditText =   rootView.findViewById(R.id.description_EditText_ecm);
        description_EditText.setText(ServiceReference.getDescription_Temp());
        okayBtn_ecm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = enterSoftTokenEditText.getText().toString();
                if (TOKEN.equals(temp)) {
                    errorSoftTokenTextView.setVisibility(View.INVISIBLE);
                    ServiceReference.setDescription_Temp(description_EditText.getText().toString());
                    // TODO
                    gotoDecryptedMessagePage();
                } else {
                    errorSoftTokenTextView.setVisibility(View.VISIBLE);
                    enterSoftTokenEditText.setText("");
                }
            }
        });
        return rootView;
    }
    void gotoDecryptedMessagePage(){
        FrameLayout fl = rootView.findViewById(R.id.layout);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction().replace(R.id.layout, new DecryptedMessageFragment())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fl.removeAllViewsInLayout();
        transaction.commit();
    }
}
