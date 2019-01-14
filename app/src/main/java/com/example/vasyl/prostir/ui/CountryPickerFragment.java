package com.example.vasyl.prostir.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;
import com.rilixtech.CountryCodePicker;

public class CountryPickerFragment extends Fragment {
    View rootView;

    com.rilixtech.CountryCodePicker CountryCodePicker;
    TextView numberTextView;
    EditText phoneNumber;
    Button toNextPageBtn;

    String countryCode;
    String userNumberPhone;
    String userFullNumber;

    public CountryPickerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_country_picker, container, false);
        CountryCodePicker = rootView.findViewById(R.id.countryCodePicker);
        // ADD FUNCTIONALITY TO GET USER PHONE NUMBER
        toNextPageBtn = rootView.findViewById(R.id.toNextPageBtn);
        numberTextView = rootView.findViewById(R.id.numberEditText);
        phoneNumber = rootView.findViewById(R.id.numberEditText);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(14);
        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        toNextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNumber();
                FrameLayout fl = rootView.findViewById(R.id.pickerLayout);
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().replace(R.id.pickerLayout, new SelectAuthTypeFragment())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fl.removeAllViewsInLayout();
                // i.putExtra("USER_NUMBER", userNumberPhone);
                transaction.commit();
            }
        });
        return rootView;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        ServiceReference.setUserPhoneNumber(phoneNumber.getText().toString());
        FrameLayout fl = rootView.findViewById(R.id.pickerLayout);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction().replace(R.id.pickerLayout, new SelectAuthTypeFragment())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fl.removeAllViewsInLayout();
        transaction.commit();
        return true;
    }
    public void getNumber(){
        countryCode = CountryCodePicker.getSelectedCountryCodeWithPlus();
        userFullNumber = countryCode;
        /*userNumberPhone = numberTextView.getText().toString();
        userFullNumber = userFullNumber + userNumberPhone;*/
    }
}
