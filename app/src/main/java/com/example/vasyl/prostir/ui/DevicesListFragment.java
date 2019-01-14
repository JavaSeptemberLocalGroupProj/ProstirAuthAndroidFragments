package com.example.vasyl.prostir.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.data.ServiceReference;
import com.example.vasyl.prostir.models.Device;
import com.example.vasyl.prostir.utils.DeviceAdapter;

import java.util.ArrayList;

public class DevicesListFragment extends Fragment {
    FragmentActivity main;
    View rootView;
    private Switch allowSwitch;

    public DevicesListFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_devices_list, container, false);
        ArrayList<Device> device = ServiceReference.getDeviceList();
        DeviceAdapter deviceAdapter = new DeviceAdapter(main, device);
        ListView listView = rootView.findViewById(R.id.listview_flavor);
        listView.setAdapter(deviceAdapter);
        allowSwitch = rootView.findViewById(R.id.allowSwitch);
        allowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ServiceReference.setAllowMultipleDevices(true);
                    // TODO
                } else {
                    ServiceReference.setAllowMultipleDevices(false);
                    // TODO
                }
            }
        });
        return rootView;
    }

}
