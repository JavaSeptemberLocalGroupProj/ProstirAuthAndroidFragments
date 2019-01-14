package com.example.vasyl.prostir.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasyl.prostir.R;
import com.example.vasyl.prostir.models.Device;

import java.util.ArrayList;

public class DeviceAdapter extends ArrayAdapter<Device> {

    private static final String LOG_TAG = DeviceAdapter.class.getSimpleName();
    public DeviceAdapter(Activity context, ArrayList<Device> Device) {
        super(context, 0, Device);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_template, parent, false);
        }
        Device currentDevice = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        nameTextView.setText(currentDevice.getDeviceName());
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        numberTextView.setText(currentDevice.getLastUseDate());
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(Device.getDeviceImage(currentDevice));
        return listItemView;

    }
}




