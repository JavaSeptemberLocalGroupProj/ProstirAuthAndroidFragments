package com.example.vasyl.prostir.models;

import com.example.vasyl.prostir.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("type")
    @Expose
    private String deviceType;

    @SerializedName("name")
    @Expose
    private String deviceName;

    @SerializedName("date")
    @Expose
    private String lastUseDate;

    public Device(String deviceType, String deviceName, String lastUseDate) {

        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.lastUseDate = lastUseDate;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(String lastUseDate) {
        this.lastUseDate = lastUseDate;
    }

    public static int getDeviceImage(Device device) {
        if (device.getDeviceType().equals("tablet"))
            return R.drawable.ic_tablet;
        else
            return R.drawable.ic_smartphone;

    }
}
