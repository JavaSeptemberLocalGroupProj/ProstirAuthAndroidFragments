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

public class DecryptedMessageFragment extends Fragment {
    private String codeTemp = ServiceReference.getCodeForAccount();
    private TextView passwordTextView;
    private EditText description_EditText;
    Button b;
    View rootView;
    public DecryptedMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_decrypted_message, container, false);
        b = rootView.findViewById(R.id.okayBtn_dm);
        passwordTextView = rootView.findViewById(R.id.passwordTextView);
        passwordTextView.setText(codeTemp);
        description_EditText = rootView.findViewById(R.id.description_EditText_dm);
        description_EditText.setText(ServiceReference.getDescription_Temp());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = description_EditText.getText().toString();
                String token = codeTemp;
                ServiceReference.addItemToArrayListOfAccounts(name, token);
                FrameLayout fl = rootView.findViewById(R.id.decryptLayout);
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().replace(R.id.decryptLayout, new AuthorizationFragment())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fl.removeAllViewsInLayout();
                transaction.commit();
            }
        });
        return rootView;
    }

}
