package com.example.vasyl.prostir.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import com.example.vasyl.prostir.ui.AcceptByCallFragment;
import com.example.vasyl.prostir.models.ITelephony;

public class CallReceiver extends BroadcastReceiver {

    private String incomingPhoneNumber;
    private final String serverPhoneNumber = ServiceReference.getPhoneNumber();

    public void onReceive(Context context, Intent intent) {
        if (AcceptByCallFragment.isAcceptByCallActive) {
            if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
                String phone_state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                if (phone_state.equals(TelephonyManager.EXTRA_STATE_RINGING)) { // Get incoming number
                    incomingPhoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    //if (incomingPhoneNumber.equals(serverPhoneNumber)) { // This if block adds phone checker
                    AcceptByCallFragment.setEnterText();
                    //}
                } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    // if phone is in on line mode
                    // TODO
                } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    // phone in sleeping mode/end of a call
                    // TODO
                }
            }
        }
    }
}
